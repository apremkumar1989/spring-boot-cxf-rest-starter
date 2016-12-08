package com.premkumar.spring.boot_rest_apis.repository;

import org.springframework.data.repository.CrudRepository;

import com.premkumar.spring.boot_rest_apis.entities.Contact;

public interface ContactRepository extends CrudRepository<Contact, Integer>{

}
