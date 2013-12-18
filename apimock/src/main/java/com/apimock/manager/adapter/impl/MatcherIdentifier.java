package com.apimock.manager.adapter.impl;

import com.apimock.core.model.HttpMethod;

public class MatcherIdentifier {

	private HttpMethod method;

	private String path;

	private int matcherPriority;

	private CustomServiceMatcher matcher;

	public HttpMethod getMethod() {
		return method;
	}

	public void setMethod(HttpMethod method) {
		this.method = method;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public CustomServiceMatcher getMatcher() {
		return matcher;
	}

	public void setMatcher(CustomServiceMatcher matcher) {
		this.matcher = matcher;
	}

	public int getMatcherPriority() {
		return matcherPriority;
	}

	public void setMatcherPriority(int matcherPriority) {
		this.matcherPriority = matcherPriority;
	}
}
