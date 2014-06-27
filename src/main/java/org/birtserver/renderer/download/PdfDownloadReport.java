package org.birtserver.renderer.download;

import java.io.InputStream;

public class PdfDownloadReport extends DownloadReport{

	public PdfDownloadReport(InputStream stream, String name){
		super(stream, name, ".pdf");
	}
	
}
