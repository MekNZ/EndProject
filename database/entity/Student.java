package database.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int age;

    @ManyToOne
    private Education education;

    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void addEducation(Education e) {
        education.addStudent(this);
    }

    public void removeEducation(Education e) {
        e.getStudents().remove(null);
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    @Override
    public String toString() {
        return "ID: "+ id + " || "+ name +" || "+ "Age: " + age + " || " ;
    }
}


/*
package database.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    private int age;

    @ManyToMany(mappedBy = "students", cascade = CascadeType.PERSIST)
    private List<database.entity.Course> courses = new ArrayList<>();


    public void addCourse(database.entity.Course c) {
        courses.add(c);
        c.getStudents().add(this);
    }

    public void removeCourse(database.entity.Course c) {
        courses.remove(c);
        c.getStudents().remove(this);
    }


    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public List<database.entity.Course> getCourses() {
        return courses;
    }

    public void setCourses(List<database.entity.Course> courses) {
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
*/