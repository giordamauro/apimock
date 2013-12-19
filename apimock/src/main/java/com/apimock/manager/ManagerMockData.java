package com.apimock.manager;

import com.apimock.core.model.MockResponse;

public class ManagerMockData {

	private MockRequest request;

	private MockResponse response;

	public MockRequest getRequest() {
		return request;
	}

	public void setRequest(MockRequest request) {
		this.request = request;
	}

	public MockResponse getResponse() {
		return response;
	}

	public void setResponse(MockResponse response) {
		this.response = response;
	}
}
