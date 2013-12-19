package com.apimock.manager.adapter.impl;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.apimock.manager.CustomServiceFilter;
import com.apimock.manager.filter.CompositeServiceMatcher;
import com.apimock.manager.filter.RequestCustomMatcher;
import com.apimock.utils.SpringXmlUtils;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

public class JsonCustomMatcherDeserializer implements JsonDeserializer<CustomServiceFilter> {

	private Gson gson;

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
			Map<String, List<String>> queryParamsMap = gson.fromJson(queryParams, mapType);
			Map<String, List<String>> headersMap = gson.fromJson(headers, mapType);

			customMatcher = new RequestCustomMatcher(queryParamsMap, headersMap);

		} else if (type.equals("spring")) {

			String xmlData = data.getAsString();
			customMatcher = SpringXmlUtils.getBeanFromSpringContext(xmlData, CustomServiceFilter.class);

		} else if (type.equals("composite")) {

			customMatcher = gson.fromJson(data, CompositeServiceMatcher.class);

		} else {
			throw new IllegalStateException("Type not supported");
		}

		return customMatcher;
	}

	public void setGson(Gson gson) {
		this.gson = gson;
	}

}