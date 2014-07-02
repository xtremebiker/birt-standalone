package org.birtserver.reports.renderer;

import org.springframework.stereotype.Service;

@Service
public class LocalReportRenderer extends AbstractReportRenderer {

	private String repositoryReports;

	public void setRepositoryReports(String repositoryReports) {
		this.repositoryReports = repositoryReports;
	}

	@Override
	public String getRootDirectoryPath() {
		return this.repositoryReports;
	}

}
