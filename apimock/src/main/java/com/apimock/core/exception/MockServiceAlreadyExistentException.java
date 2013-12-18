package com.apimock.core.exception;

import com.apimock.core.model.ServiceIdentifier;

public class MockServiceAlreadyExistentException extends Exception {

	private static final long serialVersionUID = -1947752249356234514L;

	private final ServiceIdentifier mockIdentifier;

	public MockServiceAlreadyExistentException(ServiceIdentifier serviceIdentifier) {
		this.mockIdentifier = serviceIdentifier;
	}

	public ServiceIdentifier getMockIdentifier() {
		return mockIdentifier;
	}

}
