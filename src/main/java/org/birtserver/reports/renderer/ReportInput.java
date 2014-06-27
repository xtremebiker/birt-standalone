package org.birtserver.reports.renderer;

import java.util.Locale;
import java.util.Map;

/**
 * Establece los parametros de entrada para procesar un report concreto
 * 
 * @author amaeztu
 * @since 1.0
 * 
 */
public abstract class ReportInput {

	private ReportPath _ReportPath;

	private Map<String, Object> _ReportParams;

	private Locale _Locale;
	
	public ReportInput(ReportPath reportPath, Map<String, Object> reportParams, Locale locale) {
		this._ReportPath = reportPath;
		this._ReportParams = reportParams;
		this._Locale = locale;
	}

	public Map<String, Object> get_ReportParams() {
		return this._ReportParams;
	}

	public ReportPath get_ReportPath() {
		return this._ReportPath;
	}

	public void set_ReportParams(Map<String, Object> _ReportParams) {
		this._ReportParams = _ReportParams;
	}

	public void set_ReportPath(ReportPath _ReportPath) {
		this._ReportPath = _ReportPath;
	}

	public Locale get_Locale() {
		return _Locale;
	}

	public void set_Locale(Locale _Locale) {
		this._Locale = _Locale;
	}

}
