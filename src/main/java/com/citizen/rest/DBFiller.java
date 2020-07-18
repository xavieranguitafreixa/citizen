package com.citizen.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import com.citizen.model.Person;
import com.citizen.model.PersonRepository;

@Component
public class DBFiller {

	private static final String TEST_FILE = "persons_from.csv";

	final PersonRepository personRepository;

	@Autowired
	public DBFiller(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@PostConstruct
	public void fillFields() throws IOException {

		final String DELIMITER = ",";

		ClassLoader cl = this.getClass().getClassLoader();
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);
		Resource[] resources = resolver.getResources("classpath*:/" + TEST_FILE);
		for (Resource resource : resources) {
			try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {

				String line = "";
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				int id = 0;
				Date date = null;
				while ((line = fileReader.readLine()) != null) {
					String[] tokens = line.split(DELIMITER);
					try {
						date = formatter.parse(tokens[9]);
					} catch (Exception e) {

					}
					Person person = new Person(id, tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5],
							tokens[6], tokens[7], tokens[8], date);
					personRepository.save(person);
					id++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}