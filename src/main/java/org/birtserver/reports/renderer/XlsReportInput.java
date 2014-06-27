package org.birtserver.reports.renderer;

import java.util.Locale;
import java.util.Map;

public class XlsReportInput extends ReportInput {

	private String _NameSheet;

	public XlsReportInput(ReportPath reportPath,
			Map<String, Object> reportParams, String nameSheet, Locale locale) {
		super(reportPath, reportParams, locale);
		this._NameSheet = nameSheet;
	}

	public String get_NameSheet() {
		return _NameSheet;
	}

}
