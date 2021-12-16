package database.management;

import database.entity.Course;
import database.entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static database.schoolpackage.Utility.*;
import static database.management.CourseManagement.*;

public class TeacherManagement {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");

    public static void teacherMenu() {
        boolean loop = true;
        while (loop) {
            System.out.println("What would you like to do?");
            System.out.println("1. Show all teachers");
            System.out.println("2. Create a teacher ");
            System.out.println("3. Remove a teacher");
            System.out.println("4. Update a teacher");
            System.out.println("5. Add existing teacher to existing course");
            System.out.println("6. Show a specific teacher and courses");
            System.out.println("0. Back to the main menu");
            int choice = readInt();

            switch (choice) {
                case 1:
                    showAllTeachers();
                    pressEnter();
                    break;
                case 2:
                    createTeacher();
                    break;
                case 3:
                    removeTeacher();
                    break;
                case 4:
                    updateTeachers();
                    break;
                case 5:
                    addExistingTeacherToExistingCourse();
                    break;
                case 6:
                    showSpecificTeacherAndCourses();
                    break;
                case 0:
                    loop = false;
                    break;
            }
        }
    }

    public static void showAllTeachers() {
        EntityManager em = emf.createEntityManager();
        em.createQuery("SELECT t FROM Teacher t", Teacher.class)
                .getResultStream()
                .forEach(System.out::println);
    }

    public static void showSpecificTeacherAndCourses() {

        EntityManager em = emf.createEntityManager();

        showAllTeachers();
        System.out.println("Enter the ID of the teacher:");
        Teacher t = em.find(Teacher.class, readInt());

        System.out.println(t);
        List<Course> courses = t.getCourses();
        for (Course c : courses) {
            System.out.println(c);
        }
        pressEnter();
    }


    public static void createTeacher() {
        EntityManager em = emf.createEntityManager();
        System.out.println("Enter teacher name:");
        String name = readString();
        Teacher t = new Teacher(name);

        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        em.close();


        pressEnter();
    }

    public static void removeTeacher() {
        EntityManager em = emf.createEntityManager();
        showAllTeachers();
        System.out.println("Enter the ID of the teacher you want to remove:");

        Teacher teacherToRemove = em.find(Teacher.class, readInt());
        em.getTransaction().begin();
        em.remove(teacherToRemove);
        em.getTransaction().commit();
        em.close();
        System.out.println(teacherToRemove + " IS REMOVED");
        pressEnter();
    }

    public static void updateTeachers() {
        EntityManager em = emf.createEntityManager();
        showAllTeachers();
        System.out.println("Enter teacher ID to update:");

        Teacher teacherToUpdate = em.find(Teacher.class, readInt());

        System.out.println("Enter the new name:");
        String newName = readString();

        em.getTransaction().begin();
        teacherToUpdate.setName(newName);
        em.getTransaction().commit();
        em.close();
        System.out.println(teacherToUpdate + " IS UPDATED");
        pressEnter();
    }

    public static void addExistingTeacherToExistingCourse() {

        EntityManager em = emf.createEntityManager();
        showAllTeachers();
        System.out.println("Enter the ID of the teacher you want to add to the course:");
        Teacher teacherToAdd = em.find(Teacher.class, readInt());

        showAllCourses();
        System.out.println("Enter the ID of the course you want to add the teacher to:");
        Course courseToAdd = em.find(Course.class, readInt());

        em.getTransaction().begin();
        teacherToAdd.addCourse(courseToAdd);
        em.getTransaction().commit();
        em.close();
        System.out.println(teacherToAdd + " IS ADDED TO " + courseToAdd);
        pressEnter();

    }
}
