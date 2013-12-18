package com.apimock.manager.adapter.impl;

import com.apimock.core.model.ServiceParameters;

public interface CustomServiceMatcher {

	boolean equals(Object object);

	int hashCode();

	boolean evaluate(ServiceParameters serviceParameters);
}
