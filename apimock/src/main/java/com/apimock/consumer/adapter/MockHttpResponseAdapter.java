package com.apimock.consumer.adapter;

import javax.servlet.http.HttpServletResponse;

import com.apimock.core.model.MockResponse;

public interface MockHttpResponseAdapter {

	void sendMockResponse(MockResponse mockResponse, HttpServletResponse response);
}
