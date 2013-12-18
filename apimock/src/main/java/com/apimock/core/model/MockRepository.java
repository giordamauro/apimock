package com.apimock.core.model;

import java.util.Map;

public interface MockRepository {

	Map<ServiceMatcher, MockResponse> getMocksData(ServiceIdentifier identifier);

	void putMockData(ServiceIdentifier identifier, ServiceMatcher matcher, MockResponse response);
}
