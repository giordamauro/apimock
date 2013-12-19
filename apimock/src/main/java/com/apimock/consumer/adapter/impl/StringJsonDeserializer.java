package com.apimock.consumer.adapter.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Type;

import com.apimock.utils.json.CustomJsonDeserializer;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class StringJsonDeserializer implements CustomJsonDeserializer<InputStream> {

	@Override
	public InputStream deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

		String data = json.getAsString();

		InputStream inputStream = getFromString(data);

		return inputStream;
	}

	@Override
	public Class<InputStream> getDeserializingClass() {
		return InputStream.class;
	}

	private InputStream getFromString(String stringContent) {

		try {
			byte[] bytes = stringContent.getBytes("UTF-8");
			InputStream stream = new ByteArrayInputStream(bytes);

			return stream;

		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

}
