package dao;

import config.DatabaseConfig;
import models.Enrollment;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO {

    public boolean addEnrollment(Enrollment enrollment) {
        String query = "INSERT INTO enrollments (student_id, course_id, enrollment_date) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, enrollment.getStudentId());
            stmt.setInt(2, enrollment.getCourseId());
            stmt.setDate(3, Date.valueOf(enrollment.getEnrollmentDate()));
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


   public List<Enrollment> getAllEnrollments() {
    List<Enrollment> list = new ArrayList<>();
    String query = "SELECT * FROM enrollments";
    try (Connection conn = DatabaseConfig.getConnection()) {
      
        System.out.println("Connected to: " + conn.getCatalog()); 
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                list.add(new Enrollment(
                    rs.getInt("student_id"),
                    rs.getInt("course_id"),
                    rs.getDate("enrollment_date").toLocalDate()
                ));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}

   
    public boolean deleteEnrollment(int studentId, int courseId) {
        String query = "DELETE FROM enrollments WHERE student_id = ? AND course_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, studentId);
            pstmt.setInt(2, courseId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { 
            e.printStackTrace(); 
            return false; 
        }
    }


    public boolean updateEnrollment(int oldSId, int oldCId, int newSId, int newCId, LocalDate newDate) {
        String query = "UPDATE enrollments SET student_id = ?, course_id = ?, enrollment_date = ? WHERE student_id = ? AND course_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, newSId);
            pstmt.setInt(2, newCId);
            pstmt.setDate(3, java.sql.Date.valueOf(newDate));
            pstmt.setInt(4, oldSId);
            pstmt.setInt(5, oldCId);
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { 
            e.printStackTrace(); 
            return false; 
        }
    }
}