package com.nur.contact.services;

import java.util.List;
import com.nur.contact.entities.Contact;


public interface ContactService {
	
	String upsert(Contact contact);
	
	List<Contact> getAllContacts();
	
	Contact getContact(int cid);
	
	String deleteContact(int cid);
	
	String deleteSoft(int cid);

}
