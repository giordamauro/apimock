package com.apimock.manager;

import com.apimock.core.exception.MockServiceAlreadyExistentException;
import com.apimock.core.exception.MockServiceNotFoundException;
import com.apimock.core.model.MockRepository;
import com.apimock.core.model.MockRepositoryManager;
import com.apimock.core.model.MockResponse;
import com.apimock.core.model.ServiceIdentifier;
import com.apimock.core.model.ServiceMatcher;

public class MockRepositoryManagerImpl implements MockRepositoryManager {

	private final MockRepository mockRepository;

	public MockRepositoryManagerImpl(MockRepository mockRepository) {
		this.mockRepository = mockRepository;
	}

	@Override
	public void createMockService(ServiceMatcher matcher, MockResponse mockResponse) throws MockServiceAlreadyExistentException {

		if (matcher == null || mockResponse == null) {
			throw new IllegalArgumentException("ServiceMatcher and MockResponse cannot be null");
		}

		ServiceIdentifier identifier = matcher.getIdentifier();
		mockRepository.putMockData(identifier, matcher, mockResponse);
	}

	@Override
	public void editMockService(ServiceMatcher matcher, MockResponse mockResponse) throws MockServiceNotFoundException {

		throw new UnsupportedOperationException();
	}

	@Override
	public void removeMockService(ServiceMatcher matcher) throws MockServiceNotFoundException {

		throw new UnsupportedOperationException();
	}
}
