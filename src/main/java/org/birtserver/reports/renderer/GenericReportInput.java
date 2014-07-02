package org.birtserver.reports.renderer;

import java.util.Locale;
import java.util.Map;

/**
 * Parameters for generic report to be rendered
 * 
 * @author Aritz
 *
 */
public class GenericReportInput extends ReportInput {

	public GenericReportInput(ReportPath reportPath,
			Map<String, Object> reportParams, Locale locale) {
		super(reportPath, reportParams, locale);
	}

}
