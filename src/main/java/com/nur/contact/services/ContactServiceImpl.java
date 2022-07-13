package com.nur.contact.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nur.contact.custom.exception.BusinessException;
import com.nur.contact.entities.Contact;
import com.nur.contact.repos.ContactRepository;


@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository repository;

	@Override
	public String upsert(Contact contact) {
		
		if(contact.getName().isBlank() || contact.getName().length()==0) {
			throw new BusinessException("701", "Name is Blank");
		}	
		try {			
			repository.save(contact);
			return "Success";			
		} catch (IllegalArgumentException e) {
			throw new BusinessException("702", "given contact is empty" +e.getMessage());
		}catch (Exception e) {
			throw new BusinessException("703", "Something went wrong in service layer while saving data" +e.getMessage());
		}
	}

	@Override
	public List<Contact> getAllContacts() {				
		try {
			List<Contact> contacts = repository.findAll();
			if(contacts.isEmpty()) 
				throw new BusinessException("704", "Contact List is Empty");		
			return contacts;
			
		} catch (Exception e) {
			throw new BusinessException("705", "Something went wrong in service layer while fetching all contacts" +e.getMessage());
		}
	}

	@Override
	public Contact getContact(int cid) {
		try {
			Optional<Contact> findById = repository.findById(cid);
			if(findById.isPresent()) {
				return findById.get();
			}
			throw new BusinessException("707", "contact does not exit with this id");
		} 
		catch (IllegalArgumentException e) {
			throw new BusinessException("701", "given id is null "+e.getMessage());
		} 
	}

	@Override
	public String deleteContact(int cid) {			
		try {			
			Optional<Contact> id = repository.findById(cid);
			if(id.isPresent())
				repository.deleteById(cid);
			throw new BusinessException("707", "Id does noe exist");
			
		} catch (IllegalArgumentException e) {
			throw new BusinessException("701", "Id is null, Please send Id" + e.getMessage());
		}
	}
	
	@Override
	public String deleteSoft(int cid) {		
		Optional<Contact> findById = repository.findById(cid);
		if(findById.isPresent()) {
			Contact contact = findById.get();
			contact.setActiveSw("N");
			repository.save(contact);
		}
		return "Contact deleted soft";
	}
}
