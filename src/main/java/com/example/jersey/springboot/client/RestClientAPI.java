package com.example.jersey.springboot.client;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import com.example.jersey.springboot.model.Message;

public class RestClientAPI {

	static final String BASE_TARGET_URL = "http://localhost:9090/api";

	public static void main(String[] args) {
		new RestClientAPI();
	}

	public RestClientAPI() {

		Client client = ClientBuilder.newClient();

		WebTarget baseTargetURL = client.target(BASE_TARGET_URL);
		WebTarget messageTargetPath = baseTargetURL.path("messages");

		WebTarget messageIdPath = messageTargetPath.path("{messageId}");

		// GET Message
		Message message = messageIdPath.resolveTemplate("messageId", "2").request().get(Message.class);
		System.out.println(message.getMessage());

		// POST Message
		Message postMessage = new Message(5, "Learning ReactJS with Redux", "Udemy");
//		System.out.println(" printing JSON that will be sent to server " + Entity.json(postMessage));
		Response postResponse = messageTargetPath.request().post(Entity.json(postMessage));
		Message readMessageEntity = postResponse.readEntity(Message.class);
		System.out.println("Response message text =>" + readMessageEntity.getMessage());

		// GET List of Messages
		List<Message> messagesList = messageTargetPath.queryParam("year", 2018).request()
				.get(new GenericType<List<Message>>() {
				});

		Optional.ofNullable(messagesList).get().stream().forEach((msg) -> System.out.println(msg));
	}
}