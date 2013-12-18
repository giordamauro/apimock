package com.apimock.core.model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceParameters {

	public static class Builder {

		private final ServiceIdentifier identifier;

		private final Map<String, List<String>> queryParams = new HashMap<String, List<String>>();

		private final Map<String, List<String>> headers = new HashMap<String, List<String>>();

		private InputStream payload;

		public Builder(ServiceIdentifier identifier) {
			this.identifier = identifier;
		}

		public Builder(HttpMethod httpMethod, String path) {
			this.identifier = new ServiceIdentifier(httpMethod, path);
		}

		public void addQueryParams(Map<String, List<String>> params) {
			this.queryParams.putAll(params);
		}

		public void addQueryParam(String param, List<String> values) {
			this.queryParams.put(param, values);
		}

		public void addQueryParam(String param, String value) {
			List<String> values = this.queryParams.get(param);
			if (values == null) {
				values = new ArrayList<String>();
				this.queryParams.put(param, values);
			}
			values.add(value);
		}

		public void addHeaders(Map<String, List<String>> headers) {
			this.headers.putAll(headers);
		}

		public void addHeader(String header, List<String> values) {
			this.headers.put(header, values);
		}

		public void addHeader(String header, String value) {
			List<String> values = this.headers.get(header);
			if (values == null) {
				values = new ArrayList<String>();
				this.headers.put(header, values);
			}
			values.add(value);
		}

		public void setPayload(InputStream payload) {
			this.payload = payload;
		}

		public ServiceParameters build() {
			ServiceParameters parameters = new ServiceParameters(this);
			return parameters;
		}
	}

	private final ServiceIdentifier identifier;

	private final Map<String, List<String>> queryParams;

	private final Map<String, List<String>> headers;

	private final InputStream payload;

	private ServiceParameters(Builder builder) {

		this.identifier = builder.identifier;
		this.queryParams = builder.queryParams;
		this.headers = builder.headers;
		this.payload = builder.payload;
	}

	public ServiceIdentifier getIdentifier() {
		return identifier;
	}

	public Map<String, List<String>> getQueryParams() {
		return queryParams;
	}

	public Map<String, List<String>> getHeaders() {
		return headers;
	}

	public InputStream getPayload() {
		return payload;
	}
}
