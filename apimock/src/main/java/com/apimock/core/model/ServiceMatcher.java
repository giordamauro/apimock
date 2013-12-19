package com.apimock.core.model;

public interface ServiceMatcher {

	boolean equals(Object object);

	int hashCode();

	boolean evaluate(ServiceParameters serviceParameters);

	ServiceIdentifier getIdentifier();

	int getPriority();
}
