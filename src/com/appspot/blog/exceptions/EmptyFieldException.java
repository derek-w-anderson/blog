package com.appspot.blog.exceptions;

public class EmptyFieldException extends ModelException
{
	private static final long serialVersionUID = -8575767605404228769L;

	private String fieldName;
	
	public EmptyFieldException(String fieldName) {
		super();
		this.fieldName = fieldName;
	}
	
	public String toString() {
		return "The " + fieldName + " field cannot be left empty.";
	}
}
