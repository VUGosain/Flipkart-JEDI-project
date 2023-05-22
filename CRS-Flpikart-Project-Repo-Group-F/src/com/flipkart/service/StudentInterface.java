package com.flipkart.service;


import com.flipkart.constant.GenderConstant;
import com.flipkart.exception.StudentNotRegisteredException;


public interface StudentInterface {
	
	/**
	 * Method to register a student, although student can't login until it's approved by admin
	 * @param name name
	 * @param userID user id
	 * @param password password
	 * @param gender gender
	 * @param batch batch
	 * @param branch branch
	 * @param address address
	 * @return Student ID
	 * @throws StudentNotRegisteredException student not registered
	 */
	public String register(String name,String userID,String password,GenderConstant gender,int batch,String branch,String address) throws StudentNotRegisteredException; 
	
	/**
	 * Method to get Student ID from User ID
	 * @param userId
	 * @return Student ID
	 */
	public String getStudentId(String userId);
	
	/**
     * Method to check if student is approved by Admin or not
     * @param studentId
     * @return boolean indicating if student is approved
     */
    public boolean isApproved(String studentId);
}