/**
 *
 */
package com.flipkart.service;

import com.flipkart.exception.UserNotFoundException;


public interface UserInterface {


    /**
     * Method to get role of a specific User
     * @param userId user id
     * @return RoleConstant of the User
     */
    String getRole(String userId);

    /**
     * Method to verify User credentials
     * @param userID user id
     * @param password password
     * @return boolean indicating if user exists in the database
     */
    boolean verifyCredentials(String userID, String password) throws UserNotFoundException;

    /**
     * Method to update password of a user
     * @param userID user id
     * @param newPassword password
     * @return boolean indicating if the password is updated successfully
     */
    boolean updatePassword(String userID, String newPassword);

}
