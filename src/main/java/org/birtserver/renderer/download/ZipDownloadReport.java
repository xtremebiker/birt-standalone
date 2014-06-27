package org.birtserver.renderer.download;

import java.io.InputStream;

public class ZipDownloadReport extends DownloadReport {

	public ZipDownloadReport(InputStream stream, String name) {
		super(stream, name, ".zip");
	}

}
