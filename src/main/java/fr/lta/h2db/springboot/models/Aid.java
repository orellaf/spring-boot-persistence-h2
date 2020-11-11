package fr.lta.h2db.springboot.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "aids")
@Entity
@Data
@NoArgsConstructor
public class Aid {
	@Id
	@GeneratedValue
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "postman_id")
	private Postman postman;

	private String requestNumber;

	@ManyToOne
	@JoinColumn(name = "certificate_id", nullable = false)
	private Certificate certificate;

	@Setter(AccessLevel.NONE)
	@Column(updatable = false, nullable = false)
	@CreationTimestamp
	private LocalDateTime createAt;

	@Builder
	public Aid(Integer id, Postman postman, String requestNumber, Certificate certificate) {
		super();
		this.id = id;
		this.postman = postman;
		this.requestNumber = requestNumber;
		this.certificate = certificate;
	}
	
	
	
}
