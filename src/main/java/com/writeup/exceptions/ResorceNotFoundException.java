package com.writeup.exceptions;

public class ResorceNotFoundException extends RuntimeException {
	String resorceName;
	String fieldName;
	long fieldValue;

	public ResorceNotFoundException(String resorceName, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s : %s", resorceName ,fieldName,fieldValue));
		this.resorceName = resorceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
}
