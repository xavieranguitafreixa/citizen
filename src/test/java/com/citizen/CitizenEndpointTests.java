package com.citizen;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.citizen.model.Person;

public class CitizenEndpointTests {
	private static final String uri = "http://localhost:8080/persons";

	@Test
	public void getTest() {
		RestTemplate template = new RestTemplate();
		String response = template.getForObject(uri, String.class);
		assertNotNull(response);
	}

	@Test
	public void postTest() {
		Map<String, String> params = new HashMap<>();
		Person person = new Person(3, "aaaa", "aaaa", "aaaa", "aaaa", "aaaa", "aaaa", "aaaa", "aaaa", "aaaa", null);

		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.postForObject(uri, person, String.class);

		assertNotNull(response);
	}
}