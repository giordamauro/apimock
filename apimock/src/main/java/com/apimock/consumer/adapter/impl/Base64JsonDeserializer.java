package com.apimock.consumer.adapter.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Type;

import org.apache.commons.codec.binary.Base64;

import com.apimock.utils.json.CustomJsonDeserializer;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class Base64JsonDeserializer implements CustomJsonDeserializer<InputStream> {

	@Override
	public InputStream deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

		String data = json.getAsString();

		InputStream inputStream = getFromBase64(data);

		return inputStream;
	}

	@Override
	public Class<InputStream> getDeserializingClass() {
		return InputStream.class;
	}

	private InputStream getFromBase64(String base64String) {

		byte[] decodedBytes = Base64.decodeBase64(base64String);
		InputStream stream = new ByteArrayInputStream(decodedBytes);

		return stream;
	}

}
