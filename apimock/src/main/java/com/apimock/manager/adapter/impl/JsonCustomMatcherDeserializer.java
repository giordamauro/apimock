package com.apimock.manager.adapter.impl;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.apimock.manager.CustomServiceFilter;
import com.apimock.manager.filter.CompositeServiceFilter;
import com.apimock.manager.filter.RequestCustomMatcher;
import com.apimock.utils.JsonCustomDeserializer;
import com.apimock.utils.SpringXmlUtils;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

public class JsonCustomMatcherDeserializer implements JsonCustomDeserializer<CustomServiceFilter> {

	// TODO desacoplar los Deserializers

	@Override
	public CustomServiceFilter deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

		JsonObject content = json.getAsJsonObject();
		String type = content.get("type").getAsString();
		JsonElement data = content.get("data");

		CustomServiceFilter customMatcher = null;

		if (type.equals("request")) {
			JsonObject requestData = data.getAsJsonObject();
			JsonElement queryParams = requestData.get("queryParams");
			JsonElement headers = requestData.get("headers");

			Type mapType = new TypeToken<Map<String, List<String>>>() {
			}.getType();
			Map<String, List<String>> queryParamsMap = context.deserialize(queryParams, mapType);
			Map<String, List<String>> headersMap = context.deserialize(headers, mapType);

			customMatcher = new RequestCustomMatcher(queryParamsMap, headersMap);

		} else if (type.equals("spring")) {

			String xmlData = data.getAsString();
			customMatcher = SpringXmlUtils.getBeanFromSpringContext(xmlData, CustomServiceFilter.class);

		} else if (type.equals("composite")) {

			customMatcher = context.deserialize(data, CompositeServiceFilter.class);

		} else {
			throw new IllegalStateException("Type not supported");
		}

		return customMatcher;
	}

	@Override
	public Class<CustomServiceFilter> getDeserializingClass() {

		return CustomServiceFilter.class;
	}

}