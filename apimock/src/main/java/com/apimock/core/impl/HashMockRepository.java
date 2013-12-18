package com.apimock.core.impl;

import java.util.HashMap;
import java.util.Map;

import com.apimock.core.model.MockRepository;
import com.apimock.core.model.MockResponse;
import com.apimock.core.model.ServiceIdentifier;
import com.apimock.core.model.ServiceMatcher;

public class HashMockRepository implements MockRepository {

	private final Map<ServiceIdentifier, Map<ServiceMatcher, MockResponse>> repo = new HashMap<ServiceIdentifier, Map<ServiceMatcher, MockResponse>>();

	@Override
	public Map<ServiceMatcher, MockResponse> getMocksData(ServiceIdentifier identifier) {

		return repo.get(identifier);
	}

	@Override
	public void putMockData(ServiceIdentifier identifier, ServiceMatcher matcher, MockResponse response) {
		Map<ServiceMatcher, MockResponse> mocks = repo.get(identifier);
		if (mocks == null) {
			mocks = new HashMap<ServiceMatcher, MockResponse>();
			repo.put(identifier, mocks);
		}
		mocks.put(matcher, response);
	}

}
