package fr.lta;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import fr.lta.h2db.springboot.SpringBootH2Application;
import fr.lta.h2db.springboot.daos.AidRepository;
import fr.lta.h2db.springboot.daos.CertificateRepository;
import fr.lta.h2db.springboot.daos.PostmanRepository;
import fr.lta.h2db.springboot.models.Aid;
import fr.lta.h2db.springboot.models.Certificate;
import fr.lta.h2db.springboot.models.Postman;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootH2Application.class)
class PaginationTest {

	@Autowired
	private AidRepository aidRepository;

	@Autowired
	private CertificateRepository certificateRepository;

	@Autowired
	private PostmanRepository postmanRepository;

	String[] HEADERS = { "createAt", "requestNumber" };

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() throws Exception {

		var idRH = "P8963";
		var postman = Postman.builder().idRH(idRH).build();
		postmanRepository.save(postman);
		assertEquals(1, postmanRepository.count());

		var certificate = Certificate.builder().number("AR569").postman(postman).build();
		certificate = certificateRepository.save(certificate);
		assertEquals(1, certificateRepository.count());

		int nbAids = 47;
		for (int i = 0; i < nbAids; i++) {
			aidRepository
					.save(Aid.builder().certificate(certificate).requestNumber("AR569" + i).postman(postman).build());
			Thread.sleep(100);

		}

		assertEquals(nbAids, aidRepository.count());

		var pageSize = 10;
		Pageable pageable = PageRequest.of(0, pageSize, Sort.by(Direction.DESC, "createAt"));
		Page<Aid> page = null;
		var nbLoop = 0;
		var aids = new ArrayList<Aid>();

		try (FileWriter out = new FileWriter("book_new.csv");
				CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(HEADERS).withDelimiter(';'))) {
			do {
				System.out.println("####");
				System.out.println(pageable.getPageNumber());
				page = aidRepository.findAll(pageable);
				var content = page.getContent();
				aids.addAll(content);
				for (var aid : content) {
					System.out.println(aid);
					printer.printRecord(List.of(
							Optional.ofNullable(aid).map(Aid::getCreateAt)
									.map(d -> d.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)).orElse(""),
							Optional.ofNullable(aid).map(Aid::getRequestNumber).orElse("")));
				}

				if (page.hasNext()) {
					pageable = page.nextOrLastPageable();
				}

				nbLoop++;

			} while (!Optional.ofNullable(page).map(Page::isLast).orElse(true));
//			(page != null && !page.isLast());
		}
		float a = ((float) nbAids) / ((float) pageSize);
		assertEquals(Math.round(a), nbLoop);
		assertEquals(nbAids, aids.size());
	}

}
