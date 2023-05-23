/**
 *
 */
package com.flipkart.service;

import java.sql.SQLException;
import java.util.List;


import com.flipkart.bean.*;
import com.flipkart.exception.GradeNotAllotedException;

public interface ProfessorInterface {

    /**
     * method to add a grade for a student in a particular course
     * @param studentID student id
     * @param courseID course id
     * @param grade grade
     * @return Returns the status of the operation
     * @throws GradeNotAllotedException grade not alotted exception
     */
    public boolean addGrade(String studentID, String courseID, String grade) throws GradeNotAllotedException;

    /**
     * Method to view all courses offered by a professor
     * @param profID professor id
     * @return list of courses
     */
    public List<Course> viewCourses(String profID);

    /**
     * Method to get the professor name with ID
     * @param profId professor id
     * @return Professor name
     */
    String getProfessorById(String profId);

    /**
     * Method to view all the enrolled students
     * @param profId: professor id
     * @return List of enrolled students
     * @throws SQLException sql exception
     */
    public List<EnrolledStudent> viewEnrolledStudents(String profId) throws SQLException;


}
