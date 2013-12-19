package com.apimock.manager.filter;

import com.apimock.core.model.HttpMethod;
import com.apimock.core.model.ServiceParameters;
import com.apimock.manager.CustomServiceFilter;

public class SpringCustomMatcherStub implements CustomServiceFilter {

	private static final long serialVersionUID = -7600568319444572220L;

	@Override
	public boolean evaluate(ServiceParameters serviceParameters) {

		HttpMethod httpMethod = serviceParameters.getIdentifier().getHttpMethod();

		return httpMethod == HttpMethod.GET;
	}

}
