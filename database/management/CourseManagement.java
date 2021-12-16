package database.management;

import database.entity.Course;
import database.entity.Education;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static database.management.EducationManagement.showAllEducations;
import static database.schoolpackage.Utility.*;

public class CourseManagement {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");

    public static void courseMenu() {

        boolean loop = true;

        while (loop) {
            System.out.println("What would you like to do?");
            System.out.println("1. Show all courses");
            System.out.println("2. Create a course ");
            System.out.println("3. Remove a course");
            System.out.println("4. Update a course");
            System.out.println("5. Add course to education");
            System.out.println("6. Remove a course from an education");
            System.out.println("7. Show specific course ");
            System.out.println("8. Show a course and its students ");
            System.out.println("0. Back to the main menu");
            int choice = readInt();

            switch (choice) {
                case 1:
                    showAllCourses();
                    pressEnter();
                    break;
                case 2:
                    createCourse();
                    break;
                case 3:
                    removeCourse();
                    break;
                case 4:
                    updateCourse();
                    break;
                case 5:
                    addExistingCourseToEducation();
                    break;
                case 6:
                    removeExistingCourseFromExistingEducation();
                    break;
                case 7:
                    showSpecificCourseByID();
                    break;
                case 8:
                    showCourseAndItsStudentsByID();
                    break;
                case 0:
                    loop = false;
                    break;
            }
        }
    }

    public static void showAllCourses() {
        EntityManager em = emf.createEntityManager();
        em.createQuery("SELECT c FROM Course c", Course.class)
                .getResultStream()
                .forEach(System.out::println);

    }

    public static void showSpecificCourseByID() {
        EntityManager em = emf.createEntityManager();

        showAllCourses();
        System.out.println("Enter course ID of the course");
        Course c = em.find(Course.class, readInt());
        System.out.println(c);
        pressEnter();


    }

    public static void createCourse() {

        EntityManager em = emf.createEntityManager();

        System.out.println("Enter course name: ");
        String name = readString();
        System.out.println("Enter weekly length");
        int wLength = readInt();

        Course c = new Course(name, wLength);

        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
        em.close();
        pressEnter();

    }

    private static void removeCourse() {
        EntityManager em = emf.createEntityManager();
        showAllCourses();
        System.out.println("Enter the ID of the course you want to remove:");

        Course courseToRemove = em.find(Course.class, readInt());

        em.getTransaction().begin();
        em.remove(courseToRemove);
        em.getTransaction().commit();
        em.close();
        System.out.println(courseToRemove + " IS REMOVED");
        pressEnter();

    }

    private static void updateCourse() {
        EntityManager em = emf.createEntityManager();
        showAllCourses();
        System.out.println("Enter course-ID to update:");

        Course courseToUpdate = em.find(Course.class, readInt());

        System.out.println("Enter the new name:");
        String newName = readString();
        System.out.println("Enter the weekly length:");
        int newWeeklyLength = readInt();

        em.getTransaction().begin();
        courseToUpdate.setName(newName);
        courseToUpdate.setWeeklyLength(newWeeklyLength);
        em.getTransaction().commit();
        em.close();
        System.out.println(courseToUpdate + " IS UPDATED");
        pressEnter();
    }


    private static void addExistingCourseToEducation() {

        EntityManager em = emf.createEntityManager();
        showAllCourses();
        System.out.println("Enter the course ID: ");
        Course courseToAdd = em.find(Course.class, readInt());

        showAllEducations();
        System.out.println("Enter education ID to add the course");
        Education educationToAdd = em.find(Education.class, readInt());


        em.getTransaction().begin();
        educationToAdd.addCourse(courseToAdd);
        em.getTransaction().commit();
        em.close();
        System.out.println(courseToAdd + " IS ADDED TO " + educationToAdd);
        pressEnter();
    }

    public static void removeExistingCourseFromExistingEducation() {

        EntityManager em = emf.createEntityManager();

        showAllCourses();
        System.out.println("Enter the course ID: ");
        Course courseToRemove = em.find(Course.class, readInt());

        showAllEducations();
        System.out.println("Enter education ID to add the course");
        Education educationToRemove = em.find(Education.class, readInt());

        em.getTransaction().begin();
        courseToRemove.removeEducation(educationToRemove);
        em.getTransaction().commit();
        em.close();
        System.out.println(courseToRemove + " IS REMOVED FROM " + educationToRemove);
        pressEnter();
    }
    /// HÄR SKA EN LISTA TILL
    private static void showCourseAndItsStudentsByID() {
        showAllCourses();
        EntityManager em = emf.createEntityManager();
        System.out.println("Write the ID of the course you want to show:");
        Course c = em.find(Course.class, readInt());
        System.out.println(c);
        pressEnter();

    }
}



