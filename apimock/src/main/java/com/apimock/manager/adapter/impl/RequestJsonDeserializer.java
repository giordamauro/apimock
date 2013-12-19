package com.apimock.manager.adapter.impl;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.apimock.manager.CustomServiceFilter;
import com.apimock.manager.filter.RequestCustomMatcher;
import com.apimock.utils.json.CustomJsonDeserializer;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

public class RequestJsonDeserializer implements CustomJsonDeserializer<CustomServiceFilter> {

	@Override
	public CustomServiceFilter deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

		JsonObject requestData = json.getAsJsonObject();
		JsonElement queryParams = requestData.get("queryParams");
		JsonElement headers = requestData.get("headers");

		Type mapType = new TypeToken<Map<String, List<String>>>() {
		}.getType();
		Map<String, List<String>> queryParamsMap = context.deserialize(queryParams, mapType);
		Map<String, List<String>> headersMap = context.deserialize(headers, mapType);

		CustomServiceFilter customMatcher = new RequestCustomMatcher(queryParamsMap, headersMap);

		return customMatcher;
	}

	@Override
	public Class<CustomServiceFilter> getDeserializingClass() {
		return CustomServiceFilter.class;
	}

}
