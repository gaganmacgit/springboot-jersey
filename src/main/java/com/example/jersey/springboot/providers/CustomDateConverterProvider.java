package com.example.jersey.springboot.providers;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Calendar;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

import com.example.jersey.springboot.model.CustomDate;

@Provider
public class CustomDateConverterProvider implements ParamConverterProvider {

	public CustomDateConverterProvider() {
	}

	@Override
	public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
		System.out.println(" converter received is " + rawType);
		System.out.println(" annotations received are " + Arrays.deepToString(annotations));

		if (rawType.getName().equals(CustomDate.class.getName())) {
			return new ParamConverter<T>() {

				@Override
				public T fromString(String value) {
					Calendar calendar = Calendar.getInstance();
					CustomDate customDate = new CustomDate();
					customDate.setDay(calendar.get(Calendar.DATE));
					customDate.setMonth(calendar.get(Calendar.MONTH));
					customDate.setYear(calendar.get(Calendar.YEAR));
					return rawType.cast(customDate);
				}

				@Override
				public String toString(T value) {
					return value != null ? value.toString() : null;
				}
			};
		}

		return null;
	}

}
