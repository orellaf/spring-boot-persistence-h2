package com.baeldung.h2db.springboot.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "letters")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Letter {
	
	@Id
	@GeneratedValue
	private Integer id;

	@Enumerated(EnumType.STRING)
	private LetterCode code;

	@ManyToOne
	@JoinColumn(name = "certificate_id")
	private Certificate certificate;

	@ManyToOne
	@JoinColumn(name = "aid_id")
	private Aid aid;

	private LocalDateTime createdAt;
}
