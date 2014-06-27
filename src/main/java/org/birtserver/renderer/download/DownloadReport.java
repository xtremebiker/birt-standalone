package org.birtserver.renderer.download;

import java.io.InputStream;

public abstract class DownloadReport {

	/**
	 * Input Stream
	 */
	private InputStream _Stream;
	
	/**
	 * Extension
	 */
	private String _Extension;
	
	/**
	 * Name of file to download (without extension)
	 */
	private String _Name;
	
	public DownloadReport(InputStream stream, String name, String extension){
		this._Stream = stream;
		this._Name = name;
		this._Extension = extension;
	}

	public InputStream get_Stream() {
		return _Stream;
	}

	public String get_Extension() {
		return _Extension;
	}

	public String get_NameFile() {
		return _Name + this._Extension;
	}
	
}
