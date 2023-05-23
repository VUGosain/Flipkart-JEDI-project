package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import org.apache.log4j.Logger;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.constant.SQLQueriesConstant;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.utils.DBUtils;


public class RegistrationDaoOperation implements RegistrationDaoInterface {


    private static volatile RegistrationDaoOperation instance = null;
    //	private static Logger logger = Logger.getLogger(RegistrationDaoOperation.class);
    private PreparedStatement stmt = null;

    /**
     * Default Constructor
     */
    private RegistrationDaoOperation() {
    }

    /**
     * Method to make RegistrationDaoOperation Singleton
     * @return returns an object of RegistrationDaoOperation
     */
    public static RegistrationDaoOperation getInstance() {
        if (instance == null) {
            synchronized (RegistrationDaoOperation.class) {
                instance = new RegistrationDaoOperation();
            }
        }
        return instance;
    }


    /**
     * Method to register a student to a course
     * @param courseCode course code
     * @param studentId student id
     * @return Returns the status of the operation (registered/ encountered some error)
     * @throws SQLException sql exception
     */
    @Override
    public boolean addCourse(String courseCode, String studentId) throws SQLException {

        Connection conn = DBUtils.getConnection();

        try {
            stmt = conn.prepareStatement(SQLQueriesConstant.ADD_COURSE);
            stmt.setString(1, studentId);
            stmt.setString(2, courseCode);
            stmt.setString(3, null);
            stmt.executeUpdate();

            stmt = conn.prepareStatement(SQLQueriesConstant.DECREMENT_COURSE_SEATS);
            stmt.setString(1, courseCode);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
//			logger.info(e.getMessage());
        } finally {
            stmt.close();
            conn.close();
        }

        System.out.println("hello");
        return false;

    }


    /**
     * Number of registered courses for a student
     * @param studentId student id
     * @return Number of registered courses for a student
     * @throws SQLException sql exception
     */
    @Override
    public int numOfRegisteredCourses(String studentId) throws SQLException {

        Connection conn = DBUtils.getConnection();

        int count = 0;
        try {

            stmt = conn.prepareStatement(SQLQueriesConstant.NUMBER_OF_REGISTERED_COURSES);
            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                count++;
            }
            return count;

        } catch (SQLException se) {

//			logger.error(se.getMessage());

        } catch (Exception e) {

//			logger.error(e.getMessage());
        } finally {
            stmt.close();
            conn.close();
        }

