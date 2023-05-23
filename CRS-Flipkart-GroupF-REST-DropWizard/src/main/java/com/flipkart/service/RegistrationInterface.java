package com.flipkart.service;

import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Notification;
import com.flipkart.bean.Grade;
import com.flipkart.constant.PaymentModeConstant;

import com.flipkart.exception.CourseLimitExceededException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.SeatNotAvailableException;


public interface RegistrationInterface {

    public boolean addCourse(String courseCode, String studentId, List<Course> availableCourseList)
            throws CourseNotFoundException, CourseLimitExceededException, SeatNotAvailableException, SQLException;

    /**
     * Method to set student registration status
     * @param studentId student id
     * @throws SQLException sql exception
     */
    void setRegistrationStatus(String studentId) throws SQLException;

    /**
     * Method to check student registration status
     *
     * @param studentId student id
     * @return boolean indicating if the student's registration status
     * @throws SQLException sql exception
     */
    boolean getRegistrationStatus(String studentId) throws SQLException;

    /**
     * Method to get the payment status of a student
     * @param studentId student id
     * @return Returns a boolean value of the payment status
     * @throws SQLException sql exception
     */
    boolean getPaymentStatus(String studentId) throws SQLException;

    /**
     * Method to view the list of courses registered by the student
     * @param studentId student id
     * @return List of courses
     * @throws SQLException sql exception
     */
    List<Course> viewRegisteredCourses(String studentId) throws SQLException;

    /**
     * Method to view the list of available courses
     * @param studentId student id
     * @return List of courses
     * @throws SQLException sql exception
     */
    List<Course> viewCourses(String studentId) throws SQLException;

    /**
     * Method to view grade card for students
     * @param studentId student id
     * @return List of Student's Grades
     * @throws SQLException sql exception
     */
    List<Grade> viewGradeCard(String studentId) throws SQLException;

    /**
     * Method for Fee Calculation for selected courses
     * Fee calculation for selected courses
     * @param studentId student id
     * @return Fee Student has to pay
     * @throws SQLException sql exception
     */
    double calculateFee(String studentId) throws SQLException;

    /**
     * Method to drop Course selected by student
     * @param courseCode course code
     * @param studentId student id
     * @param registeredCourseList list of registered courses
     * @return boolean indicating if the course is dropped successfully
     * @throws CourseNotFoundException course not found exception
     * @throws SQLException sql exception
     */
    boolean dropCourse(String courseCode, String studentId, List<Course> registeredCourseList)
            throws CourseNotFoundException, SQLException;

    /**
     * Method to check the status of the generation of report card
     * @param studentId student id
     * @return Returns a boolean corresponding the the status
     * @throws SQLException sql exception
     */
    public boolean isReportGenerated(String studentId) throws SQLException;

    /**
     * Method to make and record a payment
     * @param studentId student id
     * @param mode mode
     * @param fee fee
     * @throws SQLException sql exception
     */
    public void makePayment(String studentId, String mode, double fee) throws SQLException;

//	public void setPaymentStatus(String studentId);

}