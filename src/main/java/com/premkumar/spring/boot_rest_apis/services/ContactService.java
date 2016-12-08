package com.premkumar.spring.boot_rest_apis.services;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.premkumar.spring.boot_rest_apis.repository.ContactRepository;

@Path(value = "/contacts")
@Api(value = "Contacts Service apis")
public class ContactService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ContactService.class);

	@Autowired
	private ContactRepository contactRepository;

	@PersistenceContext
	private EntityManager em;

	@GET
	@Path(value = "/{id}")
	@Produces(value = MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get contact by id")
	public Contact getContact(@PathParam("id") int id) {
		LOGGER.info("get contact by id api called");
		com.premkumar.spring.boot_rest_apis.entities.Contact e = contactRepository
				.findOne(id);
		return e == null ? null : new Contact(e.getName(), e.getId());
	}

	@GET
	@Path(value = "/")
	@Produces(value = MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get contacts")
	public Contact[] getContacts() {
		LOGGER.info(LOGGER.getClass().toString());
		LOGGER.info("get contacts api called");
		Iterator<com.premkumar.spring.boot_rest_apis.entities.Contact> contacts = contactRepository.findAll().iterator();
		if(contacts==null || !contacts.hasNext())
			return new Contact[]{};
		
		List<Contact> contactDTOs = new ArrayList<Contact>();
		while(contacts.hasNext()){
			com.premkumar.spring.boot_rest_apis.entities.Contact c = contacts.next();
			contactDTOs.add(new Contact(c.getName(), c.getId()));
		}
		Contact[] contactArr = new Contact[contactDTOs.size()];
		return contactDTOs.toArray(contactArr);
	}

	@POST
	@Path(value = "/")
	@Produces(value = MediaType.APPLICATION_JSON)
	@ApiOperation(value = "add a new contact")
	@Transactional
	public Contact addContact(Contact contact) {
		LOGGER.info("add contact api called");
		com.premkumar.spring.boot_rest_apis.entities.Contact entity = new com.premkumar.spring.boot_rest_apis.entities.Contact();
		entity.setName(contact.getName());
		em.persist(entity);
		return new Contact(entity.getName(), entity.getId());
	}

	public static class Contact {
		private String name;
		private int id;

		public Contact() {

		}

		public Contact(String name, int id) {
			this.setName(name);
			this.setId(id);
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
	}
}
