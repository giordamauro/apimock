package com.apimock.manager.adapter.impl;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;

import com.apimock.core.impl.ServiceMatcherImpl;
import com.apimock.core.model.MockResponse;
import com.apimock.core.model.ServiceIdentifier;
import com.apimock.manager.CustomServiceFilter;
import com.apimock.manager.ManagerMockData;
import com.apimock.manager.MockManagerContent;
import com.apimock.manager.MockRequest;
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

			ManagerMockData content = gson.fromJson(bodyContent, ManagerMockData.class);

			MockRequest identifier = content.getRequest();

			ServiceIdentifier serviceIdentifier = new ServiceIdentifier(identifier.getMethod(), identifier.getPath());
			MockResponse response = content.getResponse();
			int matcherPriority = identifier.getMatcherPriority();

			ServiceMatcherImpl matcher = new ServiceMatcherImpl(serviceIdentifier, matcherPriority);

			CustomServiceFilter customMatcher = identifier.getMatcher();
			if (customMatcher != null) {
				matcher.setCustomMatcher(customMatcher);
			}

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
