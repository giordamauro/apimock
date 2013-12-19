package com.apimock.utils;

import com.google.gson.JsonDeserializer;

public interface JsonCustomDeserializer<T> extends JsonDeserializer<T> {

	Class<T> getDeserializingClass();

}
