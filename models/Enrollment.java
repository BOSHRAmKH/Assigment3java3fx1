package models;

import java.time.LocalDate;

public class Enrollment {
    private int studentId;
    private int courseId;
    private LocalDate enrollmentDate;

    public Enrollment(int studentId, int courseId, LocalDate enrollmentDate) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
    }

  
    public int getStudentId() { return studentId; }
    public int getCourseId() { return courseId; }
    public LocalDate getEnrollmentDate() { return enrollmentDate; }


    public void setStudentId(int studentId) { this.studentId = studentId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }
    public void setEnrollmentDate(LocalDate enrollmentDate) { this.enrollmentDate = enrollmentDate; }
}