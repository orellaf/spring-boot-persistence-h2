package fr.lta.h2db.springboot.models;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Aid.class)
public class Aid_ {

    public static volatile SingularAttribute<Aid, Integer> id;

    public static volatile SingularAttribute<Aid, Postman> postman;
    
    public static volatile SingularAttribute<Aid, String> requestNumber;


}