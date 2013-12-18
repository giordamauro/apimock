package com.apimock.core.model;

import com.apimock.core.exception.MockServiceMatcherConflict;
import com.apimock.core.exception.MockServiceNotFoundException;

public interface MockRepositoryConsumer {

	MockResponse getMockResponse(ServiceParameters serviceParameters) throws MockServiceNotFoundException, MockServiceMatcherConflict;
}
