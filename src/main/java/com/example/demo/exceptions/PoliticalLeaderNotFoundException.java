package com.example.demo.exceptions;

public class PoliticalLeaderNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PoliticalLeaderNotFoundException() {
		super();
	}

	public PoliticalLeaderNotFoundException(String message) {
		super(message);
	}

}
