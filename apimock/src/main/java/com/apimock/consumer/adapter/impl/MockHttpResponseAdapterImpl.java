package com.apimock.consumer.adapter.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import com.apimock.consumer.adapter.MockHttpResponseAdapter;
import com.apimock.core.model.MockResponse;

public class MockHttpResponseAdapterImpl implements MockHttpResponseAdapter {

	@SuppressWarnings("deprecation")
	@Override
	public void sendMockResponse(MockResponse mockResponse, HttpServletResponse response) {

		int statusCode = mockResponse.getStatusCode();
		String reasonPhrase = mockResponse.getReasonPhrase();
		if (reasonPhrase == null) {
			response.setStatus(statusCode);
		} else {
			response.setStatus(statusCode, reasonPhrase);
		}

		Map<String, List<String>> headers = mockResponse.getHeaders();
		setResponseHeaders(headers, response);

		InputStream inputStreamContent = mockResponse.getContent();
		setResponsePayload(inputStreamContent, response);

	}

	private void setResponsePayload(InputStream in, HttpServletResponse response) {

		try {
			OutputStream out = response.getOutputStream();

			byte[] buffer = new byte[4096];
			int read = 0;

			while ((read = in.read(buffer)) != -1) {
				out.write(buffer, 0, read);
			}
			in.reset();

		} catch (IOException e) {

			throw new IllegalStateException(e);
		}
	}

	private void setResponseHeaders(Map<String, List<String>> headers, HttpServletResponse response) {

		for (Entry<String, List<String>> entry : headers.entrySet()) {
			String headerName = entry.getKey();
			List<String> values = entry.getValue();

			String completeValue = "";
			for (String value : values) {
				completeValue += value + ";";
			}
			if (!values.isEmpty()) {
				completeValue = completeValue.substring(0, completeValue.length() - 1);
			}
			response.setHeader(headerName, completeValue);
		}
	}

}
