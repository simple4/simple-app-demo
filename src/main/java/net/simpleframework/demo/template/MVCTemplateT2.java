package net.simpleframework.demo.template;

import net.simpleframework.mvc.AbstractMVCPage;
import net.simpleframework.mvc.template.t1.AbstractMVCTemplateT1;

public class MVCTemplateT2 extends AbstractMVCTemplateT1 {

	@Override
	public Class<? extends AbstractMVCPage> getHeaderPage() {
		return HeaderPageT2.class;
	}

	@Override
	public Class<? extends AbstractMVCPage> getFooterPage() {
		return FooterPageT2.class;
	}
}
