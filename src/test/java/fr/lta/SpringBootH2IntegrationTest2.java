package fr.lta;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import fr.lta.h2db.springboot.SpringBootH2Application;
import fr.lta.h2db.springboot.daos.AidRepository;
import fr.lta.h2db.springboot.daos.CertificateRepository;
import fr.lta.h2db.springboot.daos.LetterRepository;
import fr.lta.h2db.springboot.daos.PostmanRepository;
import fr.lta.h2db.springboot.models.Aid;
import fr.lta.h2db.springboot.models.Certificate;
import fr.lta.h2db.springboot.models.Letter;
import fr.lta.h2db.springboot.models.LetterCode;
import fr.lta.h2db.springboot.models.Postman;
import fr.lta.h2db.springboot.specifications.LetterSpecifications;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootH2Application.class)
public class SpringBootH2IntegrationTest2 {

	@Autowired
	private PostmanRepository postmanRepository;

	@Autowired
	private CertificateRepository certificateRepository;

	@Autowired
	private AidRepository aidRepository;

	@Autowired
	private LetterRepository letterRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void find_letters_with_id_rh() {

		var idRH = "P8963";
		{
			var postman = Postman.builder().idRH(idRH).build();
			postmanRepository.save(postman);
			assertEquals(1, postmanRepository.count());

			var certificate = Certificate.builder().number("AR569").postman(postman).build();
			certificateRepository.save(certificate);
			assertEquals(1, certificateRepository.count());

			var aid = Aid.builder().requestNumber("AR569").postman(postman).build();
			aidRepository.save(aid);
			assertEquals(1, aidRepository.count());

			{
				// create a letter for the certificate
				var letter = Letter.builder().createdAt(LocalDateTime.now()).code(LetterCode.AAA).aid(aid).build();
				letterRepository.save(letter);
				assertEquals(1, letterRepository.count());
			}
			{
				// create a letter for the certificate
				var letter = Letter.builder().createdAt(LocalDateTime.now().minus(Duration.ofDays(5)))
						.code(LetterCode.BBB).certificate(certificate).build();
				letterRepository.save(letter);
				assertEquals(2, letterRepository.count());
			}
		}

		{
			var postman = Postman.builder().idRH("002").build();
			postmanRepository.save(postman);
			assertEquals(2, postmanRepository.count());

			var certificate = Certificate.builder().number("XXX89").postman(postman).build();
			certificateRepository.save(certificate);
			assertEquals(2, certificateRepository.count());
			// create a letter for an other certificate
			var letter = Letter.builder().createdAt(LocalDateTime.now().minus(Duration.ofDays(5)))
					.certificate(certificate).code(LetterCode.BBB).build();
			letterRepository.save(letter);
			assertEquals(3, letterRepository.count());
		}

		var lettersFilter = letterRepository.findAll(LetterSpecifications.idRH(idRH).and(LetterSpecifications.orderByMostRecent()),
		// sort in the specification
//				PageRequest.of(0, 20, Sort.by("createdAt").descending()));
				PageRequest.of(0, 20));
		assertEquals(2, lettersFilter.getTotalElements());

		var letters = lettersFilter.stream().collect(Collectors.toList());

		assertEquals(LetterCode.AAA, letters.get(0).getCode());
		// the more recent letter is first in the stream
		assertTrue(letters.get(0).getCreatedAt().isAfter(letters.get(1).getCreatedAt()));

		// convert pageable parameter in pagerequest
//		Pageable p = null;
//		PageRequest.of(p.getPageNumber(), p.getPageSize());
	}

}
