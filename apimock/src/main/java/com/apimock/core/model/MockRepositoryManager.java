package com.apimock.core.model;

import com.apimock.core.exception.MockServiceAlreadyExistentException;
import com.apimock.core.exception.MockServiceNotFoundException;

public interface MockRepositoryManager {

	void createMockService(ServiceMatcher matcher, MockResponse mockResponse) throws MockServiceAlreadyExistentException;

	void editMockService(ServiceMatcher matcher, MockResponse mockResponse) throws MockServiceNotFoundException;

	void removeMockService(ServiceMatcher matcher) throws MockServiceNotFoundException;
}
