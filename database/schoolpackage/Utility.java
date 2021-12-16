package database.schoolpackage;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Utility{
    public static Scanner input = new Scanner(System.in);

    public static int readInt() {
        int number = 0;
        boolean loop = true;
        while (loop) {
            try {
                number = input.nextInt();
                loop = false;
            } catch (InputMismatchException e) {
                System.out.println("Wrong input, try again");
            } finally {
                input.nextLine();
            }
        }
        return number;
    }

    public static double readDouble() {
        double number = 0;
        boolean loop = true;
        while (loop) {
            try {
                number = input.nextDouble();
                loop = false;
            } catch (InputMismatchException e) {
                System.out.println("Wrong input, try again");
            } finally {
                input.nextLine();
            }
        }
        return number;
    }

    public static String readString() {
        return input.nextLine();
    }

    public static void pressEnter() {
        System.out.println("Press enter to continue");
        input.nextLine();

    }
}

