package com.citizen.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Person {

	@Id
	@GeneratedValue
	private int id;

	private String firstName;
	private String surname;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String postCode;
	private String countryCode;
	private String gender;
	private Date dateOfBirth;

}
