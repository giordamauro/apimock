package com.apimock.core.model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockResponse {

	private int statusCode = 200;

	private String reasonPhrase;

	private Map<String, List<String>> headers = new HashMap<String, List<String>>();

	private InputStream content;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getReasonPhrase() {
		return reasonPhrase;
	}

	public void setReasonPhrase(String reasonPhrase) {
		this.reasonPhrase = reasonPhrase;
	}

	public Map<String, List<String>> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, List<String>> headers) {
		this.headers = headers;
	}

	public void setHeaderValues(String headerName, List<String> values) {
		headers.put(headerName, values);
	}

	public void addHeaderValue(String headerName, String value) {

		List<String> headerValues = headers.get(headerName);
		if (headerValues == null) {
			headerValues = new ArrayList<String>();
			headers.put(headerName, headerValues);
		}

		headerValues.add(value);
	}

	public InputStream getContent() {
		return content;
	}

	public void setContent(InputStream content) {
		this.content = content;
	}

	public void setContent(String stringContent) {

		try {
			byte[] bytes = stringContent.getBytes("UTF-8");
			InputStream stream = new ByteArrayInputStream(bytes);

			this.content = stream;

		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
}
