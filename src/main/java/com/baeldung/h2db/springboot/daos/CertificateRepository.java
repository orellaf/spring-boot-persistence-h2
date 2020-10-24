package com.baeldung.h2db.springboot.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baeldung.h2db.springboot.models.Certificate;

public interface CertificateRepository extends JpaRepository<Certificate, Integer>{

}
