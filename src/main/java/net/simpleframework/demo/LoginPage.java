package net.simpleframework.demo;

import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.template.AbstractTemplatePage;
import net.simpleframework.organization.web.component.login.LoginBean;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public class LoginPage extends AbstractTemplatePage {

	@Override
	protected void onInit(final PageParameter pParameter) {
		super.onInit(pParameter);

		addImportCSS(getCssResourceHomePath(pParameter) + "/login.css");
		addComponentBean(pParameter, "default_m_login", LoginBean.class).setContainerId(
				"default_m_login");
	}
}