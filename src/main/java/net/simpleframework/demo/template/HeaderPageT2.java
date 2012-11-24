package net.simpleframework.demo.template;

import net.simpleframework.demo.DemoApplication;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.template.AbstractTemplatePage;

public class HeaderPageT2 extends AbstractTemplatePage {

	@Override
	protected void onInit(final PageParameter pParameter) {
		super.onInit(pParameter);

		addImportCSS(getCssResourceHomePath(pParameter, DemoApplication.class) + "/header_t2.css");
	}
}
