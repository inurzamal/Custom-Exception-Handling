package com.nur.contact.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Contact {
	
	@Id
	@GeneratedValue
	private int cid;
	private String name;
	private String email;
	private Long phno;
	private String activeSw;

}
