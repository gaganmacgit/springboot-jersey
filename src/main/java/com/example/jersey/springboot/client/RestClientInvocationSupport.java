package com.example.jersey.springboot.client;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.jersey.springboot.model.Message;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class RestClientInvocationSupport {
	static final String BASE_TARGET_URL = "http://localhost:9090/api";

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		Invocation restClientInvocationSupport = new RestClientInvocationSupport().getRestClientInvocationSupport();
		Response webserviceResponse = restClientInvocationSupport.invoke();

		// 1. Response can be extracted in String format and then using object mapper
		// cast into real objects
		// String responseEntity = webserviceResponse.readEntity(String.class);
		// ObjectMapper objectMapper = new ObjectMapper();
		// List<Message> messageLists = objectMapper.readValue(responseEntity, new
		// TypeReference<List<Message>>() {
		// });

		// 2. using Generic type
		List<Message> messageLists = webserviceResponse.readEntity(new GenericType<List<Message>>() {
		});
		messageLists.stream().forEach((message) -> System.out.println(message));
	}

	/**
	 * Method creates the invoker, which is ready to be used.
	 * @return
	 */
	public Invocation getRestClientInvocationSupport() {

		Client client = ClientBuilder.newClient();
		return client.target(BASE_TARGET_URL).path("messages").queryParam("year", 2018)
				.request(MediaType.APPLICATION_JSON).buildGet();
	}

}