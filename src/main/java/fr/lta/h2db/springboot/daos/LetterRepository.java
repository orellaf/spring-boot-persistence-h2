package fr.lta.h2db.springboot.daos;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import fr.lta.h2db.springboot.models.Letter;
import fr.lta.h2db.springboot.models.LetterCode;

public interface LetterRepository  extends PagingAndSortingRepository<Letter, LetterCode>,
	JpaSpecificationExecutor<Letter>{

}