        return count;
    }


    /**
     * Check if seat is available for that particular course
     * @param courseCode course code
     * @return status of seat availablity
     * @throws SQLException sql exception
     */
    @Override
    public boolean seatAvailable(String courseCode) throws SQLException {

        Connection conn = DBUtils.getConnection();
        try {
            stmt = conn.prepareStatement(SQLQueriesConstant.GET_SEATS);
            stmt.setString(1, courseCode);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                return (rs.getInt("seats") > 0);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt.close();
            conn.close();
        }

        return true;


    }


    /**
     * Method checks if the student is registered for that course
     * @param courseCode course code
     * @param studentId student id
     * @return Students registration status
     * @throws SQLException sql exception
     */
    @Override
    public boolean isRegistered(String courseCode, String studentId) throws SQLException {

        Connection conn = DBUtils.getConnection();

        boolean check = false;
        try {
            stmt = conn.prepareStatement(SQLQueriesConstant.IS_REGISTERED);
            stmt.setString(1, courseCode);
            stmt.setString(2, studentId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                check = true;
            }
        } catch (Exception e) {
//			logger.error(e.getClass());
//			logger.error(e.getMessage());
        } finally {
            stmt.close();
            conn.close();
        }

        return check;

    }


    /**
     * Drop Course selected by student
     * @param courseCode : code for selected course
     * @param studentId  student id
     * @return status of drop course operation
     * @throws SQLException sql exception
     */
    @Override
    public boolean dropCourse(String courseCode, String studentId) throws SQLException {

        Connection conn = DBUtils.getConnection();


        try {
            stmt = conn.prepareStatement(SQLQueriesConstant.DROP_COURSE_QUERY);
            stmt.setString(1, courseCode);
            stmt.setString(2, studentId);
            stmt.execute();

            stmt = conn.prepareStatement(SQLQueriesConstant.INCREMENT_SEAT_QUERY);
            stmt.setString(1, courseCode);
            stmt.execute();

            stmt = conn.prepareStatement(SQLQueriesConstant.DE_REGISTER_QUERY);
            stmt.setString(1, studentId);
            stmt.execute();

            stmt.close();

            return true;
        } catch (Exception e) {
//				logger.error("Exception found" + e.getMessage());
        } finally {

            stmt.close();
            conn.close();
        }


        return false;

    }

    /**
     * Method to retrieve fee for the selected courses from the database and calculate total fee
     * @param studentId student id
     * @return Fee Student has to pay
     * @throws SQLException sql exception
     */

    @Override
    public double calculateFee(String studentId) throws SQLException {
        Connection conn = DBUtils.getConnection();
        double fee = 0;
        try {
            stmt = conn.prepareStatement(SQLQueriesConstant.CALCULATE_FEES);
            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            fee = rs.getDouble(1);
        } catch (SQLException e) {
//			logger.error(e.getErrorCode());
//			logger.error(e.getMessage());
        } catch (Exception e) {
//			logger.error(e.getMessage());
        } finally {
            stmt.close();
            conn.close();
        }

        return fee;
    }

    /**
     * Method to view grade card of the student
     * @param studentId student id
     * @return Studen's grade card
     * @throws SQLException sql exception
     */
    @Override
    public List<Grade> viewGradeCard(String studentId) throws SQLException {

        Connection conn = DBUtils.getConnection();
        List<Grade> grade_List = new ArrayList<Grade>();
        try {
            stmt = conn.prepareStatement(SQLQueriesConstant.VIEW_GRADE);
            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String courseCode = rs.getString("courseCode");
                String courseName = rs.getString("courseName");
                String grade = rs.getString("grade");
                Grade obj = new Grade(courseCode, courseName, grade);
                grade_List.add(obj);
            }
        } catch (SQLException e) {
//			logger.error(e.getMessage());
        } catch (Exception e) {
//			logger.error(e.getMessage());
        } finally {
            stmt.close();
            conn.close();

        }

        return grade_List;
    }

    /**
     * Method to get the list of courses available from course catalog
     * @param studentId student id
     * @return list of courses
     * @throws SQLException sql exception
     */
    @Override
    public List<Course> viewCourses(String studentId) throws SQLException {

        List<Course> availableCourseList = new ArrayList<>();
        Connection conn = DBUtils.getConnection();

        try {
            stmt = conn.prepareStatement(SQLQueriesConstant.VIEW_AVAILABLE_COURSES);
            stmt.setString(1, studentId);
            //stmt.setBoolean(2, true);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                availableCourseList.add(new Course(rs.getString("courseCode"), rs.getString("courseName"),
                        rs.getString("professorId"), rs.getInt("seats")));

            }


        } catch (SQLException e) {
//			logger.error(e.getMessage());
        } catch (Exception e) {
//			logger.error(e.getMessage());
        } finally {
            stmt.close();
            conn.close();
        }

        return availableCourseList;

    }

    /**
     * Method to get the list of courses registered by the student
     * @param studentId student id
     * @return list of courses registered by student
     * @throws SQLException sql exception
     */
    @Override
    public List<Course> viewRegisteredCourses(String studentId) throws SQLException {

        Connection conn = DBUtils.getConnection();
        List<Course> registeredCourseList = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(SQLQueriesConstant.VIEW_REGISTERED_COURSES);
            stmt.setString(1, studentId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                registeredCourseList.add(new Course(rs.getString("courseCode"), rs.getString("courseName"),
                        rs.getString("professorId"), rs.getInt("seats")));

            }
        } catch (SQLException e) {
//			logger.error(e.getMessage());

        } finally {
            stmt.close();
            conn.close();
        }

        return registeredCourseList;
    }

    /**
     * Method to retrieve Student's registration status
     *
     * @param studentId student id
     * @return Student's registration status
     * @throws SQLException sql exception
     */
    @Override
    public boolean getRegistrationStatus(String studentId) throws SQLException {
        Connection conn = DBUtils.getConnection();
        boolean status = false;
        try {
            stmt = conn.prepareStatement(SQLQueriesConstant.GET_REGISTRATION_STATUS);
            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            status = rs.getBoolean(1);
            //System.out.println(status);
        } catch (SQLException e) {
//			logger.error(e.getMessage());

        } finally {
            stmt.close();
            conn.close();
        }

        return status;
    }

    /**
     * Method to set Student's registration status
     * @param studentId student id
     * @throws SQLException sql exception
     */
    @Override
    public void setRegistrationStatus(String studentId) throws SQLException {
        Connection conn = DBUtils.getConnection();
        try {
            stmt = conn.prepareStatement(SQLQueriesConstant.SET_REGISTRATION_STATUS);
            stmt.setString(1, studentId);
            stmt.executeUpdate();

        } catch (SQLException e) {
//			logger.error(e.getMessage());

        } finally {
            stmt.close();
            conn.close();
        }

    }

    /**
     * Method to check the status of a reported (is generated or not)
     * @param studentId student id
     * @return Returns a boolean corresponding to the status
     * @throws SQLException sql exception
     */
    @Override
    public boolean isReportGenerated(String studentId) throws SQLException {
        Connection conn = DBUtils.getConnection();
        boolean status = false;
        try {
            stmt = conn.prepareStatement(SQLQueriesConstant.GET_GENERATED_REPORT_CARD_TRUE);
            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            status = rs.getBoolean(1);
            //System.out.println(status);
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            stmt.close();
            conn.close();
        }

        return status;
    }

    /**
     * Method to get payment status of a student
     * @param studentId student id
     * @return Returns a boolean corresponding to the status of the payment
     * @throws SQLException sql exception
     */
    @Override
    public boolean getPaymentStatus(String studentId) throws SQLException {
        {
            Connection conn = DBUtils.getConnection();
            boolean status = false;
            try {
                stmt = conn.prepareStatement(SQLQueriesConstant.GET_PAYMENT_STATUS);
                stmt.setString(1, studentId);
                ResultSet rs = stmt.executeQuery();
                rs.next();
                status = rs.getBoolean(1);
                //System.out.println(status);
            } catch (SQLException e) {
//				logger.error(e.getMessage());

            } finally {
                stmt.close();
                conn.close();
            }

            return status;
        }


    }

    /**
     * Method to make and record a payment by a student for a semester
     * @param studentId student id
     * @param mode mode
     * @param fee fee
     * @throws SQLException sql exception
     */
    @Override
    public void makePayment(String studentId, String mode, double fee) throws SQLException {
        Connection conn = DBUtils.getConnection();
        try {
            String ref_id = studentId + "_" + (new Date()).getTime();
            stmt = conn.prepareStatement(SQLQueriesConstant.INSERT_NOTIFICATION);
            stmt.setString(1, studentId);
            stmt.setString(2, mode);
            stmt.setString(3, ref_id);
            stmt.executeUpdate();


            stmt = conn.prepareStatement(SQLQueriesConstant.INSERT_PAYMENT);
            stmt.setString(1, studentId);
            stmt.setString(2, mode);
            stmt.setString(3, ref_id);
            stmt.setString(4, Double.toString(fee));
            stmt.executeUpdate();

            stmt = conn.prepareStatement(SQLQueriesConstant.SET_PAYMENT_STATUS);
            stmt.setString(1, studentId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
//			logger.error(e.getMessage());

        } finally {
            stmt.close();
            conn.close();
        }

    }
}