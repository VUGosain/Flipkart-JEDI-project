/**
 *
 */
package com.flipkart.dao;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.exception.CourseExistsAlreadyException;
import com.flipkart.exception.CourseNotDeletedException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.ProfessorNotAddedException;
import com.flipkart.exception.StudentNotFoundForApprovalException;
import com.flipkart.exception.UserIdAlreadyInUseException;
import com.flipkart.exception.UserNotAddedException;
import com.flipkart.exception.UserNotFoundException;


public interface AdminDaoInterface {

    public List<Course> viewCourses();

    public List<Professor> viewProfessors();


    /**
     * Method to generate grade card of a Student
     * Studentid user id of the student
     *
     *
     */
    public void setGeneratedReportCardTrue(String Studentid);

    public List<RegisteredCourse> generateGradeCard(String Studentid);

    /**
     * Fetch Students yet to approved using SQL commands
     * @return List of Students yet to approved
     */
    public List<Student> viewPendingAdmissions();

    /**
     * Method to approve a Student
     * studentid
     * studentlist
     */


    public void approveStudent(String studentid) throws StudentNotFoundForApprovalException;

    /**
     * Method to add Professor to DB
     * professor : Professor Object storing details of a professor
     */
    public void addProfessor(Professor professor) throws ProfessorNotAddedException, UserIdAlreadyInUseException;

    /**
     * Method to Delete Course from Course Catalog
     * @param coursecode course code
     * @throws CourseNotFoundException course not found
     * @throws CourseNotDeletedException  unable to delete course
     */

    public void removeCourse(String coursecode) throws CourseNotFoundException, CourseNotDeletedException;

    /**
     * Method to add Course to Course Catalog
     * @param course : Course object storing details of a course
     * @throws CourseExistsAlreadyException course already exists
     */

    public void addCourse(Course course) throws CourseExistsAlreadyException;

    /**
     * Method to assign Course to a Professor
     * @param courseCode
     * @param professorId
     * @throws CourseNotFoundException course not found
     * @throws UserNotFoundException user not found
     */

    /**
     * Method to assign a course to another professor
     * @param courseCode course code
     * @param professorId user id of the professor
     * @throws UserNotFoundException user not found
     * @throws CourseNotFoundException course not found
     */
    public void assignCourse(String courseCode, String professorId) throws CourseNotFoundException, UserNotFoundException;

    /**
     * @param user user object
     * @throws UserNotAddedException user not added
     * @throws UserIdAlreadyInUseException user id already in use
     */
    public void addUser(User user) throws UserNotAddedException, UserIdAlreadyInUseException;
}
