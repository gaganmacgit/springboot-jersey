package com.example.jersey.springboot.resource;

import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.jersey.springboot.model.CustomDate;

@Path("date")
public class DateResource {

	public DateResource() {
	}

	/**
	 * This method takes a path parameter from client (by default coming in the
	 * string format ) in CustomDate format. For this to happen custom parameter
	 * converter and provider has to be written .
	 * 
	 * @param datePattern
	 * @return
	 */
	@GET
	@Path("/{dateContext}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getDate(@PathParam("dateContext") CustomDate datePattern) {
		return " Date received is " + datePattern;
	}

	/**
	 * This method will try to write the current date in text/plain format. By
	 * default no message writer does that , so a custom message body writer has to
	 * be defined to achieve this.
	 * 
	 * @return
	 */
	@GET
	@Path("render")
	@Produces(MediaType.TEXT_PLAIN)
	public Date writeDate() {
		return Calendar.getInstance().getTime();

	}
}
