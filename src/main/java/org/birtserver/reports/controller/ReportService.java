package org.birtserver.reports.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.birtserver.reports.renderer.GenericReportInput;
import org.birtserver.reports.renderer.LocalReportRenderer;
import org.birtserver.reports.renderer.ReportInput;
import org.birtserver.reports.renderer.ReportPath;
import org.birtserver.reports.renderer.AbstractReportRenderer.ReportFileType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class ReportService implements IReportService {

	@Autowired
	private LocalReportRenderer _ReportRenderer;

	private Logger _Logger = LoggerFactory.getLogger(ReportService.class);

	private String _TemplatesPath = "/home/birtserver/templates/";

	@PostConstruct
	public void postConstruct() {
		new File(_TemplatesPath).mkdirs();
		_ReportRenderer.set_RepositoryReports(_TemplatesPath);
		_Logger.info(
				"{} folder has been stabished as BIRT template source directory",
				_TemplatesPath);
	}

	@RequestMapping(value = "/report", method = RequestMethod.GET, headers = "Accept=application/xml, application/json")
	@ResponseBody
	public ResponseEntity<byte[]> renderReport(@RequestParam String template,
			@RequestParam(required = false) String locale,
			@RequestParam(required = false) String outputType,
			@RequestParam Map<String, String> allRequestParams)
			throws Exception {
		if (locale == null) {
			locale = "es";
		}
		if (outputType == null) {
			outputType = "PDF";
		}
		Map<String, Object> params = new HashMap<String, Object>();
		for (String param : allRequestParams.keySet()) {
			params.put(param, allRequestParams.get(param));
		}
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		List<ReportInput> inputList = new ArrayList<ReportInput>();
		inputList.add(new GenericReportInput(new ReportPath(template
				+ ".rptdesign"), params, new Locale(locale)));
		ReportFileType outputFileType = ReportFileType.valueOf(outputType);
		this._ReportRenderer.render(inputList, stream, outputFileType);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType(outputFileType
				.equals(ReportFileType.PDF) ? "application/pdf"
				: "application/vnd.ms-excel"));
		return new ResponseEntity<byte[]>(stream.toByteArray(), headers,
				HttpStatus.CREATED);
	}

	@Override
	public String get_TemplatesPath() {
		return _TemplatesPath;
	}

	@Override
	public void set_TemplatesPath(String path) {
		_TemplatesPath = path;
	}
}
