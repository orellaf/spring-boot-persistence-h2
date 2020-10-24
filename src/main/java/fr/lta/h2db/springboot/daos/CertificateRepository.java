package fr.lta.h2db.springboot.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.lta.h2db.springboot.models.Certificate;

public interface CertificateRepository extends JpaRepository<Certificate, Integer>{

}
