/**
 * 
 */
package com.flipkart.exception;


public class GradeNotAllotedException extends Exception{
	 
	private String studentId;
	 
	/**
	 * Constructor
	 * @param studentId2 student id
	 */
	 public GradeNotAllotedException(String studentId2)
	 {
		 this.studentId=studentId2;
	 }
	 
	 /**
	  * Getter function for studentId
	  */
	 public String getStudentId()
	 {
		 return studentId;
	 }
	 
	 /**
		 * Message returned when exception is thrown
	 */
	 
	 public String getMessage() 
	 {
			return "Student with id: " + studentId + "hasn't been alloted a grade yet";
	 }
}
