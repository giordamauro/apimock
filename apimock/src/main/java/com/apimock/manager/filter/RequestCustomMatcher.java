package com.apimock.manager.filter;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.apimock.core.model.ServiceParameters;
import com.apimock.manager.CustomServiceFilter;

public class RequestCustomMatcher implements CustomServiceFilter {

	private final Map<String, List<String>> queryParams;

	private final Map<String, List<String>> headers;

	public RequestCustomMatcher(Map<String, List<String>> queryParams, Map<String, List<String>> headers) {
		this.queryParams = queryParams;
		this.headers = headers;
	}

	@Override
	public boolean evaluate(ServiceParameters serviceParameters) {

		boolean eval = false;

		if (queryParams != null) {
			eval = compareParameters(queryParams, serviceParameters.getQueryParams());
		}

		if (eval && headers != null) {
			eval = compareParameters(headers, serviceParameters.getHeaders());
		}

		return eval;
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
		if (!(obj instanceof RequestCustomMatcher))
			return false;

		RequestCustomMatcher serviceMatcher = (RequestCustomMatcher) obj;
		EqualsBuilder equalsBuilder = new EqualsBuilder();
		equalsBuilder.append(queryParams, serviceMatcher.queryParams);
		equalsBuilder.append(headers, serviceMatcher.headers);

		return equalsBuilder.isEquals();
	}

}
