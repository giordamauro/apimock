package com.apimock.core.impl;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.apimock.core.model.ServiceIdentifier;
import com.apimock.core.model.ServiceMatcher;
import com.apimock.core.model.ServiceParameters;
import com.apimock.manager.CustomServiceFilter;

public class ServiceMatcherImpl implements ServiceMatcher {

	private final ServiceIdentifier identifier;

	private final int priority;

	private CustomServiceFilter customMatcher;

	public ServiceMatcherImpl(ServiceIdentifier identifier, int priority) {
		this.identifier = identifier;
		this.priority = priority;
	}

	public ServiceMatcherImpl(ServiceIdentifier identifier) {
		this(identifier, 0);
	}

	@Override
	public boolean evaluate(ServiceParameters serviceParameters) {

		boolean eval = identifier.equals(serviceParameters.getIdentifier());

		if (eval && customMatcher != null) {
			eval = customMatcher.evaluate(serviceParameters);
		}

		return eval;
	}

	@Override
	public ServiceIdentifier getIdentifier() {
		return identifier;
	}

	@Override
	public int getPriority() {

		return priority;
	}

	public CustomServiceFilter getCustomMatcher() {
		return customMatcher;
	}

	public void setCustomMatcher(CustomServiceFilter customMatcher) {
		this.customMatcher = customMatcher;
	}

	@Override
	public int hashCode() {
		HashCodeBuilder hashCodeBuilder = new HashCodeBuilder(17, 31);
		hashCodeBuilder.append(identifier);
		hashCodeBuilder.append(priority);
		hashCodeBuilder.append(customMatcher);

		int hashCode = hashCodeBuilder.toHashCode();

		return hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof ServiceMatcherImpl))
			return false;

		ServiceMatcherImpl serviceMatcher = (ServiceMatcherImpl) obj;
		EqualsBuilder equalsBuilder = new EqualsBuilder();
		equalsBuilder.append(identifier, serviceMatcher.identifier);
		equalsBuilder.append(priority, serviceMatcher.priority);
		equalsBuilder.append(customMatcher, serviceMatcher.customMatcher);

		return equalsBuilder.isEquals();
	}

}
