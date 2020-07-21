package com.cycloid.epg.domain.exception;

public class ExistsException  extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExistsException() {
		super("Resource already exist");
	}
	
}
