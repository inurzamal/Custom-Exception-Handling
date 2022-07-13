package com.nur.contact.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nur.contact.custom.exception.BusinessException;
import com.nur.contact.custom.exception.ControllerException;
import com.nur.contact.entities.Contact;
import com.nur.contact.services.ContactService;

@RestController
public class ContactRestController {
	
	@Autowired
	private ContactService service;
	
	@PostMapping("/contact")
	public ResponseEntity<?> contact(@RequestBody Contact contact){
		try {
			String status = service.upsert(contact);
			return new ResponseEntity<>	(status, HttpStatus.CREATED);
		} 
		catch (BusinessException e) {			
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {			
			ControllerException ce = new ControllerException("709", "Something wrong in Controller");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_GATEWAY);
		}
	}
	
	@GetMapping("/contacts")
	public ResponseEntity<?> getAllContacts(){
		try {
			
			List<Contact> allContacts = service.getAllContacts();		
			return new ResponseEntity<List<Contact>>(allContacts,HttpStatus.OK);	
			
		} 
		catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {			
			ControllerException ce = new ControllerException("709", "Something wrong in Controller");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_GATEWAY);
		}
	}
	
	@GetMapping("/contact/{cid}")
	public ResponseEntity<?> getContact(@PathVariable int cid){
		
		try {			
			Contact contact = service.getContact(cid);			
			return new ResponseEntity<Contact>(contact,HttpStatus.OK);				
		} 
		catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {			
			ControllerException ce = new ControllerException("710", "Something wrong in Controller while fetching by id");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_GATEWAY);
		}
	}
	
	
	@DeleteMapping("/contact/{cid}")
	public ResponseEntity<?> deleteContact(@PathVariable int cid){
		try {
			
			String deleteContact = service.deleteContact(cid);
			return new ResponseEntity<>(deleteContact,HttpStatus.OK);
			
		} catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce,HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			ControllerException ce = new ControllerException("709", "Something wrong in Controller");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_GATEWAY);
		}
	}
	
	@DeleteMapping("/softdel/{cid}")
	public ResponseEntity<String> deleteSoft(@PathVariable int cid){
		String deleteSoft = service.deleteSoft(cid);
		return new ResponseEntity<>(deleteSoft, HttpStatus.OK);
		
	}

}
