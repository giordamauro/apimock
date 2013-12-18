package com.apimock.manager.adapter.impl;

import java.util.List;
import java.util.Map;

import com.apimock.core.model.HttpMethod;

public class MatcherIdentifier {

	private HttpMethod method;

	private String path;

	private int matcherPriority;

	private Map<String, List<String>> queryParams;

	private Map<String, List<String>> headers;

	private String matcher;

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

	public Map<String, List<String>> getQueryParams() {
		return queryParams;
	}

	public void setQueryParams(Map<String, List<String>> queryParams) {
		this.queryParams = queryParams;
	}

	public Map<String, List<String>> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, List<String>> headers) {
		this.headers = headers;
	}
}
