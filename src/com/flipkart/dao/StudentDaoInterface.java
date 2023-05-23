/**
 *
 */
package com.flipkart.dao;

import java.sql.SQLException;

import com.flipkart.bean.Student;
import com.flipkart.exception.StudentNotRegisteredException;


public interface StudentDaoInterface {


    /**
     * Method to add student to database
     * @param student: student object containing all the fields
     * @return true if student is added, else false
     * @throws StudentNotRegisteredException student not registerd
     */
    public String addStudent(Student student) throws StudentNotRegisteredException;


    /**
     * Method to retrieve Student Id from User Id
     * @param userId user id
     * @return Student Id
     */
    public String getStudentId(String userId);

    /**
     * Method to check if Student is approved
     * @param studentId student id
     * @return boolean indicating if student is approved
     */
    public boolean isApproved(String studentId);
}