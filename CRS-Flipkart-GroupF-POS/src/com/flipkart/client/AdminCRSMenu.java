/**
 *
 */
package com.flipkart.client;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Professor;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.constant.GenderConstant;
import com.flipkart.constant.NotificationTypeConstant;
import com.flipkart.constant.RoleConstant;
import com.flipkart.exception.CourseExistsAlreadyException;
import com.flipkart.exception.CourseNotDeletedException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.ProfessorNotAddedException;
import com.flipkart.exception.StudentNotFoundForApprovalException;
import com.flipkart.exception.UserIdAlreadyInUseException;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.service.AdminInterface;
import com.flipkart.service.AdminOperation;
import com.flipkart.service.NotificationInterface;
import com.flipkart.service.NotificationOperation;
import com.flipkart.service.RegistrationInterface;
import com.flipkart.service.RegistrationOperation;

public class AdminCRSMenu {

    AdminInterface adminOperation = AdminOperation.getInstance();
    Scanner in = new Scanner(System.in);
    NotificationInterface notificationInterface = NotificationOperation.getInstance();
    RegistrationInterface registrationInterface = RegistrationOperation.getInstance();

    /**
     * Method to Create Admin Menu
     */
    public void createMenu() {

        while (CRSApplication.loggedin) {
//            System.out.println("------------------------------------------------------------");
            System.out.println("-------------------------- ADMIN MENU --------------------------");
//            System.out.println("------------------------------------------------------------");
            System.out.println("0. Logout");
            System.out.println("1. View courses in catalog");
            System.out.println("2. Add course to catalog");
            System.out.println("3. Delete Course from catalog");
            System.out.println("4. Approve Students");
            System.out.println("5. Approve all students");
            System.out.println("6. View Pending Approvals");
            System.out.println("7. Add Professor");
            System.out.println("8. Assign a new professor to a course");
            System.out.println("9. Generate Report Card");
//            System.out.println("------------------------------------------------------------");
            System.out.print("Enter operation: ");
            int choice = in.nextInt();

            switch (choice) {
                case 1:
                    viewCoursesInCatalog();
                    break;

                case 2:
                    addCourseToCatalog();
                    break;

                case 3:
                    removeCourse();
                    break;

                case 4:
                    approveStudent();
                    break;

                case 5:
                    approveAll();

                case 6:
                    viewPendingAdmissions();
                    break;

                case 7:
                    addProfessor();
                    break;

                case 8:
                    assignCourseToProfessor();
                    break;

                case 9:
                    generateReportCard();
                    break;

                case 0:
                    CRSApplication.loggedin = false;
                    return;

                default:
                    System.out.println("***** Wrong Choice *****");
            }
        }
    }

    /**
     * generate report card for a student
     */
    private void generateReportCard() {

        List<Grade> grade_card = null;
        boolean isReportGenerated = true;

        Scanner in = new Scanner(System.in);

        System.out.println("\nEnter the StudentId for report card generation : ");
        String studentId = in.next();

        try {
            adminOperation.setGeneratedReportCardTrue(studentId);
            if (isReportGenerated) {
                grade_card = registrationInterface.viewGradeCard(studentId);
                System.out.println(String.format("%-20s %-20s %-20s", "COURSE CODE", "COURSE NAME", "GRADE"));

                if (grade_card.isEmpty()) {
                    System.out.println("You haven't registered for any course");
                    return;
                }

                for (Grade obj : grade_card) {
                    System.out.println(String.format("%-20s %-20s %-20s", obj.getCrsCode(), obj.getCrsName(), obj.getGrade()));
                }
            } else
                System.out.println("Report card not yet generated");
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }


    }


    /**
     * Method to assign Course to a Professor
     */
    private void assignCourseToProfessor() {
        List<Professor> professorList = adminOperation.viewProfessors();
        System.out.println("------------------------ Professor ------------------------");
        System.out.println(String.format("%20s | %20s | %20s ", "ProfessorId", "Name", "Designation"));
        for (Professor professor : professorList) {
            System.out.println(String.format("%20s | %20s | %20s ", professor.getUserId(), professor.getName(), professor.getDesignation()));
        }


        System.out.println("\n\n");
        List<Course> courseList = adminOperation.viewCourses();
        System.out.println("-------------------------- Course --------------------------");
        System.out.println(String.format("%20s | %20s | %20s", "CourseCode", "CourseName", "ProfessorId"));
        for (Course course : courseList) {
            System.out.println(String.format("%20s | %20s | %20s", course.getCourseCode(), course.getCourseName(), course.getInstructorId()));
        }

        System.out.print("Enter Course Code: ");
        String courseCode = in.next();

        System.out.print("Enter Professor's User Id: ");
        String userId = in.next();

        try {

            adminOperation.assignCourse(courseCode, userId);
            System.out.println("Professor " + userId + " assigned to course " + courseCode + " successfully");

        } catch (CourseNotFoundException | UserNotFoundException e) {

            System.out.println(e.getMessage());
        }

    }

