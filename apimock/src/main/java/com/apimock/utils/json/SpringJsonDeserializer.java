package com.apimock.utils.json;

import java.lang.reflect.Type;

import com.apimock.utils.SpringXmlUtils;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class SpringJsonDeserializer<T> implements CustomJsonDeserializer<T> {

	private final Class<T> deserializingClass;

	public SpringJsonDeserializer(Class<T> deserializingClass) {
		this.deserializingClass = deserializingClass;
	}

	@Override
	public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

		String xmlData = json.getAsString();
		T object = SpringXmlUtils.getBeanFromSpringContext(xmlData, deserializingClass);

		return object;
	}

	@Override
	public Class<T> getDeserializingClass() {
		return deserializingClass;
	}

}
