package org.birtserver.reports.renderer;

import java.util.Locale;
import java.util.Map;

/**
 * Parameters for a report to be rendered
 * 
 * @author amaeztu
 * @since 1.0
 * 
 */
public abstract class ReportInput {

	private ReportPath reportPath;

	private Map<String, Object> reportParams;

	private Locale locale;

	public ReportInput(ReportPath reportPath, Map<String, Object> reportParams,
			Locale locale) {
		this.reportPath = reportPath;
		this.reportParams = reportParams;
		this.locale = locale;
	}

	public Map<String, Object> getReportParams() {
		return this.reportParams;
	}

	public ReportPath getReportPath() {
		return this.reportPath;
	}

	public void setReportParams(Map<String, Object> reportParams) {
		this.reportParams = reportParams;
	}

	public void setReportPath(ReportPath reportPath) {
		this.reportPath = reportPath;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

}
