package fr.lta.h2db.springboot.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.lta.h2db.springboot.models.Aid;

public interface AidRepository extends JpaRepository<Aid, Integer>{

}
