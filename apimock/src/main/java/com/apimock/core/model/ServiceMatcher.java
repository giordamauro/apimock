package com.apimock.core.model;

// TODO forma de guardar en la base

public interface ServiceMatcher {

	boolean equals(Object object);

	int hashCode();

	boolean evaluate(ServiceParameters serviceParameters);

	ServiceIdentifier getIdentifier();

	int getPriority();
}
