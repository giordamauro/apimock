package com.apimock.consumer.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.apimock.consumer.adapter.MockHttpResponseAdapter;
import com.apimock.consumer.adapter.ServiceParametersAdapter;
import com.apimock.core.exception.MockServiceMatcherConflict;
import com.apimock.core.exception.MockServiceNotFoundException;
import com.apimock.core.model.MockRepositoryConsumer;
import com.apimock.core.model.MockResponse;
import com.apimock.core.model.ServiceParameters;

public class MockServletContainer extends HttpServlet implements Filter {

	private static final long serialVersionUID = -1886366373810761633L;

	private static final Log logger = LogFactory.getLog(MockServletContainer.class);

	@Autowired
	private MockRepositoryConsumer mockRepository;

	@Autowired
	private MockHttpResponseAdapter mockHttpResponseAdapter;

	@Autowired
	private ServiceParametersAdapter serviceParametersAdapter;

	private FilterConfig filterConfig;

	@Override
	public ServletContext getServletContext() {
		if (filterConfig != null)
			return filterConfig.getServletContext();

		return super.getServletContext();
	}

	@Override
	public void init() throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, getServletContext());
		super.init();
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		init();
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		logger.info(String.format("Servicing request '%s: %s' from ip '%s'", request.getMethod(), request.getPathInfo(), request.getRemoteAddr()));

		ServiceParameters mockIdentifier = serviceParametersAdapter.buildServiceParameters(request);

		try {
			MockResponse mockResponse = mockRepository.getMockResponse(mockIdentifier);
			mockHttpResponseAdapter.sendMockResponse(mockResponse, response);

		} catch (MockServiceNotFoundException e) {

			response.sendError(400, "Service not found");

		} catch (MockServiceMatcherConflict e) {

			response.sendError(400, "Service matcher conflict - update priorities");
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		if (!request.getClass().isAssignableFrom(HttpServletRequest.class) || !response.getClass().isAssignableFrom(HttpServletResponse.class)) {
			throw new ServletException("non-HTTP request or response");
		}

		doHttpFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
	}

	private void doHttpFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
		try {
			service(request, response);
			chain.doFilter(request, response);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

}
