package com.apimock.utils;

import java.util.List;

import org.springframework.beans.factory.FactoryBean;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonFactoryBean implements FactoryBean<Gson> {

	private final GsonBuilder gs;

	private final List<JsonCustomDeserializer<?>> deserializers;

	public GsonFactoryBean(GsonBuilder gs, List<JsonCustomDeserializer<?>> deserializers) {
		this.gs = gs;
		this.deserializers = deserializers;
	}

	@Override
	public Gson getObject() throws Exception {

		if (deserializers != null) {
			for (JsonCustomDeserializer<?> deserializer : deserializers) {

				gs.registerTypeAdapter(deserializer.getDeserializingClass(), deserializer);
			}
		}
		return gs.create();
	}

	@Override
	public Class<?> getObjectType() {
		return Gson.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
