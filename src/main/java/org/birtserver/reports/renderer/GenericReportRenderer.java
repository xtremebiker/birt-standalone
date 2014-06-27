package org.birtserver.reports.renderer;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

/**
 * Clase gen�rica de generaci�n de reports que se puede a�adir en cada m�dulo
 * 
 * @author amaeztu
 * @since 1.0
 * 
 */
@Service
public class GenericReportRenderer extends AbstractReportRenderer implements
		ServletContextAware {

	@Autowired
	private ServletContext _ServletContext;

	@Override
	public void setServletContext(ServletContext servletContext) {
		this._ServletContext = servletContext;
	}

	@Override
	public String get_RootDirectoryPath() {
		return this._ServletContext.getRealPath("/");
	}
}
