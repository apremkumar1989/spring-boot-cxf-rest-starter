package com.premkumar.spring.boot_rest_apis.services;

import io.swagger.annotations.Api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
@Api(value = "hello Service impl apis")
public class HelloServiceImpl implements HelloService {

	public String sayHello(String a) {
		return "Hello " + a;
	}

	@GET
	@Path("/{a}/json")
	@Produces(MediaType.APPLICATION_JSON)
	public String sayHelloInJson(@PathParam("a") String a) {
		return "{\"msg:\"Hello " + a + "\"}";
	}

}
