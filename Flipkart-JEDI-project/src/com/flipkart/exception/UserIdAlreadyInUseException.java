package com.flipkart.exception;


public class UserIdAlreadyInUseException extends Exception {
    private String userId;


    /***
     * Setter function for UserId
     * @param id
     */

    public UserIdAlreadyInUseException(String id) {
        userId = id;
    }

    /***
     * Getter function for UserId
     */

    public String getUserId() {
        return userId;
    }


    @Override
    public String getMessage() {
        return "userId: " + userId + " is already in use.";
    }

}