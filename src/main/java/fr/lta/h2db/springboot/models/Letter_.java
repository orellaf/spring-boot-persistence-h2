package fr.lta.h2db.springboot.models;

import java.time.LocalDateTime;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Letter.class)
public class Letter_ {
	
	public static volatile SingularAttribute<Letter, Integer> id;

    public static volatile SingularAttribute<Letter, LetterCode> code;

    public static volatile SingularAttribute<Letter, Certificate> certificate;
    
    public static volatile SingularAttribute<Letter, Aid> aid;

//    public static volatile SetAttribute<Letter, Item> items;

    public static volatile SingularAttribute<Letter, LocalDateTime> createdAt;

}