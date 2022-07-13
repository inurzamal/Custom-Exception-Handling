package com.nur.contact.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nur.contact.entities.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
