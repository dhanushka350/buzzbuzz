package com.akvasoft.buzzbuzz.config;

import javax.persistence.*;

@Entity
@Table(name = "buzzbuzz")
public class Modal {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "link")
    String link;

    @Column(name = "Name")
    String Name;

    @Column(name = "Price_Range")
    String Price_Range;

    @Column(name = "Address")
    String Address;

    @Column(name = "City")
    String City;

    @Column(name = "Type")
    String Type;

    @Column(name = "Units")
    String Units;

    @Column(name = "Stories")
    String Stories;

    @Column(name = "Square_Feet")
    String Square_Feet;

    @Column(name = "Price_per_Square_Feet")
    String Price_per_Square_Feet;

    @Column(name = "Phase")
    String Phase;

    @Column(name = "Website")
    String Website;

    @Column(name = "Ownership")
    String Ownership;

    @Column(name = "Selling_Status")
    String Selling_Status;

    @Column(name = "Sales_Start")
    String Sales_Start;

    @Column(name = "Estimated_Completion")
    String Estimated_Completion;

    @Column(name = "Architect")
    String Architect;

    @Column(name = "Builder")
    String Builder;

    @Column(name = "Builder_Website")
    String Builder_Website;

    @Column(name = "Builder_Phone")
    String Builder_Phone;

    @Column(name = "Marketing_Company")
    String Marketing_Company;

    @Column(name = "Marketing_Website")
    String Marketing_Website;

    @Column(name = "Sales_Company")
    String Sales_Company;

    @Column(name = "Sales_Website")
    String Sales_Website;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice_Range() {
        return Price_Range;
    }

    public void setPrice_Range(String price_Range) {
        Price_Range = price_Range;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getUnits() {
        return Units;
    }

    public void setUnits(String units) {
        Units = units;
    }

    public String getStories() {
        return Stories;
    }

    public void setStories(String stories) {
        Stories = stories;
    }

    public String getSquare_Feet() {
        return Square_Feet;
    }

    public void setSquare_Feet(String square_Feet) {
        Square_Feet = square_Feet;
    }

    public String getPrice_per_Square_Feet() {
        return Price_per_Square_Feet;
    }

    public void setPrice_per_Square_Feet(String price_per_Square_Feet) {
        Price_per_Square_Feet = price_per_Square_Feet;
    }

    public String getPhase() {
        return Phase;
    }

    public void setPhase(String phase) {
        Phase = phase;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public String getOwnership() {
        return Ownership;
    }

    public void setOwnership(String ownership) {
        Ownership = ownership;
    }

    public String getSelling_Status() {
        return Selling_Status;
    }

    public void setSelling_Status(String selling_Status) {
        Selling_Status = selling_Status;
    }

    public String getSales_Start() {
        return Sales_Start;
    }

    public void setSales_Start(String sales_Start) {
        Sales_Start = sales_Start;
    }

    public String getEstimated_Completion() {
        return Estimated_Completion;
    }

    public void setEstimated_Completion(String estimated_Completion) {
        Estimated_Completion = estimated_Completion;
    }

    public String getArchitect() {
        return Architect;
    }

    public void setArchitect(String architect) {
        Architect = architect;
    }

    public String getBuilder() {
        return Builder;
    }

    public void setBuilder(String builder) {
        Builder = builder;
    }

    public String getBuilder_Website() {
        return Builder_Website;
    }

    public void setBuilder_Website(String builder_Website) {
        Builder_Website = builder_Website;
    }

    public String getBuilder_Phone() {
        return Builder_Phone;
    }

    public void setBuilder_Phone(String builder_Phone) {
        Builder_Phone = builder_Phone;
    }

    public String getMarketing_Company() {
        return Marketing_Company;
    }

    public void setMarketing_Company(String marketing_Company) {
        Marketing_Company = marketing_Company;
    }

    public String getMarketing_Website() {
        return Marketing_Website;
    }

    public void setMarketing_Website(String marketing_Website) {
        Marketing_Website = marketing_Website;
    }

    public String getSales_Company() {
        return Sales_Company;
    }

    public void setSales_Company(String sales_Company) {
        Sales_Company = sales_Company;
    }

    public String getSales_Website() {
        return Sales_Website;
    }

    public void setSales_Website(String sales_Website) {
        Sales_Website = sales_Website;
    }
}
