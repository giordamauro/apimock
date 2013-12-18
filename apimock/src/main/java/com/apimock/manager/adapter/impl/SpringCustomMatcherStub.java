package com.apimock.manager.adapter.impl;

import com.apimock.core.model.HttpMethod;
import com.apimock.core.model.ServiceParameters;

public class SpringCustomMatcherStub implements CustomServiceMatcher {

	@Override
	public boolean evaluate(ServiceParameters serviceParameters) {

		HttpMethod httpMethod = serviceParameters.getIdentifier().getHttpMethod();

		return httpMethod == HttpMethod.DELETE;
	}

}
