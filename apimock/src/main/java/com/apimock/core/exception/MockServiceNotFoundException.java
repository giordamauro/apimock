package com.apimock.core.exception;

import com.apimock.core.model.ServiceParameters;

public class MockServiceNotFoundException extends Exception {

	private static final long serialVersionUID = -2495135857252042994L;

	private final ServiceParameters mockIdentifier;

	public MockServiceNotFoundException(ServiceParameters mockIdentifier) {
		this.mockIdentifier = mockIdentifier;
	}

	public ServiceParameters getMockIdentifier() {
		return mockIdentifier;
	}

}
