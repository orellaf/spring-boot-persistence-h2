package fr.lta.h2db.springboot.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.lta.h2db.springboot.models.Postman;

public interface PostmanRepository extends JpaRepository<Postman, String>{

	void deleteByIdRH(String idRH);

}
