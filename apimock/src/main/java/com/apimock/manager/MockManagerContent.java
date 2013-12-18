package com.apimock.manager;

import com.apimock.core.model.MockResponse;
import com.apimock.core.model.ServiceMatcher;

public class MockManagerContent {

	private final ServiceMatcher matcher;

	private final MockResponse response;

	public MockManagerContent(ServiceMatcher matcher, MockResponse response) {

		this.matcher = matcher;
		this.response = response;
	}

	public ServiceMatcher getMatcher() {
		return matcher;
	}

	public MockResponse getResponse() {
		return response;
	}
}
