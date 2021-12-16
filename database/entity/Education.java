package database.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Education {

    @Id
    @GeneratedValue
    private int id;

    private String name;
    private int yearlyLength;

    @OneToMany(mappedBy = "education")
    private List<Student> students = new ArrayList<>();

    @ManyToMany
    private List<Course> courses = new ArrayList<>();

    public Education(String name, int yearlyLength) {
        this.name = name;
        this.yearlyLength = yearlyLength;
    }

    public Education() {
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

    public int getYearlyLength() {
        return yearlyLength;
    }

    public void setYearlyLength(int yearlyLength) {
        this.yearlyLength = yearlyLength;
    }

    public List<Student> getStudents() {
        if (students == null) {
            students = new ArrayList<>();
        }
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void addStudent(Student s) {
        students.add(s);
        s.setEducation(this);
    }

    public void removeStudent(Student s){
        getStudents().remove(s);

    }

    public List<Course> getCourses() {
        if (courses == null) {
            courses = new ArrayList<>();
        }
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(Course course) {
        getCourses().add(course);
        course.getEducations().add(this);
    }

    public void removeCourse(Course course) {
        getCourses().remove(course);
        course.getEducations().remove(this);
    }

    @Override
    public String toString() {
        return "ID: " + id + " || " + "Education: " + name + " || " + "Duration: " + yearlyLength + " years || ";
    }

}


/*
package database.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Education {
    @Id
    @GeneratedValue
    private int id;

    private String educationName;

    private int yearlyLength;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<database.entity.Course> courses = new ArrayList<>();

    public void addCourse(database.entity.Course c){
        courses.add(c);
        c.getEducation().addCourse(c);
    }

    public void removeCourse(database.entity.Course c){
        courses.remove(c);
    }

    public Education() {
    }

    public Education(String educationName, int yearlyLength) {
        this.educationName = educationName;
        this.yearlyLength = yearlyLength;
    }

    public List<database.entity.Course> getCourses() {
        return courses;
    }

    public void setCourses(List<database.entity.Course> courses) {
        this.courses = courses;
    }

    public int getYearlyLength() {
        return yearlyLength;
    }

    public void setYearlyLength(int yearlyLength) {
        this.yearlyLength = yearlyLength;
    }

    public String getEducationName() {
        return educationName;
    }

    public void setEducationName(String educationName) {
        this.educationName = educationName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Education{" +
                "id=" + id +
                ", educationName='" + educationName + '\'' +
                ", yearlyLength=" + yearlyLength +
                '}';
    }
}
*/