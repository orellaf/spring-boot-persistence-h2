package com.baeldung.h2db.springboot.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "postmen")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Postman {
	
	@Id
	@Column(name = "id_rh")
	String idRH;
	
	String firstname;
	
	String lastname;
}
