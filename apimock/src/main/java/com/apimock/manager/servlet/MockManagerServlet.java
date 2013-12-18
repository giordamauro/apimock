package com.apimock.manager.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.apimock.consumer.servlet.MockServletContainer;
import com.apimock.core.exception.MockServiceAlreadyExistentException;
import com.apimock.core.model.MockRepositoryManager;
import com.apimock.core.model.MockResponse;
import com.apimock.core.model.ServiceMatcher;
import com.apimock.manager.MockManagerContent;
import com.apimock.manager.adapter.MockManagerAdapter;

public class MockManagerServlet extends HttpServlet {

	private static final long serialVersionUID = -1886366373810761633L;

	private static final Log logger = LogFactory.getLog(MockServletContainer.class);

	@Autowired
	private MockRepositoryManager mockModel;

	@Autowired
	private MockManagerAdapter httpMockAdapter;

	@Override
	public void init() throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, getServletContext());
		super.init();
	}

	// TODO Mejorar las respuestas

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

		logger.info("Creating Mock Service");

		MockManagerContent managerContent = httpMockAdapter.getMockContent(request);

		MockResponse mockResponse = managerContent.getResponse();
		ServiceMatcher matcher = managerContent.getMatcher();

		try {
			mockModel.createMockService(matcher, mockResponse);

			PrintWriter writer = response.getWriter();
			writer.println("Success");

		} catch (MockServiceAlreadyExistentException e) {

			response.sendError(400, "Mock service already exists");
		}
	}
}
