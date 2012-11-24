package net.simpleframework.demo;

import java.io.IOException;
import java.util.Map;

import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.component.ui.pager.AbstractTablePagerHandler;
import net.simpleframework.mvc.template.t2.T2TemplatePage;

public class HomePage extends T2TemplatePage {

	@Override
	protected void onInit(final PageParameter pParameter) {
		super.onInit(pParameter);
		// final TablePagerBean tablePager = (TablePagerBean)
		// addComponentBean(pParameter,
		// "biaozhunTable",
		// TablePagerBean.class).setShowLineNo(true).setContainerId("idTest")
		// .setHandleClass(TestTbl.class.getName());
		// tablePager.addColumn(new
		// TablePagerColumn("f1").setColumnText("标题").setTextAlign(
		// ETextAlign.left));

		// addComponentBean(pParameter, "idTest",
		// PortalBean.class).setContainerId("idTest");
	}

	@Override
	protected String toHtml(final PageParameter pParameter, final Map<String, Object> variables,
			final String variable) throws IOException {
		return "<div id=\"idTest\"></div>";
	}

	public static class TestTbl extends AbstractTablePagerHandler {
	}
}
