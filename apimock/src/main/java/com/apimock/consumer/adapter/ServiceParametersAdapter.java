package com.apimock.consumer.adapter;

import javax.servlet.http.HttpServletRequest;

import com.apimock.core.model.ServiceParameters;

public interface ServiceParametersAdapter {

	ServiceParameters buildServiceParameters(HttpServletRequest request);

}
