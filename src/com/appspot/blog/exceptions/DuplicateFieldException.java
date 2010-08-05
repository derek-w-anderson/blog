package com.appspot.blog.exceptions;

public class DuplicateFieldException extends ModelException
{
	private static final long serialVersionUID = 2152831520237285496L;

	private String modelName;
	private String fieldName;
	
	public DuplicateFieldException(String modelName, String fieldName) {
		super();
		this.modelName = modelName;
		this.fieldName = fieldName;
	}
	
	public String toString() {
		return "Sorry, but a " + modelName + " with that " + fieldName + " already exists.";
	}
}
