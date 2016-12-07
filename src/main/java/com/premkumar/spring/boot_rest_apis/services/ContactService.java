package com.premkumar.spring.boot_rest_apis.services;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
import org.springframework.transaction.annotation.Transactional;

@Path(value = "/contacts")
@Api(value = "Contacts Service apis")
public class ContactService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ContactService.class);

	public ContactService() {
		LOGGER.info("once ----------------------------------------------------====================");
	}
	
	@PersistenceContext
	private EntityManager em;

	@GET
	@Path(value = "/{id}")
	@Produces(value = MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get contact by id")
	public Contact getContact(@PathParam("id") int id) {
		LOGGER.info("get contact by id api called");
		return new Contact("dummy" + id, id);
	}

	@GET
	@Path(value = "/")
	@Produces(value = MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get contacts")
	public Contact[] getContacts() {
		LOGGER.info(LOGGER.getClass().toString());
		LOGGER.info("get contacts api called");
		return new Contact[] { new Contact("dummy" + 1, 1),
				new Contact("dummy" + 2, 2) };
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
		return new Contact("dummy" + entity.getId(), entity.getId());
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
