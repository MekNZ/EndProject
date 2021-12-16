package database.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import database.entity.Education;
import database.entity.Student;

@Entity
public class Course {

    @Id
    @GeneratedValue
    private int id;

    private String name;
    private int weeklyLength;

    @ManyToOne
    private Teacher teacher;

    @ManyToMany(mappedBy = "courses")
    private List<Education> educations;

    public Course() {
    }

    public Course(String name, int weeklyLength) {
        this.name = name;
        this.weeklyLength = weeklyLength;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeeklyLength() {
        return weeklyLength;
    }

    public void setWeeklyLength(int weeklyLength) {
        this.weeklyLength = weeklyLength;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Education> getEducations() {
        if (educations == null) {
            educations = new ArrayList<>();
        }
        return educations;
    }

    public void setEducations(List<Education> educations) {
        this.educations = educations;
    }

    public void addEducation(Education education) {
        getEducations().add(education);
    }

    public void removeEducation(Education education) {
        getEducations().remove(education);
    }
    @Override
    public String toString() {
        return "ID: "+ id + " || "+"Course: " + name +" || "+ "Duration: " + weeklyLength + " weeks || " ;
    }
}

/*package database.entity;

import database.entity.Education;
import database.entity.Student;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue
    private int id;

    private String courseName;

    private int weeklyLength;

    @ManyToMany
    private List<Student> students = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Education education;

    public void addStudent(Student s) {
        students.add(s);
        s.getCourses().add(this);

    }

    public void removeStudent(database.entity.Student s) {
        students.remove(s);
        s.getCourses().remove(this);
    }

    public void addEducation(database.entity.Education e){
        e.getCourses().add(this);
    }

    public void removeEducation(database.entity.Education e){
        e.removeCourse(this);
    }

    public Course() {
    }

    public Course(String courseName, int weeklyLength) {
        this.courseName = courseName;
        this.weeklyLength = weeklyLength;
    }

    public database.entity.Education getEducation() {
        return education;
    }

    public void setEducation(database.entity.Education education) {
        this.education = education;
    }

    public List<database.entity.Student> getStudents() {
        return students;
    }

    public int getWeeklyLength() {
        return weeklyLength;
    }

    public void setWeeklyLength(int weeklyLength) {
        this.weeklyLength = weeklyLength;
    }

    public void setStudents(List<database.entity.Student> students) {
        this.students = students;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", weeklyLength=" + weeklyLength +
                '}';
    }
}*/