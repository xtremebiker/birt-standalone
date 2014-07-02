package org.birtserver.reports.renderer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.eclipse.birt.report.engine.api.EXCELRenderOption;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.EngineException;
import org.eclipse.birt.report.engine.api.IRenderOption;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.birt.report.engine.api.RenderOption;
import org.eclipse.birt.report.engine.api.ReportEngine;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;

/**
 * Class for report rendering
 * 
 * @author Aritz
 *
 */
public abstract class AbstractReportRenderer {

	private final Log logger = LogFactory.getLog(this.getClass());

	public abstract String getRootDirectoryPath();

	public static enum ReportFileType {
		PDF("pdf"), XLS("xls");

		private String typeValue;

		public String getTypeValue() {
			return this.typeValue;
		}

		private ReportFileType(String typeValue) {
			this.typeValue = typeValue;
		}
	}

	public void renderReport(ReportPath reportPath,
			Map<String, Object> reportParams, Locale locale,
			OutputStream outputStream, ReportFileType reportFileType)
			throws EngineException {
		this.logger.info("Generating report with template in "
				+ this.getRootDirectoryPath() + reportPath.getPath());
		try {
			ReportEngine engine = new ReportEngine(new EngineConfig());

			// open design document
			IReportRunnable runnable = engine.openReportDesign(this
					.getRootDirectoryPath() + reportPath.getPath());

			IRunAndRenderTask task = engine.createRunAndRenderTask(runnable);

			for (Entry<String, Object> ent : reportParams.entrySet()) {
				task.setParameterValue(ent.getKey(), ent.getValue());
			}

			task.setLocale(locale);

			if (reportFileType == ReportFileType.XLS) {
				final EXCELRenderOption options = new EXCELRenderOption();

				options.setOutputFormat(reportFileType.getTypeValue());
				options.setOutputStream(outputStream);

				task.setRenderOption(options);
			} else {
				final IRenderOption options = new RenderOption();

				options.setOutputFormat(reportFileType.getTypeValue());
				options.setOutputStream(outputStream);

				task.setRenderOption(options);
			}

			task.run();
			// close task and doc
			task.close();

		} catch (EngineException e) {

			this.logger.error("Exception while proceessing report ", e);
			throw e;

		}
	}

	private void doMergePDF(Collection<InputStream> list,
			OutputStream outputStream) throws DocumentException, IOException {
		Document document = new Document();
		PdfCopy copy = new PdfCopy(document, outputStream);
		document.open();
		int n;
		for (InputStream in : list) {
			PdfReader reader = new PdfReader(in);

			n = reader.getNumberOfPages();
			// loop over the pages in that document
			for (int page = 0; page < n;) {
				copy.addPage(copy.getImportedPage(reader, ++page));
			}
			copy.freeReader(reader);
			reader.close();

		}
		outputStream.flush();
		document.close();
		outputStream.close();
	}

	private void doMergeXLS(Map<String, InputStream> list,
			OutputStream outputStream) throws Exception {

		HSSFWorkbook wb = new HSSFWorkbook();

		for (Entry<String, InputStream> entry : list.entrySet()) {
			HSSFWorkbook temp = new HSSFWorkbook(entry.getValue());

			HSSFSheet sheet = wb.createSheet(entry.getKey());

			UtilsHSSFWorkbook.copySheets(sheet, temp.getSheetAt(0));
		}

		wb.write(outputStream);
		outputStream.close();

	}

	/**
	 * Processes some report entries returning them in a single pdf file
	 * 
	 * @param reportPath
	 * @param reportParams
	 * @param locale
	 * @return
	 * @throws EngineException
	 * @throws IOException
	 * @throws DocumentException
	 * @throws WriteException
	 * @throws BiffException
	 */
	public void render(List<ReportInput> reportParams,
			OutputStream outputStream, ReportFileType reportFileType)
			throws Exception {

		if (reportParams.size() == 1) {
			renderReport(reportParams.get(0).getReportPath(),
					reportParams.get(0).getReportParams(), reportParams.get(0)
							.getLocale(), outputStream, reportFileType);
		} else if (reportParams.size() > 1) {

			Map<String, InputStream> streamMap = new HashMap<String, InputStream>();
			List<InputStream> streamList = new ArrayList<InputStream>();
			for (ReportInput reportEntry : reportParams) {

				ByteArrayOutputStream output = new ByteArrayOutputStream();
				renderReport(reportEntry.getReportPath(),
						reportEntry.getReportParams(), reportEntry.getLocale(),
						output, reportFileType);

				if (reportEntry instanceof XlsReportInput) {
					XlsReportInput xls = (XlsReportInput) reportEntry;
					streamMap.put(xls.getNameSheet(), new ByteArrayInputStream(
							output.toByteArray()));
					streamList.add(new ByteArrayInputStream(output
							.toByteArray()));
				} else {
					streamMap.put(reportEntry.toString(),
							new ByteArrayInputStream(output.toByteArray()));
					streamList.add(new ByteArrayInputStream(output
							.toByteArray()));
				}

			}
			if (streamList.size() > 1) {
				switch (reportFileType) {
				case PDF:
					doMergePDF(streamList, outputStream);
					break;
				case XLS:
					doMergeXLS(streamMap, outputStream);
					break;
				}
			}
		} else {

		}

	}

}
