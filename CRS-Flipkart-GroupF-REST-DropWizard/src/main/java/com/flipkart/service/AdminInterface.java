/**
 *
 */
package com.flipkart.service;

import java.util.List;

import com.flipkart.exception.*;


import com.flipkart.bean.*;


public interface AdminInterface {

    public void setGeneratedReportCardTrue(String Studentid);

    /**
     * Method to get list of courses in catalog
     * @return List of courses in catalog
     */
    public List<Course> viewCourses();

    /**
     * View professor in the institute
     * @return List of the professors in the institute
     */
    public List<Professor> viewProfessors();

    /**
     * Method to view Students yet to be approved by Admin
     * @return List of Students with pending admissions
     */
    public List<Student> viewPendingAdmissions();

    /**
     * Method to generate grade card of a Student
     * studentid
     * @return
     */
    public List<RegisteredCourse> generateGradeCard(String Studentid);

    /**
     * method to approve student registration
     * @param studentid student id
     * @param studentlist list of students
     * @throws StudentNotFoundForApprovalException student not found
     */
    public void approveStudent(String studentid, List<Student> studentlist) throws StudentNotFoundForApprovalException;

    /**
     * Approves all pending student requests
     * @param studentList list of students to be approved
     * @throws StudentNotFoundForApprovalException student not found
     */
    public void approveAll(List<Student> studentList) throws StudentNotFoundForApprovalException;

    /**
     * @param professor professor object
     * @throws ProfessorNotAddedException professor not added
     * @throws UserIdAlreadyInUseException user id already in use
     */

    /**
     * Adds a new professor
     * @param professor professor object
     * @throws ProfessorNotAddedException unable to add professor
     * @throws UserIdAlreadyInUseException user id already in use
     */

    public void addProfessor(Professor professor) throws ProfessorNotAddedException, UserIdAlreadyInUseException;

    /**
     * Method to Delete Course from Course Catalog
     * @param coursecode course code
     * @param courselist : Courses available in the catalog
     * @throws CourseNotFoundException course not found exception
     * @throws CourseNotDeletedException course not deleted
     */

    public void removeCourse(String coursecode, List<Course> courselist) throws CourseNotFoundException, CourseNotDeletedException;

    /**
     * Method to add Course to Course Catalog
     * @param course : Course object storing details of a course
     * @param courselist : Courses available in the catalog
     * @throws CourseExistsAlreadyException course already exists
     */

    public void addCourse(Course course, List<Course> courselist) throws CourseExistsAlreadyException;

    /**
     * Method to assign Course to a Professor
     * @param courseCode course code
     * @param professorId professor id
     * @throws CourseNotFoundException course not found
     * @throws UserNotFoundException user not found
     */
    public void assignCourse(String courseCode, String professorId) throws CourseNotFoundException, UserNotFoundException;

}
