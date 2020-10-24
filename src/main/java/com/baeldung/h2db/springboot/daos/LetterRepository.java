package com.baeldung.h2db.springboot.daos;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.baeldung.h2db.springboot.models.Letter;
import com.baeldung.h2db.springboot.models.LetterCode;

public interface LetterRepository  extends PagingAndSortingRepository<Letter, LetterCode>,
	JpaSpecificationExecutor<Letter>{

}
