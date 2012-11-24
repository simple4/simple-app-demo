package net.simpleframework.demo;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import net.simpleframework.common.ClassUtils;
import net.simpleframework.common.coll.ParameterMap;
import net.simpleframework.ctx.IModuleContext;
import net.simpleframework.ctx.IModuleContextCallback;
import net.simpleframework.ctx.ado.DeploySqlUtils;
import net.simpleframework.ctx.ado.DeploySqlUtils.ITargetPath;
import net.simpleframework.ctx.ado.IADOModuleContext;
import net.simpleframework.ctx.permission.PermissionFactory;
import net.simpleframework.demo.template.MVCTemplateT1;
import net.simpleframework.demo.template.MVCTemplateT2;
import net.simpleframework.mvc.AbstractMVCPage;
import net.simpleframework.mvc.IMVCTemplate;
import net.simpleframework.mvc.MVCConfig;
import net.simpleframework.mvc.MVCContext;
import net.simpleframework.mvc.MVCUtils;
import net.simpleframework.mvc.template.t2.T2TemplatePage;

import org.hsqldb.Server;

public class DemoApplication extends MVCContext {

	@Override
	public void doInit(final ServletContext servletContext) throws Exception {
		super.doInit(servletContext);
		final ApplicationSettings settings = settings();
		final File settingsFile = new File(servletContext.getRealPath("/WEB-INF/base.properties"));
		if (!settingsFile.exists()) {
			settings.load(ClassUtils.getResourceAsStream("base.properties"));
		} else {
			settings.load(new FileInputStream(settingsFile));
		}

		if (settings.getBoolProperty("hsql.start")) {
			try {
				final Server svr = new Server();
				svr.setAddress(settings.getProperty("hsql.address"));
				svr.setPort(settings.getIntProperty("hsql.port"));
				svr.setDatabaseName(0, settings.getProperty("hsql.dbname"));
				svr.setDatabasePath(0, settings.getProperty("hsql.dbpath"));
				svr.setSilent(true);
				svr.start();
			} catch (final Exception e1) {
				log.warn(e1);
			}
		}

		final DataSource dataSource = settings.getDataSource();
		try {
			DeploySqlUtils.executeSqlScript(dataSource, getScanPackageNames(), new ITargetPath() {
				@Override
				public String getRealPath(final String p) {
					return MVCUtils.getRealPath(p);
				}
			});
		} catch (final Exception e) {
			log.warn(e);
		}

		scanModuleContext(new IModuleContextCallback() {
			@Override
			public void doModuleContext(final IModuleContext ctx) {
				if (ctx instanceof IADOModuleContext) {
					((IADOModuleContext) ctx).setDataSource(dataSource);
				}
				if (ctx.getContextSettings() == null) {
					ctx.setContextSettings(settings);
				}
			}
		});

		PermissionFactory.set(settings.getProperty("role.permission.handler"));
	}

	public ApplicationSettings settings() {
		return singleton(ApplicationSettings.class);
	}

	@Override
	public String[] getScanPackageNames() {
		return new String[] { "net.simpleframework" };
	}

	@Override
	public IMVCTemplate getTemplate(final AbstractMVCPage page) {
		if (page instanceof T2TemplatePage) {
			return singleton(MVCTemplateT2.class);
		}
		return singleton(MVCTemplateT1.class);
	}

	@Override
	public MVCConfig getPageConfig() {
		return singleton(MyPageConfig.class);
	}

	public static class MyPageConfig extends MVCConfig {
		@Override
		public String getPagePath() {
			return "/demo";
		}

		@Override
		public String getLoginUrl() {
			return AbstractMVCPage.uriFor(LoginPage.class);
		}

		@Override
		public String getHomeUrl() {
			return AbstractMVCPage.uriFor(HomePage.class);
		}

		private final ParameterMap packages = new ParameterMap();
		{
			packages.put("/sf", "net.simpleframework");
		}

		@Override
		public ParameterMap getPagePackages() {
			return packages;
		}

		@Override
		public boolean isResourceCompress() {
			return false;
		}
	}
}
