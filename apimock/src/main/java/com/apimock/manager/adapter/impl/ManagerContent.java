package com.apimock.manager.adapter.impl;

import com.apimock.core.model.MockResponse;

public class ManagerContent {

	private MatcherIdentifier identifier;

	private MockResponse response;

	public MatcherIdentifier getIdentifier() {
		return identifier;
	}

	public void setIdentifier(MatcherIdentifier identifier) {
		this.identifier = identifier;
	}

	public MockResponse getResponse() {
		return response;
	}

	public void setResponse(MockResponse response) {
		this.response = response;
	}
}
