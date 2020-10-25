package fr.lta.h2db.springboot.specifications;

import javax.persistence.criteria.JoinType;

import org.springframework.data.jpa.domain.Specification;

import fr.lta.h2db.springboot.models.Aid_;
import fr.lta.h2db.springboot.models.Certificate_;
import fr.lta.h2db.springboot.models.Letter;
import fr.lta.h2db.springboot.models.Letter_;
import fr.lta.h2db.springboot.models.Postman_;

public abstract class LetterSpecifications {

	public static Specification<Letter> idRH(String idRh) {
		return (root, query, criteriaBuilder) -> {

			var certPath = root.join(Letter_.certificate, JoinType.LEFT).join(Certificate_.postman, JoinType.LEFT)
					.get(Postman_.idRH);
			var aidPath = root.join(Letter_.aid, JoinType.LEFT).join(Aid_.postman, JoinType.LEFT).get(Postman_.idRH);

			query.orderBy(criteriaBuilder.desc(root.get(Letter_.createdAt)));
			
			return criteriaBuilder.or(criteriaBuilder.equal(certPath, idRh), criteriaBuilder.equal(aidPath, idRh));
		};
	}


}
