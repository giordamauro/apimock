package com.apimock.utils.json;

import com.google.gson.JsonDeserializer;

public interface CustomJsonDeserializer<T> extends JsonDeserializer<T> {

	Class<T> getDeserializingClass();

}
