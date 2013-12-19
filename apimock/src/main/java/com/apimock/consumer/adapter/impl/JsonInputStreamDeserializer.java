package com.apimock.consumer.adapter.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Type;

import org.apache.commons.codec.binary.Base64;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class JsonInputStreamDeserializer implements JsonDeserializer<InputStream> {

	@Override
	public InputStream deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

		JsonObject content = json.getAsJsonObject();
		String type = content.get("type").getAsString();
		String data = content.get("data").getAsString();

		InputStream inputStream = null;

		if (type.equals("plain")) {

			inputStream = getFromString(data);

		} else if (type.equals("base64")) {

			inputStream = getFromBase64(data);
		} else {

			throw new IllegalStateException(String.format("The type '%s' is not supported", type));
		}

		return inputStream;
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

	private InputStream getFromBase64(String base64String) {

		byte[] decodedBytes = Base64.decodeBase64(base64String);
		InputStream stream = new ByteArrayInputStream(decodedBytes);

		return stream;
	}
}