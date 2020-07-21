package com.cycloid.epg.domain.exception;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotFoundException(Long id) {
		super("Could not find resource " + id);
	}
}
