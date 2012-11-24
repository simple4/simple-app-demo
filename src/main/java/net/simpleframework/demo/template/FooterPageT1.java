package net.simpleframework.demo.template;

import net.simpleframework.common.coll.KVMap;
import net.simpleframework.mvc.MVCFilter;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.template.AbstractTemplatePage;

/**
 * 这是一个开源的软件，请在LGPLv3下合法使用、修改或重新发布。
 * 
 * @author 陈侃(cknet@126.com, 13910090885)
 *         http://code.google.com/p/simpleframework/
 *         http://www.simpleframework.net
 */
public class FooterPageT1 extends AbstractTemplatePage {

	@Override
	public KVMap createVariables(final PageParameter pParameter) {
		return ((KVMap) super.createVariables(pParameter)).add("MVCFilter", MVCFilter.class);
	}
}
