package com.apimock.manager.filter;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.apimock.core.model.ServiceParameters;
import com.apimock.manager.CustomServiceFilter;

public class CompositeServiceFilter implements CustomServiceFilter {

	private static final long serialVersionUID = 7635135650776707144L;

	private final CustomServiceFilter filter1;

	private final CustomServiceFilter filter2;

	public CompositeServiceFilter(CustomServiceFilter customMatcher1, CustomServiceFilter customMatcher2) {
		this.filter1 = customMatcher1;
		this.filter2 = customMatcher2;
	}

	@Override
	public boolean evaluate(ServiceParameters serviceParameters) {

		return filter1.evaluate(serviceParameters) && filter2.evaluate(serviceParameters);
	}

	@Override
	public int hashCode() {
		HashCodeBuilder hashCodeBuilder = new HashCodeBuilder(17, 31);
		hashCodeBuilder.append(filter1);
		hashCodeBuilder.append(filter2);

		int hashCode = hashCodeBuilder.toHashCode();

		return hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof CompositeServiceFilter))
			return false;

		CompositeServiceFilter serviceMatcher = (CompositeServiceFilter) obj;
		EqualsBuilder equalsBuilder = new EqualsBuilder();
		equalsBuilder.append(filter1, serviceMatcher.filter1);
		equalsBuilder.append(filter2, serviceMatcher.filter2);

		return equalsBuilder.isEquals();
	}
}
