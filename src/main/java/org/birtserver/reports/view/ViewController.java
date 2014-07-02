package org.birtserver.reports.view;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;

import org.birtserver.reports.controller.IReportService;
import org.springframework.stereotype.Component;

@Component
@ViewScoped
public class ViewController {

	@Inject
	private IReportService reportService;

	public String getTemplatesPath() {
		return reportService.getTemplatesPath();
	}

}
