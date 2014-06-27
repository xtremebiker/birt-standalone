package org.birtserver.renderer.download;

import java.io.InputStream;

public class GenericDownloadReport extends DownloadReport{

	public GenericDownloadReport(InputStream stream, String name, String extension){
		super(stream, name, extension);
	}
}
