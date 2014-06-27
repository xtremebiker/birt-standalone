package org.birtserver.renderer.download;

import java.io.InputStream;

public class XlsDownloadReport extends DownloadReport{

	public XlsDownloadReport(InputStream stream, String name){
		super(stream, name, ".pdf");
	}
	
}
