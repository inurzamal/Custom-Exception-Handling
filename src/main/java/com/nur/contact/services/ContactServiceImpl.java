package com.nur.contact.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nur.contact.custom.exception.EmptyInputException;
import com.nur.contact.custom.exception.NotFoundException;
import com.nur.contact.entities.Contact;
import com.nur.contact.repos.ContactRepository;


@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository repository;

	@Override
	public String upsert(Contact contact) {
		
		if(contact.getName().isBlank() || contact.getName().length()==0) {
			throw new EmptyInputException();
		}	
		repository.save(contact);
		return "Success";	
	}


	@Override
	public List<Contact> getAllContacts() {

		return repository.findAll();
	}

	@Override
	public Contact getContact(int cid) {		
		return repository.findById(cid).get();
	}
	
//	@Override
//	public Contact getContact(int cid) {	
//		Optional<Contact> findById = repository.findById(cid);		
//		if (findById.isPresent()) {
//			return findById.get();
//		}
//		return null;
//	}
	
	@Override
	public String deleteContact(int cid) {
		
		Optional<Contact> optional = repository.findById(cid);
		if(optional.isPresent()) {
			repository.deleteById(cid);
			return "Deleted successfully";
		}		
		throw new NotFoundException();
	}
	
	@Override
	public String deleteSoft(int cid) {		
		Optional<Contact> findById = repository.findById(cid);
		if(findById.isPresent()) {
			Contact contact = findById.get();
			contact.setActiveSw("N");
			repository.save(contact);
			return "Contact deleted soft";
		}
		throw new NotFoundException();
	}
}
