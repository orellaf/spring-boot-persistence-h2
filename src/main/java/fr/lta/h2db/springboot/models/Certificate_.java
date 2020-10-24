package fr.lta.h2db.springboot.models;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Certificate.class)
public class Certificate_ {

    public static volatile SingularAttribute<Certificate, Integer> id;

    public static volatile SingularAttribute<Certificate, Postman> postman;
    
    public static volatile SingularAttribute<Certificate, String> number;


}