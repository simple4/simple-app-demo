package net.simpleframework.demo;

import javax.sql.DataSource;

import net.simpleframework.common.ClassUtils;
import net.simpleframework.common.StringUtils;
import net.simpleframework.common.bean.BeanUtils;
import net.simpleframework.ctx.ContextSettings;

public class ApplicationSettings extends ContextSettings {

	private DataSource dataSource;

	public DataSource getDataSource() {
		if (dataSource == null) {
			try {
				dataSource = (DataSource) ClassUtils.forName(getProperty("dbpool.provider"))
						.newInstance();
				final String[] props = StringUtils.split(getProperty("dbpool.properties"));
				if (props != null) {
					for (final String prop : props) {
						BeanUtils.setProperty(dataSource, prop, getProperty("dbpool." + prop));
					}
				}
			} catch (final Exception e) {
				log.error(e);
			}
		}
		return dataSource;
	}
}
