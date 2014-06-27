package org.birtserver.reports.renderer;

import java.util.Locale;
import java.util.Map;

public class GenericReportInput extends ReportInput {

	public GenericReportInput(ReportPath reportPath,
			Map<String, Object> reportParams, Locale locale) {
		super(reportPath, reportParams, locale);
	}

}
