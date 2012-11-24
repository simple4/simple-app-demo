package net.simpleframework.demo.template;

import static net.simpleframework.common.I18n.$m;
import net.simpleframework.common.ID;
import net.simpleframework.common.html.element.SpanElement;
import net.simpleframework.ctx.ModuleFunctions;
import net.simpleframework.demo.DemoApplication;
import net.simpleframework.mvc.MVCUtils;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.ctx.WebModuleFunction;
import net.simpleframework.mvc.ctx.permission.IPagePermissionHandler;
import net.simpleframework.organization.web.page.base.AbstractEditAwarePage;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public class HeaderPageT1 extends AbstractEditAwarePage {

	@Override
	protected void onInit(final PageParameter pParameter) {
		super.onInit(pParameter);

		addImportCSS(getCssResourceHomePath(pParameter, DemoApplication.class) + "/header_t1.css");
	}

	public String loginInfo(final PageParameter pParameter) {
		final StringBuilder sb = new StringBuilder();
		final WebModuleFunction func = (WebModuleFunction) ModuleFunctions
				.getDefaultFunction("simple-module-myportal");
		if (func != null) {
			sb.append("<a href='").append(func.getUrl());
			sb.append("'>").append($m("HeaderPage.3")).append("</a>");
			sb.append(SpanElement.SEP);
		}

		final IPagePermissionHandler permission = permission();
		final ID loginId = permission.getLoginId(pParameter);
		if (loginId == null) {
			sb.append("<a onclick=\"$Actions.loc('").append(MVCUtils.getLocationPath())
					.append("')\">").append($m("HeaderPage.1")).append("</a>");
		} else {
			sb.append("<img class='photo_icon' src='")
					.append(permission.getPhotoUrl(pParameter, loginId, 128, 128)).append("' />");
			sb.append("<a onclick=\"$Actions['editUserWindow']('accountId=").append(loginId)
					.append("');\">").append(permission.getUser(loginId))
					.append("</a><span> ( </span><a onclick=\"$Actions['ajaxLogout']();\">")
					.append($m("HeaderPage.2")).append("</a><span> )</span>");
		}
		return sb.toString();
	}
}
