package com.apimock.manager.filter;

import com.apimock.core.model.HttpMethod;
import com.apimock.core.model.ServiceParameters;
import com.apimock.manager.CustomServiceFilter;

public class SpringCustomMatcherStub implements CustomServiceFilter {

	@Override
	public boolean evaluate(ServiceParameters serviceParameters) {

		HttpMethod httpMethod = serviceParameters.getIdentifier().getHttpMethod();

		return httpMethod == HttpMethod.GET;
	}

}
