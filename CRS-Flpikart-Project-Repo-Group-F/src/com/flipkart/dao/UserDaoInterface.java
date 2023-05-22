package com.flipkart.dao;

import com.flipkart.exception.UserNotFoundException;


public interface UserDaoInterface {
	
	/**
	 * Method to verify credentials of Users from DataBase
	 * @param userId
	 * @param password
	 * @return Verify credentials operation status
	 * @throws UserNotFoundException
	 */
	public boolean verifyCredentials(String userId,String password) throws UserNotFoundException;
	
	/**
	 * Method to update password of user in DataBase
	 * @param userID
	 * @return Update Password operation Status
	 */
	//TODO Duplicate Function. Please remove it if its not necessary
	public boolean updatePassword(String userID);
	
	/**
	 * Method to get RoleConstant of User from DataBase
	 * @param userId user id
	 * @return RoleConstant role constant
	 */
	public String getRole(String userId);
	
	
	/**
	 * Method to update password of user in DataBase
	 * @param userID user id
	 * @param newPassword new password
	 * @return Update Password operation Status
	 */
	public boolean updatePassword(String userID,String newPassword);
}