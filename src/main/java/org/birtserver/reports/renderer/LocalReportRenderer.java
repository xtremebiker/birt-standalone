package org.birtserver.reports.renderer;

import org.springframework.stereotype.Service;

@Service
public class LocalReportRenderer extends AbstractReportRenderer {

	private String _RepositoryReports;

	public void set_RepositoryReports(String _RepositoryReports) {
		this._RepositoryReports = _RepositoryReports;
	}

	@Override
	public String get_RootDirectoryPath() {
		return this._RepositoryReports;
	}

}
