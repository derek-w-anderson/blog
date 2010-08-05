package com.appspot.blog.exceptions;

public class InvalidFieldException extends ModelException
{
	private static final long serialVersionUID = 7468599926109708157L;
	
	private String fieldName;
	private String description;
	
	public InvalidFieldException(String fieldName, String description) {
		super();
		this.fieldName = fieldName;
		this.description = description;
	}
	
	public String toString() {
		return "The " + fieldName + " you specified is invalid. " + description;
	}
}
