package database.schoolpackage;

import static database.management.CourseManagement.courseMenu;
import static database.management.EducationManagement.educationMenu;
import static database.management.StudentManagement.studentMenu;
import static database.management.TeacherManagement.teacherMenu;

import static database.schoolpackage.Utility.readInt;

public class Main {
    public static void main(String[] args) {
        mainMenu();
    }
    public static void mainMenu() {
        boolean loop = true;
        while (loop) {
            System.out.println("1. Manage students");
            System.out.println("2. Manage teachers");
            System.out.println("3. Manage courses");
            System.out.println("4. Manage educations");
            System.out.println("0. Quit");
            int choice = readInt();

            switch (choice) {
                case 1:
                    studentMenu();
                    break;
                case 2:
                    teacherMenu();
                    break;
                case 3:
                    courseMenu();
                    break;
                case 4:
                    educationMenu();
                    break;
                case 0:
                    loop = false;
                    break;
            }
        }
    }
}


       /*
       X • Add educations of different kind
       X • Update education information
       X • Show information about a specific education
       X • Show all educations
       X • Remove an education

        X• Add courses of different kind
        X• Update course information
        X• Show information about a specific course
        X• Show all courses
        X• Remove a course


        X• Add students
        X• Update student information
        X• Show information about a specific student
        X• Show all students
        X• Remove a student

        X• Add courses to educations
        X• List all courses in an education
        X• remove courses from educations

        X• Add students to educations
        X• remove students from an education
        • List all students in an education

        Fixa tostring :)

        Om vi kan ha elever endast i kurser som är kopplade till utbildningar



        */