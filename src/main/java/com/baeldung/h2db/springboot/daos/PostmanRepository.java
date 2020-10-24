package com.baeldung.h2db.springboot.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baeldung.h2db.springboot.models.Postman;

public interface PostmanRepository extends JpaRepository<Postman, String>{

	void deleteByIdRH(String idRH);

}
