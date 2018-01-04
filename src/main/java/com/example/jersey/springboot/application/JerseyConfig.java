package com.example.jersey.springboot.application;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.example.jersey.springboot.messagebodywriters.CustomDateMessageBodyWriter;
import com.example.jersey.springboot.providers.CustomDateConverterProvider;
import com.example.jersey.springboot.resource.CommentResource;
import com.example.jersey.springboot.resource.DateResource;
import com.example.jersey.springboot.resource.MessageResource;
import com.example.jersey.springboot.resource.ProfileResource;
import com.example.jersey.springboot.resource.UserResource;

@Component
@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(UserResource.class);
		register(DateResource.class);
		register(MultiPartFeature.class);
		register(MessageResource.class);
		register(CommentResource.class);
		register(ProfileResource.class);
		register(LoggingFeature.class);
		register(CustomDateConverterProvider.class);
		register(CustomDateMessageBodyWriter.class);

		register(new LoggingFeature(Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME), Level.SEVERE,
				LoggingFeature.Verbosity.PAYLOAD_ANY, Integer.MAX_VALUE));
		property("jersey.config.server.tracing.type", "ALL");
		property("jersey.config.server.tracing.threshold", "TRACE");
	}
}