package com.flipkart.exception;


public class StudentIdAlreadyInUseException extends Exception{
	private String StudentId;
	
	
	/***
	 * Setter function for ProfessorId\
	 */
	
	public StudentIdAlreadyInUseException(String id) {
		StudentId = id;
	}
	
	/***
	 * Getter function for ProfessorId\
	 */
	
	public String getUserId() {
		return StudentId;
	}
	
	
	@Override
	public String getMessage() {
		return "userId: " + StudentId + " is already in use.";
	}

}