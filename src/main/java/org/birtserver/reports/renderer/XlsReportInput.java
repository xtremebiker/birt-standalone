package org.birtserver.reports.renderer;

import java.util.Locale;
import java.util.Map;

/**
 * Input for xls report
 * 
 * @author Aritz
 *
 */
public class XlsReportInput extends ReportInput {

	private String nameSheet;

	public XlsReportInput(ReportPath reportPath,
			Map<String, Object> reportParams, String nameSheet, Locale locale) {
		super(reportPath, reportParams, locale);
		this.nameSheet = nameSheet;
	}

	public String getNameSheet() {
		return nameSheet;
	}

}
