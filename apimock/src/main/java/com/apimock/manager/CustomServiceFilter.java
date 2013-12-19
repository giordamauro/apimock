package com.apimock.manager;

import java.io.Serializable;

import com.apimock.core.model.ServiceParameters;

public interface CustomServiceFilter extends Serializable {

	boolean equals(Object object);

	int hashCode();

	boolean evaluate(ServiceParameters serviceParameters);
}
