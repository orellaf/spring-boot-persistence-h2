package com.baeldung.h2db.springboot.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baeldung.h2db.springboot.models.Aid;

public interface AidRepository extends JpaRepository<Aid, Integer>{

}
