package org.birtserver.reports.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

import org.birtserver.reports.controller.IReportService;
import org.springframework.stereotype.Component;

@Component
@ViewScoped
public class ViewController {

	public class UrlParam {

		private String name;

		private String value;

		public UrlParam() {

		}

		public UrlParam(String name, String value) {
			this.name = name;
			this.value = value;
		}

		public String getFormattedParam() {
			return name + "=" + value;
		}

		public String getName() {
			return name;
		}

		public String getValue() {
			return value;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

	private List<UrlParam> urlParams = new ArrayList<UrlParam>();

	@Inject
	private IReportService reportService;

	private String reportTemplate = "test_report";

	private String reportUrl;

	private void createReportUrl() {
		reportUrl = "http://localhost:8080/birt-standalone/service/report?";
		reportUrl += "template=" + reportTemplate;
		for (UrlParam urlParam : urlParams) {
			reportUrl += "&";
			reportUrl += urlParam.getFormattedParam();
		}
		System.out.println("Rendering report for " + reportUrl);
	}

	public void addParameter() {
		urlParams.add(new UrlParam());
	}

	public void removeParameter(UrlParam param) {
		urlParams.remove(param);
	}

	public String getReportTemplate() {
		return reportTemplate;
	}

	public String getReportUrl() {
		return reportUrl;
	}

	public String getTemplatesPath() {
		return reportService.getTemplatesPath();
	}

	public List<UrlParam> getUrlParams() {
		return urlParams;
	}

	@PostConstruct
	public void postConstruct() {
		urlParams.add(new UrlParam("param1", "Hello world!"));
		createReportUrl();
	}

	public void renderReportFrame() {
		createReportUrl();
	}

	public void setReportTemplate(String reportTemplate) {
		this.reportTemplate = reportTemplate;
	}

}
