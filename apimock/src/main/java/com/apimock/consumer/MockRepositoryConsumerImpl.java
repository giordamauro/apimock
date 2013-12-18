package com.apimock.consumer;

import java.util.Map;
import java.util.Set;

import com.apimock.core.exception.MockServiceMatcherConflict;
import com.apimock.core.exception.MockServiceNotFoundException;
import com.apimock.core.model.MockRepository;
import com.apimock.core.model.MockRepositoryConsumer;
import com.apimock.core.model.MockResponse;
import com.apimock.core.model.ServiceIdentifier;
import com.apimock.core.model.ServiceMatcher;
import com.apimock.core.model.ServiceParameters;

public class MockRepositoryConsumerImpl implements MockRepositoryConsumer {

	private final MockRepository mockRepository;

	public MockRepositoryConsumerImpl(MockRepository mockRepository) {
		this.mockRepository = mockRepository;
	}

	@Override
	public MockResponse getMockResponse(ServiceParameters serviceParameters) throws MockServiceNotFoundException, MockServiceMatcherConflict {

		if (serviceParameters == null) {
			throw new IllegalArgumentException("Identifier cannot be null");
		}

		ServiceIdentifier identifier = serviceParameters.getIdentifier();
		Map<ServiceMatcher, MockResponse> mocksData = mockRepository.getMocksData(identifier);

		MockResponse mockResponse = null;
		if (mocksData != null) {
			mockResponse = findMockResponse(mocksData, serviceParameters);
		}

		if (mockResponse == null) {
			throw new MockServiceNotFoundException(serviceParameters);
		}

		return mockResponse;
	}

	private MockResponse findMockResponse(Map<ServiceMatcher, MockResponse> mocksData, ServiceParameters serviceParameters) throws MockServiceMatcherConflict {

		ServiceMatcher serviceMatcher = null;
		MockResponse response = null;

		Set<ServiceMatcher> matchers = mocksData.keySet();
		for (ServiceMatcher matcher : matchers) {
			if (matcher.evaluate(serviceParameters)) {

				if (response != null && serviceMatcher != null) {
					throw new MockServiceMatcherConflict(serviceMatcher, matcher);
				}

				response = mocksData.get(matcher);
				serviceMatcher = matcher;
			}
		}
		return response;
	}

}
