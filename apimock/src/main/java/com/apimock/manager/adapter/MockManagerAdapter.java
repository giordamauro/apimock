package com.apimock.manager.adapter;

import javax.servlet.http.HttpServletRequest;

import com.apimock.manager.MockManagerContent;

public interface MockManagerAdapter {

	MockManagerContent getMockContent(HttpServletRequest request);
}