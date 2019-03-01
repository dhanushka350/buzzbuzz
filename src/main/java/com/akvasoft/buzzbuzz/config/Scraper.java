package com.akvasoft.buzzbuzz.config;


import com.opencsv.CSVWriter;
import org.openqa.selenium.By;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
public class Scraper implements InitializingBean {

    @Autowired
    private Repo repo;

    @Override
    public void afterPropertiesSet() throws Exception {
//        Links links = new Links();
//        FirefoxDriver driver = new DriverInitializer().getFirefoxDriver();
//        int x = 1;
//        boolean ok = false;
//        Modal modal = null;
//        for (String link : links.getLinks()) {
//            if (link.equalsIgnoreCase("https://www.buzzbuzzhome.com/3170-west-olympic-boulevard")) {
//                ok = true;
//            }
//
//            if (!ok) {
//                x++;
//                continue;
//            }
//            modal = new Modal();
//            modal.setLink(link);
//            modal = repo.save(modal);
//            scrape(link, driver, modal);

//            createExcelFile();
//            System.out.println("=============" + x);
//            x++;
//        }


    }

    private boolean scrape(String url, FirefoxDriver driver, Modal modal) throws Exception, SessionNotCreatedException {
        System.out.println(url);
        driver.get(url);
        WebElement name = driver.findElementByXPath("/html/body/div[1]/div[3]/div[4]/div[1]/div[1]/div/div[1]/div[1]/div[1]");
        WebElement address = driver.findElementByXPath("/html/body/div[1]/div[3]/div[4]/div[1]/div[1]/div/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]");

        String com_name = name.findElement(By.tagName("h1")).getAttribute("innerText");
        String com_address = address.getAttribute("innerText").split(",")[0];
        String com_city = address.findElements(By.tagName("a")).get(0).getAttribute("innerText");

        WebElement price = driver.findElementByXPath("/html/body/div[1]/div[3]/div[4]/div[1]/div[1]/div/div[1]/div[1]/div[2]");
        String com_price = price.getAttribute("innerText").replace("Sales from", "");

        WebElement section_two = driver.findElementByXPath("/html/body/div[1]/div[3]/div[4]/div[1]/div[1]/div/div[2]/div");
        String types = "";
        String units = "";
        String stories = "";
        String square_feet = "";
        String price_per_quare_eet = "";
        String phase = "";
        try {
            types = section_two.findElements(By.xpath("./*")).get(0).getAttribute("innerText");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String unitAndStories = section_two.findElements(By.xpath("./*")).get(1).getAttribute("innerText");
            units = unitAndStories.split("Units")[0];
            stories = unitAndStories.split("Units")[1];

            units = units.replace("Units", "");
            stories = stories.replace("Stories", "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            square_feet = section_two.findElements(By.xpath("./*")).get(4).getAttribute("innerText");
        } catch (Exception e) {
//            e.printStackTrace();
        }

        try {
            price_per_quare_eet = section_two.findElements(By.xpath("./*")).get(5).getAttribute("innerText");
        } catch (Exception e) {
//            e.printStackTrace();
        }

        try {
            phase = section_two.findElements(By.xpath("./*")).get(3).getAttribute("innerText");
        } catch (Exception e) {
//            e.printStackTrace();
        }

        modal.setLink(url);
        modal.setName(com_name);
        modal.setAddress(com_address);
        modal.setCity(com_city);
        modal.setPrice_Range(com_price);
        modal.setType(types);
        modal.setUnits(units);
        modal.setStories(stories);
        modal.setSquare_Feet(square_feet);
        modal.setPrice_per_Square_Feet(price_per_quare_eet);
        modal.setPhase(phase);

        getDetails(driver, modal);
        return true;
    }

    private void getDetails(FirefoxDriver driver, Modal modal) {
        String web = "";
        String ownership = "";
        String selling_status = "";
        String sales_started = "";
        String estimated_completion = "";
        String architect = "";
        String builder = "";
        String builder_web = "";
        String builder_contact = "";
        String marketing_company = "";
        String marketing_company_web = "";
        String sales_company = "";
        String sales_company_web = "";

        WebElement details = driver.findElementByXPath("//*[@id=\"details\"]");
        List<WebElement> list = details.findElement(By.className("row")).findElements(By.xpath("./*"));
        for (WebElement element : list.get(0).findElements(By.xpath("./*"))) {

            if (element.getAttribute("innerText").contains("Website:")) {
                try {
                    web = element.findElement(By.tagName("a")).getAttribute("href");
                } catch (Exception e) {
                }

            } else if (element.getAttribute("innerText").contains("Ownership:")) {
                ownership = element.getAttribute("innerText").replace("Ownership:", "");
            } else if (element.getAttribute("innerText").contains("Selling Status:")) {
                selling_status = element.getAttribute("innerText").replace("Selling Status:", "");
            } else if (element.getAttribute("innerText").contains("Sales Started:")) {
                sales_started = element.getAttribute("innerText").replace("Sales Started:", "");
            } else if (element.getAttribute("innerText").contains("Estimated Completion:")) {
                estimated_completion = element.getAttribute("innerText").replace("Estimated Completion:", "");
            } else if (element.getAttribute("innerText").contains("Construction Status:")) {
                modal.setPhase(element.getAttribute("innerText").replace("Construction Status:", ""));
            }

        }

        for (WebElement element : list.get(1).findElements(By.xpath("./*"))) {

            if (element.getAttribute("innerText").contains("Architect(s):")) {
                architect = element.getAttribute("innerText").replace("Architect(s):", "");
            } else if (element.getAttribute("innerText").contains("Builder(s):")) {
                try {
                    builder = element.findElement(By.className("content-text")).getAttribute("innerText").replace("Builder(s):", "");
                    builder_web = element.findElement(By.className("content-text")).findElement(By.tagName("a")).getAttribute("href");
                } catch (Exception e) {
//                    System.err.println(e.getMessage());
                }

            } else if (element.getAttribute("innerText").contains("Marketing Company")) {
                try {
                    marketing_company = element.getAttribute("innerText").replace("Marketing Company:", "");
                    marketing_company_web = element.findElement(By.className("content-text")).findElement(By.tagName("a")).getAttribute("href");
                } catch (Exception e) {
//                    System.err.println(e.getMessage());
                }
            } else if (element.getAttribute("innerText").contains("Sales Company:")) {
                try {
                    sales_company = element.getAttribute("innerText").replace("Sales Company:", "");
                    sales_company_web = element.findElement(By.className("content-text")).findElement(By.tagName("a")).getAttribute("href");
                } catch (Exception e) {
//                    System.err.println(e.getMessage());
                }
            }
        }

        if (builder_web.length() > 2) {
            String res = getBuilderContact(builder_web, driver);
            try {
                builder_contact = res.split("#")[0];
                builder_web = res.split("#")[1];
            } catch (Exception r) {
            }
        }
        if (marketing_company_web.length() > 2) {
            String res = getMarketingContact(marketing_company_web, driver);
            marketing_company_web = res;
        }
        if (sales_company_web.length() > 2) {
            try {
                String res = getSalesContact(sales_company_web, driver);
                sales_company_web = res;
            } catch (Exception e) {
                sales_company_web = "";
            }
        }

        modal.setWebsite(web);
        modal.setOwnership(ownership);
        modal.setSelling_Status(selling_status);
        modal.setSales_Start(sales_started);
        modal.setEstimated_Completion(estimated_completion);
        modal.setArchitect(architect);
        modal.setBuilder(builder);
        modal.setBuilder_Website(builder_web);
        modal.setBuilder_Phone(builder_contact);
        modal.setMarketing_Company(marketing_company);
        modal.setMarketing_Website(marketing_company_web);
        modal.setSales_Company(sales_company);
        modal.setSales_Website(sales_company_web);
        repo.save(modal);
        System.gc();
    }

    private String getSalesContact(String sales_company_web, FirefoxDriver driver) throws Exception {
        driver.get(sales_company_web);
        WebElement web = driver.findElementByXPath("/html/body/div[1]/div[4]/div[1]/div/div[1]/div[1]/div[3]/p[3]");
        return web.findElement(By.tagName("a")).getAttribute("href");
    }

    private String getMarketingContact(String marketing_company_web, FirefoxDriver driver) {
        driver.get(marketing_company_web);
        WebElement web = driver.findElementByXPath("/html/body/div[1]/div[4]/div[1]/div/div[1]/div[1]/div[3]/p[3]");
        return web.findElement(By.tagName("a")).getAttribute("href");
    }

    private String getBuilderContact(String builder_web, FirefoxDriver driver) {
        String site = "";
        String contact = "";
        driver.get(builder_web);
        try {
            WebElement elementByXPath = driver.findElementByXPath("/html/body/div[1]/div[4]/div[1]/div/div[1]/div[1]/div[5]/p[2]");
            contact = elementByXPath.getAttribute("innerText").replace("Telephone:", "");
        } catch (Exception r) {

        }
        try {
            WebElement site_ele = driver.findElementByXPath("/html/body/div[1]/div[4]/div[1]/div/div[1]/div[1]/div[5]/p[3]");
            site = site_ele.findElement(By.tagName("a")).getAttribute("href");
        } catch (Exception r) {

        }
        return contact + "#" + site;
    }

    private synchronized void saveAddress(Modal modal) throws Exception {
        repo.save(modal);

    }

    private void createExcelFile() throws IOException {
        List<Modal> datas = repo.findAll();
        List<Modal> datalist = new ArrayList<>();

        for (Modal datum : datas) {
            for (Modal modal : datalist) {
                if (modal.getLink().equalsIgnoreCase(datum.getLink())) {
                    continue;
                }
                datalist.add(datum);
            }

        }

        String file = "/var/lib/tomcat8/CSV/buzzbuzz_phase_update.csv";

        CSVWriter csvWriter = new CSVWriter(new FileWriter("/var/lib/tomcat8/CSV/buzzbuzz_phase_update.csv"));
        List<String[]> csvRows = new LinkedList<String[]>();
        csvRows.add(new String[]
                {
                        "Buzz Buzz Link",
                        "Name",
                        "Price Range",
                        "Address",
                        "City",
                        "Type",
                        "Units",
                        "Stories",
                        "Square Feet",
                        "Price per Square Feet",
                        "Phase",
                        "Website",
                        "Ownership",
                        "Selling Status",
                        "Sales Start",
                        "Estimated Completion",
                        "Architect",
                        "Builder",
                        "Builder Website",
                        "Builder Phone",
                        "Marketing Company",
                        "Marketing Website",
                        "Sales Company",
                        "Sales Website"
                });

        for (Modal data : datalist) {
            csvRows.add(new String[]
                    {
                            data.getLink(),
                            data.getName(),
                            data.getPrice_Range(),
                            data.getAddress(),
                            data.getCity(),
                            data.getType(),
                            data.getUnits(),
                            data.getStories(),
                            data.getSquare_Feet(),
                            data.getPrice_per_Square_Feet(),
                            data.getPhase(),
                            data.getWebsite(),
                            data.getOwnership(),
                            data.getSelling_Status(),
                            data.getSales_Start(),
                            data.getEstimated_Completion(),
                            data.getArchitect(),
                            data.getBuilder(),
                            data.getBuilder_Website(),
                            data.getBuilder_Phone(),
                            data.getMarketing_Company(),
                            data.getMarketing_Website(),
                            data.getSales_Company(),
                            data.getSales_Website()
                    });
        }
        csvWriter.writeAll(csvRows);
        csvWriter.close();

    }
}
