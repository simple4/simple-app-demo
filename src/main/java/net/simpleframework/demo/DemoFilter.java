package net.simpleframework.demo;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

import net.simpleframework.mvc.MVCFilter;

public class DemoFilter extends MVCFilter {

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {
		try {
			new DemoApplication().doInit(filterConfig.getServletContext());
		} catch (final Exception e) {
			throw new ServletException(e);
		}
	}
}
