package com.flipkart.service;

import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Notification;
import com.flipkart.bean.Grade;
import com.flipkart.constant.PaymentModeConstant;
import com.flipkart.dao.RegistrationDaoInterface;
import com.flipkart.dao.RegistrationDaoOperation;
import com.flipkart.exception.CourseLimitExceededException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.SeatNotAvailableException;
import com.flipkart.validator.StudentValidator;


public class RegistrationOperation implements RegistrationInterface {

    private static volatile RegistrationOperation instance = null;

    private RegistrationOperation() {
    }

    /**
     * Method to make Registration Operation Singleton
     * @return Returns an object of RegistrationOperation class
     */
    public static RegistrationOperation getInstance() {
        if (instance == null) {
            synchronized (RegistrationOperation.class) {
                instance = new RegistrationOperation();
            }
        }
        return instance;
    }

    RegistrationDaoInterface registrationDaoInterface = RegistrationDaoOperation.getInstance();

    /**
     * Method to add Course selected by student
     *
     * @param courseCode          course code
     * @param studentId           student id
     * @param availableCourseList list of available courses
     * @return boolean indicating if the course is added successfully
     * @throws CourseNotFoundException      course not found exception
     * @throws SeatNotAvailableException    seat not available
     * @throws CourseLimitExceededException course limit exceeded
     * @throws SQLException                 sql exception
     */
    @Override
    public boolean addCourse(String courseCode, String studentId, List<Course> availableCourseList) throws CourseNotFoundException, CourseLimitExceededException, SeatNotAvailableException, SQLException {

//		System.out.println("this has to be there");

        if (registrationDaoInterface.numOfRegisteredCourses(studentId) >= 4) {
            throw new CourseLimitExceededException(4);
        } else if (registrationDaoInterface.isRegistered(courseCode, studentId)) {
            return false;
        } else if (!registrationDaoInterface.seatAvailable(courseCode)) {
            throw new SeatNotAvailableException(courseCode);
        } else if (!StudentValidator.isValidCourseCode(courseCode, availableCourseList)) {
            throw new CourseNotFoundException(courseCode);
        }


        return registrationDaoInterface.addCourse(courseCode, studentId);

    }

    /**
     * Method to drop Course selected by student
     * @param courseCode course code
     * @param studentId student id
     * @param registeredCourseList list of registered courses
     * @return boolean indicating if the course is dropped successfully
     * @throws CourseNotFoundException course not found exception
     * @throws SQLException sql exception
     */
    @Override

    public boolean dropCourse(String courseCode, String studentId, List<Course> registeredCourseList) throws CourseNotFoundException, SQLException {
        if (!StudentValidator.isRegistered(courseCode, studentId, registeredCourseList)) {
            throw new CourseNotFoundException(courseCode);
        }

        return registrationDaoInterface.dropCourse(courseCode, studentId);

    }

    /**
     * Method for Fee Calculation for selected courses
     * Fee calculation for selected courses
     * @param studentId student id
     * @return Fee Student has to pay
     * @throws SQLException sql exception
     */
    @Override

    public double calculateFee(String studentId) throws SQLException {
        return registrationDaoInterface.calculateFee(studentId);
    }


    /**
     * Method to view grade card for students
     * @param studentId student id
     * @return List of Student's Grades
     * @throws SQLException sql exception
     */
    @Override

    public List<Grade> viewGradeCard(String studentId) throws SQLException {
        return registrationDaoInterface.viewGradeCard(studentId);
    }

    /**
     * Method to view the list of available courses
     *
     * @param studentId student id
     * @return List of courses
     * @throws SQLException sql exception
     */
    @Override

    public List<Course> viewCourses(String studentId) throws SQLException {
        return registrationDaoInterface.viewCourses(studentId);
    }

    /**
     * Method to view the list of courses registered by the student
     * @param studentId student id
     * @return List of courses
     * @throws SQLException sql exception
     */
    @Override
    public List<Course> viewRegisteredCourses(String studentId) throws SQLException {
        return registrationDaoInterface.viewRegisteredCourses(studentId);
    }

    /**
     * Method to check student registration status
     *
     * @param studentId student id
     * @return boolean indicating if the student's registration status
     * @throws SQLException sql exception
     */
    @Override

    public boolean getRegistrationStatus(String studentId) throws SQLException {
        return registrationDaoInterface.getRegistrationStatus(studentId);
    }

    /**
     * Method to set student registration status
     *
     * @param studentId student id
     * @throws SQLException sql exception
     */
    @Override

    public void setRegistrationStatus(String studentId) throws SQLException {
        registrationDaoInterface.setRegistrationStatus(studentId);

    }

    /**
     * Method to check the status of report card generation
     * @param studentId student id
     * @return Returns a boolean corresponding to the status
     * @throws SQLException sql exception
     */
    @Override
    public boolean isReportGenerated(String studentId) throws SQLException {

        return registrationDaoInterface.isReportGenerated(studentId);
    }

    /**
     * Method to get payment status
     * @param studentId student id
     * @return Returns a boolean corresponing to the payment status
     * @throws SQLException sql exception
     */
    @Override
    public boolean getPaymentStatus(String studentId) throws SQLException {
        return registrationDaoInterface.getPaymentStatus(studentId);

    }

    /**
     * Method to make and record a payment
     * @param studentId student id
     * @param mode mode
     * @param fee fee
     * @throws SQLException sql exception
     */
    @Override
    public void makePayment(String studentId, String mode, double fee) throws SQLException {
        registrationDaoInterface.makePayment(studentId, mode, fee);

    }

}