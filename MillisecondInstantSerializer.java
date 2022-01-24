package com.customserializer;

import java.time.Instant;
import java.time.format.DateTimeFormatterBuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;

/**
 * 
 * 
 *  Custom Serializer to maintain milliseconds in date format.
 *  As per ISO-8601 date can be any format 
 *  uuuu-MM-dd'T'HH:mm
 *  uuuu-MM-dd'T'HH:mm:ss
*   uuuu-MM-dd'T'HH:mm:ss.SSS
*   uuuu-MM-dd'T'HH:mm:ss.SSSSSS
*   uuuu-MM-dd'T'HH:mm:ss.SSSSSSSSS
*   e.g if date = 2022-01-24T12:00:00.000+00:00 , output from date instant class would be 2022-01-24T12:00
*   if date = 2022-01-27T20:06:48.000+00:00, output would be 2022-01-27T20:06:48
*   Below Serializer takes care of formatting date to format yyyy-MM-dd'T'HH:mm:ss.SSS and appending 3digit milliseconds if all zero.
 *
 */
@Configuration
public class MillisecondInstantSerializer extends InstantSerializer {

	private static final long serialVersionUID = -8707235425066914453L;

	public MillisecondInstantSerializer() {
		super(InstantSerializer.INSTANCE, false, new DateTimeFormatterBuilder().appendInstant(3).toFormatter());
	}

	@Bean
	@Primary
	public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
		ObjectMapper objectMapper = builder.build();
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		final JavaTimeModule javaTimeModule = new JavaTimeModule();
		objectMapper.registerModule(javaTimeModule);
		javaTimeModule.addSerializer(Instant.class, new MillisecondInstantSerializer());
		return objectMapper;
	}

}
