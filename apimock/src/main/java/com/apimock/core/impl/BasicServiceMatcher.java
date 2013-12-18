package com.apimock.core.impl;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.apimock.core.model.ServiceIdentifier;
import com.apimock.core.model.ServiceMatcher;
import com.apimock.core.model.ServiceParameters;

public class BasicServiceMatcher implements ServiceMatcher {

	private final ServiceIdentifier identifier;

	private final int priority;

	public BasicServiceMatcher(ServiceIdentifier identifier, int priority) {
		this.identifier = identifier;
		this.priority = priority;
	}

	public BasicServiceMatcher(ServiceIdentifier identifier) {
		this(identifier, 0);
	}

	@Override
	public boolean evaluate(ServiceParameters serviceParameters) {

		return identifier.equals(serviceParameters.getIdentifier());
	}

	@Override
	public ServiceIdentifier getIdentifier() {
		return identifier;
	}

	@Override
	public int getPriority() {

		return priority;
	}

	@Override
	public int hashCode() {
		HashCodeBuilder hashCodeBuilder = new HashCodeBuilder(17, 31);
		hashCodeBuilder.append(identifier);
		hashCodeBuilder.append(priority);

		int hashCode = hashCodeBuilder.toHashCode();

		return hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof BasicServiceMatcher))
			return false;

		BasicServiceMatcher serviceMatcher = (BasicServiceMatcher) obj;
		EqualsBuilder equalsBuilder = new EqualsBuilder();
		equalsBuilder.append(identifier, serviceMatcher.identifier);
		equalsBuilder.append(priority, serviceMatcher.priority);

		return equalsBuilder.isEquals();
	}

}
