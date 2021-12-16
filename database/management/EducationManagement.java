package database.management;

import database.entity.Course;
import database.entity.Education;
import database.entity.Student;

import javax.persistence.*;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static database.schoolpackage.Utility.*;


public class EducationManagement {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");

    public static void educationMenu() {

        boolean loop = true;

        while (loop) {
            System.out.println("What would you like to do?");
            System.out.println("1. Show all education");
            System.out.println("2. Create a education");
            System.out.println("3. Remove a education");
            System.out.println("4. Update a education");
            System.out.println("5. Show specific education");
            System.out.println("6. Show education and the courses");
            System.out.println("7. Show education and the students");
            System.out.println("0. Back to the main menu");
            int choice = readInt();

            switch (choice) {
                case 1:
                    showAllEducations();
                    pressEnter();
                    break;
                case 2:
                    createEducation();
                    break;
                case 3:
                    removeEducation();
                    break;
                case 4:
                    updateEducation();
                    break;
                case 5:
                    showSpecificEducationById();
                    break;
                case 6:
                    showEducationAndItsCoursesByID();
                case 0:
                    loop = false;
                    break;
            }
        }
    }

    public static void showAllEducations() {
        EntityManager em = emf.createEntityManager();
        em.createQuery("SELECT e FROM Education e", Education.class)
                .getResultStream()
                .forEach(System.out::println);

    }

    public static void showSpecificEducationById() {
        EntityManager em = emf.createEntityManager();
        System.out.println("Enter the ID of the of the Education:");
        Education e = em.find(Education.class, readInt());
        System.out.println(e);
        pressEnter();
    }

    public static void createEducation() {

        EntityManager em = emf.createEntityManager();

        System.out.println("Enter education name: ");
        String name = readString();
        System.out.println("Enter yearly length");
        int yLength = readInt();

        Education e = new Education(name, yLength);

        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
        em.close();
        pressEnter();

    }

    private static void removeEducation() {
        EntityManager em = emf.createEntityManager();
        showAllEducations();
        System.out.println("Enter the ID of the education you want to remove:");
        Education educationToRemove = em.find(Education.class, readInt());

        em.getTransaction().begin();
        educationToRemove.setStudents(null);
        em.remove(educationToRemove);
        em.getTransaction().commit();
        em.close();
        System.out.println(educationToRemove + " IS REMOVED");
        pressEnter();

    }

    private static void updateEducation() {
        EntityManager em = emf.createEntityManager();
        showAllEducations();
        System.out.println("Enter education-ID to update:");

        Education educationToUpdate = em.find(Education.class, readInt());

        System.out.println("Enter the new education name:");
        String newName = readString();
        System.out.println("Enter the yearly length:");
        int newYearlyLength = readInt();

        em.getTransaction().begin();
        educationToUpdate.setName(newName);
        educationToUpdate.setYearlyLength(newYearlyLength);
        em.getTransaction().commit();
        em.close();
        System.out.println(educationToUpdate + " IS UPDATED");
        pressEnter();
    }

    private static void showEducationAndItsCoursesByID() {
        showAllEducations();
        EntityManager em = emf.createEntityManager();
        System.out.println("Write the ID of the education you want to show:");
        Education e = em.find(Education.class, readInt());
        System.out.println(e);

        List<Course> courses = e.getCourses();
        for (Course c : courses) {
            System.out.println(c);
        }
        pressEnter();

    }

    private static void showEducationAndTheStudentsByID() {
        showAllEducations();
        EntityManager em = emf.createEntityManager();
        System.out.println("Write the ID of the education you want to show:");
        Education e = em.find(Education.class, readInt());
        System.out.println(e);

        List<Student> students = e.getStudents();
        for (Student s : students) {
            System.out.println(s);
        }
        pressEnter();

    }
}
