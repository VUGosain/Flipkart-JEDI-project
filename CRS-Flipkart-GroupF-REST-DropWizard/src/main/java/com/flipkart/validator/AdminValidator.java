/**
 *
 */
package com.flipkart.validator;

import java.util.List;

import com.flipkart.bean.*;


public class AdminValidator {


    /**
     * Method to validate if newCourse is not already present in catalog
     * @param newCourse new course
     * @param courseList course list
     * @return if newCourse is not already present in catalog
     */
    public static boolean isValidNewCourse(Course newCourse, List<Course> courseList) {
        for (Course course : courseList) {
            if (newCourse.getCourseCode().equalsIgnoreCase(course.getCourseCode())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method to validate if dropCourse is already present in catalog
     * @param dropCourseCode drop course code
     * @param courseList course list
     * @return if dropCourse is already present in catalog
     */
    public static boolean isValidDropCourse(String dropCourseCode, List<Course> courseList) {
        for (Course course : courseList) {
            if (dropCourseCode.equalsIgnoreCase(course.getCourseCode())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to validate if studentId is still unapproved
     * @param studentId student id
     * @param studentList student list
     * @return if studentId is still unapproved
     */
    public static boolean isValidUnapprovedStudent(String studentId, List<Student> studentList) {
        for (Student student : studentList) {
            if (studentId == student.getStudentId()) {
                return true;
            }
        }
        return false;
    }
}