    /**
     * Method to add Professor to DB
     */
    private void addProfessor() {

        System.out.print("Enter User ID: ");
        String userId = in.next();
        Professor professor = new Professor(userId);

        System.out.print("Enter Professor Name: ");
        String professorName = in.next();
        professor.setName(professorName);

        System.out.print("Enter Department: ");
        String department = in.next();
        professor.setDepartment(department);

        System.out.print("Enter Designation: ");
        String designation = in.next();
        professor.setDesignation(designation);

        System.out.print("Enter Password: ");
        String password = in.next();
        professor.setPassword(password);

        System.out.print("Enter Gender: \t 1: Male \t 2.Female \t 3.Other: ");
        int gender = in.nextInt();

        if (gender == 1)
            professor.setGender(GenderConstant.MALE);
        else if (gender == 2)
            professor.setGender(GenderConstant.FEMALE);
        else if (gender == 3)
            professor.setGender(GenderConstant.OTHER);

        System.out.print("Enter Address: ");
        String address = in.next();
        professor.setAddress(address);

        professor.setRole(RoleConstant.PROFESSOR);

        try {
            adminOperation.addProfessor(professor);
            System.out.println("Professor added successfully");
        } catch (ProfessorNotAddedException | UserIdAlreadyInUseException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Method to view Students who are yet to be approved
     *
     * @return List of Students whose admissions are pending
     */
    private List<Student> viewPendingAdmissions() {

        List<Student> pendingStudentsList = adminOperation.viewPendingAdmissions();
        if (pendingStudentsList.size() == 0) {
//            System.out.println("No students pending approvals");
            return pendingStudentsList;
        }
        System.out.println(String.format("%20s | %20s | %20s", "StudentId", "Name", "GenderConstant"));
        for (Student student : pendingStudentsList) {
            System.out.println(String.format("%20s | %20s | %20s", student.getStudentId(), student.getName(), student.getGender().toString()));
        }
        return pendingStudentsList;
    }

    /**
     * Method to approve a Student using Student's ID
     */
    private void approveStudent() {

        List<Student> studentList = viewPendingAdmissions();
        if (studentList.size() == 0) {

            System.out.println("No pending requests");
            return;
        }

        System.out.print("Enter Student's ID: ");
        String studentUserIdApproval = in.next();


        try {
            adminOperation.approveStudent(studentUserIdApproval, studentList);
            System.out.println("\nStudent Id : " + studentUserIdApproval + " has been approved\n");
            //send notification from system
            notificationInterface.sendNotification(NotificationTypeConstant.REGISTRATION, studentUserIdApproval, null, 0);

        } catch (StudentNotFoundForApprovalException e) {
            System.out.println(e.getMessage());
        }


    }

    /**
     * Approves all pending student requests
     */
    private void approveAll(){
        List<Student> studentList = viewPendingAdmissions();
        if (studentList.size() == 0) {
            System.out.println("No pending requests");
            return;
        }

//        System.out.print("Enter Student's ID: ");
//        String studentUserIdApproval = in.next();

//        for(Student student: studentList){
            try {
//                String studentUserIdApproval = student.getStudentId();
                adminOperation.approveAll(studentList);
//                System.out.println("\nStudent Id : " + studentUserIdApproval + " has been approved\n");
                //send notification from system
//                notificationInterface.sendNotification(NotificationTypeConstant.REGISTRATION, studentUserIdApproval, null, 0);

            } catch (StudentNotFoundForApprovalException e) {
                System.out.println(e.getMessage());
            }


//        }
    }

    /**
     * Method to delete Course from catalog
     *
     * @throws CourseNotFoundException
     */
    private void removeCourse() {
        List<Course> courseList = viewCoursesInCatalog();
        System.out.print("Enter Course Code: ");
        String courseCode = in.next();

        try {
            adminOperation.removeCourse(courseCode, courseList);
            System.out.println("\nCourse Deleted : " + courseCode + "\n");
        } catch (CourseNotFoundException e) {

            System.out.println(e.getMessage());
        } catch (CourseNotDeletedException e) {

            System.out.println(e.getMessage());
        }
    }

    /**
     * Method to add Course to catalog
     *
     * @throws CourseExistsAlreadyException course already exists
     */
    private void addCourseToCatalog() {
        List<Course> courseList = viewCoursesInCatalog();

        in.nextLine();
        System.out.print("Enter Course Code: ");
        String courseCode = in.nextLine();

        System.out.print("Enter Course Name: ");
        String courseName = in.next();

        System.out.print("Enter Professor ID: ");
        String profId = in.next();


        Course course = new Course(courseCode, courseName, profId, 10);
        course.setCourseCode(courseCode);
        course.setCourseName(courseName);
        course.setSeats(10);
        course.setInstructorId(profId);
//		System.out.println("AdminCRSMenu 321");

        try {
            adminOperation.addCourse(course, courseList);
            System.out.println("Course added to catalog successfully");
        } catch (CourseExistsAlreadyException e) {
            System.out.println("Course already existed " + e.getMessage());
        }

    }

    /**
     * Method to display courses in catalog
     * @return List of courses in catalog
     */
    private List<Course> viewCoursesInCatalog() {
        List<Course> courseList = adminOperation.viewCourses();
        if (courseList.size() == 0) {
            System.out.println("Nothing present in the catalog!");
            return courseList;
        }
        System.out.println(String.format("%20s | %20s | %20s", "COURSE CODE", "COURSE NAME", "INSTRUCTOR"));
        for (Course course : courseList) {
            System.out.println(String.format("%20s | %20s | %20s", course.getCourseCode(), course.getCourseName(), course.getInstructorId()));
        }
        return courseList;
    }
}
