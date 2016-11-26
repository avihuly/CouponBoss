package com.coupon.web.filters;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

// In case of null session the filter returns a distinct json respons
// else the request & response are chained
@WebFilter("/loginFilter")
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// Casting to HTTP
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		if (httpRequest.getSession(false) == null) {
			// Set response status to bad
			httpResponse.setStatus(400);
			// return null session json
			response.setContentType(MediaType.APPLICATION_JSON);
			// Write to body
			ServletOutputStream out = response.getOutputStream();
			out.println("{"
					+ "\"errorMessag\":\"null session\","
					+ " \"errorCode\": 900"
					+ "}");
			return;
		} else {
			chain.doFilter(request, response);
		}

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}
