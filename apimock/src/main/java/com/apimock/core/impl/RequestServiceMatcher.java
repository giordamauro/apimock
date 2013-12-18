package com.apimock.core.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.apimock.core.model.ServiceIdentifier;
import com.apimock.core.model.ServiceParameters;

public class RequestServiceMatcher extends BasicServiceMatcher {

	public static class Builder {

		private final ServiceIdentifier identifier;

		private int priority;

		private final Map<String, List<String>> queryParams = new HashMap<String, List<String>>();

		private final Map<String, List<String>> headers = new HashMap<String, List<String>>();

		public Builder(ServiceIdentifier identifier) {
			this.identifier = identifier;
		}

		public Builder(ServiceIdentifier serviceIdentifier, int matcherPriority) {
			this.identifier = serviceIdentifier;
			this.priority = matcherPriority;
		}

		public void addQueryParams(Map<String, List<String>> params) {
			this.queryParams.putAll(params);
		}

		public void addQueryParam(String param, List<String> values) {
			this.queryParams.put(param, values);
		}

		public void addQueryParam(String param, String value) {
			List<String> values = this.queryParams.get(param);
			if (values == null) {
				values = new ArrayList<String>();
				this.queryParams.put(param, values);
			}
			values.add(value);
		}

		public void addHeaders(Map<String, List<String>> headers) {
			this.headers.putAll(headers);
		}

		public void addHeader(String header, List<String> values) {
			this.headers.put(header, values);
		}

		public void addHeader(String header, String value) {
			List<String> values = this.headers.get(header);
			if (values == null) {
				values = new ArrayList<String>();
				this.headers.put(header, values);
			}
			values.add(value);
		}

		public RequestServiceMatcher build() {

			return new RequestServiceMatcher(this);
		}
	}

	private final Map<String, List<String>> queryParams;

	private final Map<String, List<String>> headers;

	private RequestServiceMatcher(Builder builder) {
		super(builder.identifier, builder.priority);

		this.queryParams = builder.queryParams;
		this.headers = builder.headers;
	}

	@Override
	public boolean evaluate(ServiceParameters serviceParameters) {

		return super.evaluate(serviceParameters) && compareParameters(queryParams, serviceParameters.getQueryParams()) && compareParameters(headers, serviceParameters.getHeaders());
	}

	private boolean compareParameters(Map<String, List<String>> parameters, Map<String, List<String>> serviceParameters) {

		for (Entry<String, List<String>> param : parameters.entrySet()) {
			String paramName = param.getKey();

			List<String> serviceValues = serviceParameters.get(paramName);
			if (serviceValues == null) {
				return false;
			}

			List<String> values = param.getValue();
			for (String value : values) {
				if (!serviceValues.contains(value)) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		HashCodeBuilder hashCodeBuilder = new HashCodeBuilder(17, 31);
		hashCodeBuilder.append(getIdentifier());
		hashCodeBuilder.append(getPriority());
		hashCodeBuilder.append(headers);
		hashCodeBuilder.append(queryParams);

		int hashCode = hashCodeBuilder.toHashCode();

		return hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof RequestServiceMatcher))
			return false;

		RequestServiceMatcher serviceMatcher = (RequestServiceMatcher) obj;
		EqualsBuilder equalsBuilder = new EqualsBuilder();
		equalsBuilder.append(getIdentifier(), serviceMatcher.getIdentifier());
		equalsBuilder.append(getPriority(), serviceMatcher.getPriority());
		equalsBuilder.append(queryParams, serviceMatcher.queryParams);
		equalsBuilder.append(headers, serviceMatcher.headers);

		return equalsBuilder.isEquals();
	}

}
