package com.apimock.consumer.adapter.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.apimock.consumer.adapter.ServiceParametersAdapter;
import com.apimock.core.model.HttpMethod;
import com.apimock.core.model.ServiceParameters;

public class ServiceParametersAdapterImpl implements ServiceParametersAdapter {

	@Override
	public ServiceParameters buildServiceParameters(HttpServletRequest request) {

		String method = request.getMethod();
		String path = request.getPathInfo();

		HttpMethod httpMethod = HttpMethod.valueOf(method);
		ServiceParameters.Builder builder = new ServiceParameters.Builder(httpMethod, path);

		String queryString = request.getQueryString();
		if (queryString != null) {
			Map<String, List<String>> queryParams = getQueryParameters(queryString);
			builder.addQueryParams(queryParams);
		}

		Map<String, List<String>> headers = getHeaders(request);
		builder.addHeaders(headers);

		try {
			InputStream payload = request.getInputStream();
			builder.setPayload(payload);
		} catch (Exception ex) {
			throw new IllegalStateException(ex);
		}

		ServiceParameters mockIdentifier = builder.build();

		return mockIdentifier;
	}

	private Map<String, List<String>> getQueryParameters(String queryString) {
		Map<String, List<String>> queryParams = new HashMap<String, List<String>>();

		String[] params = queryString.split("&");
		for (String param : params) {
			String[] split = param.split("=");
			if (split.length > 1) {
				String key = split[0];
				String value = split[1];

				addQueryParam(queryParams, key, value);
			}
		}
		return queryParams;
	}

	private void addQueryParam(Map<String, List<String>> queryParams, String param, String value) {
		List<String> values = queryParams.get(param);
		if (values == null) {
			values = new ArrayList<String>();
			queryParams.put(param, values);
		}
		values.add(value);
	}

	private Map<String, List<String>> getHeaders(HttpServletRequest request) {

		Map<String, List<String>> headers = new HashMap<String, List<String>>();

		@SuppressWarnings("unchecked")
		Enumeration<String> headerNames = (Enumeration<String>) request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			String value = request.getHeader(headerName);

			String[] values = value.split(";");
			List<String> listValues = Arrays.asList(values);

			headers.put(headerName, listValues);
		}
		return headers;
	}

}
