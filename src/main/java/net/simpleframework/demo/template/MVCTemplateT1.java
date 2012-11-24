package net.simpleframework.demo.template;

import net.simpleframework.mvc.AbstractMVCPage;
import net.simpleframework.mvc.template.t1.AbstractMVCTemplateT1;

public class MVCTemplateT1 extends AbstractMVCTemplateT1 {
	@Override
	public Class<? extends AbstractMVCPage> getHeaderPage() {
		return HeaderPageT1.class;
	}

	@Override
	public Class<? extends AbstractMVCPage> getFooterPage() {
		return FooterPageT1.class;
	}

	// al.add(MenuItem.of(menuBean,
	// "开发者").setUrl(AbstractMVCPage.uriFor(DeveloperPage.class)));
	// al.add(MenuItem.sep(menuBean));
}
