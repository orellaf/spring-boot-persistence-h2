package com.baeldung.h2db.springboot.models;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Postman.class)
public class Postman_ {

	public static volatile SingularAttribute<Postman, String> idRH;

	public static volatile SingularAttribute<Postman, String> firstname;

	public static volatile SingularAttribute<Postman, String> lastname;
}