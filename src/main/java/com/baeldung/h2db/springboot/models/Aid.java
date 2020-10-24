package com.baeldung.h2db.springboot.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "aids")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Aid {
	@Id
	@GeneratedValue
	private Integer id;

	@ManyToOne
	@JoinColumn(name= "postman_id")
	private Postman postman;

	private String requestNumber;
}
