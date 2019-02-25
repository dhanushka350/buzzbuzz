package com.akvasoft.buzzbuzz.config;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repo extends JpaRepository<Modal, Integer> {
    Modal getByLinkEquals(String link);
}
