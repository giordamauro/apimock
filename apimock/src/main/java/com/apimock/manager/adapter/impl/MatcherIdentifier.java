package com.apimock.manager.adapter.impl;

import com.apimock.core.model.HttpMethod;

public class MatcherIdentifier {

	private HttpMethod method;

	private String path;

	private String matcher;

	private int matcherPriority;

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

	public String getMatcher() {
		return matcher;
	}

	public void setMatcher(String matcher) {
		this.matcher = matcher;
	}

	public int getMatcherPriority() {
		return matcherPriority;
	}

	public void setMatcherPriority(int matcherPriority) {
		this.matcherPriority = matcherPriority;
	}
}
