package domain;

import java.time.LocalDateTime;

public class Register {
    //Atributos: id(int), registerDate(LocalDateTime), studentId(String), courseId(String)
    private int id;
    private LocalDateTime registerDate;
    private String studentId;
    private String studentName;
    private String courseId;
    private String courseName;
    private int courseCredit;

    public Register(int id, LocalDateTime registerDate, String studentId,String studentName, String courseId,String courseName, int courseCredit) {
        this.id = id;
        this.registerDate = registerDate;
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseCredit = courseCredit;
    }

    public Register(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCredits() {
        return courseCredit;
    }
    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Register{" +
                "id=" + id +
                ", registerDate=" + registerDate +
                ", studentId='" + studentId + '\'' +
                ", courseId='" + courseId + '\'' +
                '}';
    }
}