package fr.lta.h2db.springboot.daos;

import org.springframework.data.repository.PagingAndSortingRepository;

import fr.lta.h2db.springboot.models.Aid;

public interface AidRepository extends PagingAndSortingRepository<Aid, Integer>{

}
