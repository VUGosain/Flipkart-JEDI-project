package com.flipkart.exception;


public class CourseNotAvailableException extends Exception {

    private String courseCode;

    /**
     * Constructor
     *
     * @param courseCode course code
     */
    public CourseNotAvailableException(String courseCode) {
        this.courseCode = courseCode;
    }


    /**
     * Message returned when exception is thrown
     */
    @Override
    public String getMessage() {
        return "Seats are not available in : " + courseCode;
    }


}