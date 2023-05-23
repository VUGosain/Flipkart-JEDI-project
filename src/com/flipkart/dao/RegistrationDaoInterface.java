package com.flipkart.dao;


import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;


public interface RegistrationDaoInterface {

    /**
     * Method to add course in database
     * @param courseCode course code
     * @param studentId student id
     * @return boolean indicating if the course is added successfully
     */
    public boolean addCourse(String courseCode, String studentId) throws SQLException;


    /**
     * Drop Course selected by student
     * @param courseCode couse code
     * @param studentId student id
     * @return boolean indicating if the course is dropped successfully
     */

    public boolean dropCourse(String courseCode, String studentId) throws SQLException;

    /**
     * Method to get the list of courses available from course catalog
     * @param studentId student id
     * @return list of Courses
     */
    public List<Course> viewCourses(String studentId) throws SQLException;


    /**
     * Method to View list of Registered Courses
     * @param studentId student id
     * @return list of Registered Courses
     */
    public List<Course> viewRegisteredCourses(String studentId) throws SQLException;


    /**
     * Method to view grade card of the student
     * @param studentId student id
     * @return GradeConstant Card
     */
    public double calculateFee(String studentId) throws SQLException;

    /**
     * Check if seat is available for that particular course
     * @param courseCode course code
     * @return seat availability status
     */
    public boolean seatAvailable(String courseCode) throws SQLException;

    /**
     * Method to get the list of courses registered by the student
     * Number of registered courses for a student
     * @param studentId student id
     * @return Number of registered Courses
     */
    public int numOfRegisteredCourses(String studentId) throws SQLException;

    /**
     * Method checks if the student is registered for that course
     *
     * @param courseCode course code
     * @param studentId student id
     * @return Students registration status
     */
    public boolean isRegistered(String courseCode, String studentId) throws SQLException;


    /**
     * Method to get student registration status
     * @param studentId student id
     * @return Student's registration status
     */
    public boolean getRegistrationStatus(String studentId) throws SQLException;

    public boolean getPaymentStatus(String studentId) throws SQLException;

    /**
     * Method to set student registration status
     * @param studentId student id
     */
    public void setRegistrationStatus(String studentId) throws SQLException;


    /**
     * Method to view grade card of the student
     * @param studentId student id
     * @return Studen's grade card
     * @throws SQLException sql expression
     */
    public List<Grade> viewGradeCard(String studentId) throws SQLException;


    /**
     * Method to check if the report card generated for a student
     * @param studentId student id
     * @return boolean for the status of generation
     * @throws SQLException sql expression
     */
    public boolean isReportGenerated(String studentId) throws SQLException;


    /**
     * method to make and record a payment by a student for a semester
     * @param studentId student id
     * @param mode mode
     * @param fee fee
     * @throws SQLException sql exception
     */
    public void makePayment(String studentId, String mode, double fee) throws SQLException;


}
