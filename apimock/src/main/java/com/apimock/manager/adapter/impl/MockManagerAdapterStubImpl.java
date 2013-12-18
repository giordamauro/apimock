package com.apimock.manager.adapter.impl;

import java.io.BufferedReader;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.apimock.core.impl.RequestServiceMatcher;
import com.apimock.core.model.MockResponse;
import com.apimock.core.model.ServiceIdentifier;
import com.apimock.core.model.ServiceMatcher;
import com.apimock.manager.MockManagerContent;
import com.apimock.manager.adapter.MockManagerAdapter;
import com.google.gson.Gson;

public class MockManagerAdapterStubImpl implements MockManagerAdapter {

	private final Gson gson;

	public MockManagerAdapterStubImpl(Gson gson) {
		this.gson = gson;
	}

	@Override
	public MockManagerContent getMockContent(HttpServletRequest request) {

		MockManagerContent managerContent = null;

		if (request.getContentType().equals("application/json")) {

			String bodyContent = getBodyFromRequest(request);

			ManagerContent content = gson.fromJson(bodyContent, ManagerContent.class);

			MatcherIdentifier identifier = content.getIdentifier();

			ServiceIdentifier serviceIdentifier = new ServiceIdentifier(identifier.getMethod(), identifier.getPath());
			MockResponse response = content.getResponse();
			int matcherPriority = identifier.getMatcherPriority();

			RequestServiceMatcher.Builder builder = new RequestServiceMatcher.Builder(serviceIdentifier, matcherPriority);
			Map<String, List<String>> queryParams = identifier.getQueryParams();
			if (queryParams != null) {
				builder.addQueryParams(queryParams);
			}

			Map<String, List<String>> headers = identifier.getHeaders();
			if (headers != null) {
				builder.addHeaders(headers);
			}

			ServiceMatcher matcher = builder.build();

			managerContent = new MockManagerContent(matcher, response);

		} else {
			throw new UnsupportedOperationException("Content not supported yet");
		}

		return managerContent;
	}

	private String getBodyFromRequest(HttpServletRequest request) {

		StringBuilder data = new StringBuilder();
		try {
			BufferedReader reader = request.getReader();
			char[] buf = new char[1024];
			int numRead = 0;
			while ((numRead = reader.read(buf)) != -1) {
				String readData = String.valueOf(buf, 0, numRead);
				data.append(readData);
			}
			reader.close();
			return data.toString();

		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
}
