package database.management;

import database.entity.Education;
import database.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static database.schoolpackage.Utility.*;
import static database.management.CourseManagement.*;

public class StudentManagement {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");

    public static void studentMenu() {
        boolean loop = true;
        while (loop) {
            System.out.println("What would you like to do?");
            System.out.println("1. Show all students");
            System.out.println("2. Create a student ");
            System.out.println("3. Remove a student");
            System.out.println("4. Update a student");
            System.out.println("5. Add existing student to existing education");
            System.out.println("6. Show a specific student and education");
            System.out.println("0. Back to the main menu");
            int choice = readInt();

            switch (choice) {
                case 1:
                    showAllStudents();
                    pressEnter();
                    break;
                case 2:
                    createStudent();
                    break;
                case 3:
                    removeStudent();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    addExistingStudentToExistingEducation();
                    break;
                case 6:
                    showSpecificStudentAndEducation();
                    break;
                case 0:
                    loop = false;
                    break;
            }
        }
    }

    public static void showAllStudents() {
        EntityManager em = emf.createEntityManager();
        em.createQuery("SELECT s FROM Student s", Student.class)
                .getResultStream()
                .forEach(System.out::println);
    }

    public static void showSpecificStudentAndEducation(){
        EntityManager em = emf.createEntityManager();
        showAllStudents();
        System.out.println("Enter the ID of the student:");
        Student s = em.find(Student.class, readInt());
        Education se = s.getEducation();

        System.out.println(s +"  "+ se);
        pressEnter();
    }


    public static void createStudent() {
        EntityManager em = emf.createEntityManager();
        System.out.println("Enter student name:");
        String name = readString();
        System.out.println("Enter student age:");
        int age = readInt();
        Student s = new Student(name, age);

        em.getTransaction().begin();
        em.persist(s);
        em.getTransaction().commit();
        em.close();


        pressEnter();
    }

    public static void removeStudent() {
        EntityManager em = emf.createEntityManager();
        showAllStudents();
        System.out.println("Enter the ID of the student you want to remove:");

        Student studentToRemove = em.find(Student.class, readInt());
        em.getTransaction().begin();
        em.remove(studentToRemove);
        em.getTransaction().commit();
        em.close();
        System.out.println(studentToRemove + " IS REMOVED");
        pressEnter();
    }

    public static void updateStudent() {
        EntityManager em = emf.createEntityManager();
        showAllStudents();
        System.out.println("Enter student ID to update:");

        Student studentToUpdate = em.find(Student.class, readInt());

        System.out.println("Enter the new name:");
        String newName = readString();
        System.out.println("Enter the new age:");
        int newAge = readInt();

        em.getTransaction().begin();
        studentToUpdate.setName(newName);
        studentToUpdate.setAge(newAge);
        em.getTransaction().commit();
        em.close();
        System.out.println(studentToUpdate + " IS UPDATED");
        pressEnter();
    }

    public static void addExistingStudentToExistingEducation() {

        EntityManager em = emf.createEntityManager();
        showAllStudents();
        System.out.println("Enter the ID of the student you want to add to the education:");
        Student studentToAdd = em.find(Student.class, readInt());

        EducationManagement.showAllEducations();
        System.out.println("Enter the ID of the education you want to add the student to:");
        Education educationToAdd = em.find(Education.class, readInt());

        em.getTransaction().begin();
        educationToAdd.addStudent(studentToAdd);
        em.getTransaction().commit();
        em.close();
        System.out.println(studentToAdd +  " IS ADDED TO " + educationToAdd);
        pressEnter();

    }
}
