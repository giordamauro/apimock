package com.apimock.core.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class ServiceIdentifier {

	private final HttpMethod httpMethod;

	private final String path;

	public ServiceIdentifier(HttpMethod httpMethod, String path) {
		this.httpMethod = httpMethod;
		this.path = path;
	}

	public HttpMethod getHttpMethod() {
		return httpMethod;
	}

	public String getPath() {
		return path;
	}

	public int hashCode() {
		HashCodeBuilder hashCodeBuilder = new HashCodeBuilder(17, 31);
		hashCodeBuilder.append(httpMethod);
		hashCodeBuilder.append(path);

		int hashCode = hashCodeBuilder.toHashCode();

		return hashCode;
	}

	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof ServiceIdentifier))
			return false;

		ServiceIdentifier serviceIdentifier = (ServiceIdentifier) obj;
		EqualsBuilder equalsBuilder = new EqualsBuilder();
		equalsBuilder.append(httpMethod, serviceIdentifier.httpMethod);
		equalsBuilder.append(path, serviceIdentifier.path);

		return equalsBuilder.isEquals();
	}
}
