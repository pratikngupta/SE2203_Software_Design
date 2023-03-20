/******************************************************
 Name: Pratik Narendra Gupta
 Student ID: 251211859
 Date: 18th September
 Modified: 1st October
 Task: Create a class which can be imported and make writing code easy.
 What will this class do?
 $$ This is Parent class for Intro $$
 1) This is an advance Scanner with few additional feature
 2) This will help in getting input from user and assign it to variable
 3) this will avoid declaring, import Scanner class in every java file.
 4) Coder will be able to pass message and request the type of input he/she is hoping to get.
 5) Depending on type of request, this will print out message, get input from user and return that input.
 6) With help of try/catch this will avoid any exception and ensure that user will only enter the type of input requested.
 *********************************************************/

package se2203b.assignments.ifinance;

//Import Scanner Class

import java.util.InputMismatchException;
import java.util.Scanner;

public class Ask {

    public static final String reset = "\u001B[0m";
    public static final String red = "\u001B[31m";
    public static final String green = "\u001B[32m";
    public static final String yellow = "\u001B[33m";
    public static final String blue = "\u001B[34m";
    public static final String purple = "\u001B[35m";
    public static final String cyan = "\u001B[36m";

    //Declaring Scanner type reference variable
    public static final Scanner input = new Scanner(System.in);

    //This will accept string message and will print it on screen
    public static void printMessage(String message) {
        if (message.length() > 0) {
            System.out.print(message);
        }
    }

    public static void printColor(String message, String color) {
        System.out.println(color + message + reset);
    }

    //This will accept string message and will return integer value it got from user.
    //This make use of try/catch to avoid any exception removing the flaw it once had.
    //If user enter any other type of value, it will print error message and ask user to enter integer value again.
    public static int getInt(String message, int opt) {
        if (opt == 1) {
            while (true) {
                try {
                    printMessage(message);
                    return input.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println(red + "Please enter valid number" + reset);
                    input.nextLine();
                }
            }
        }

        printMessage(message);
        return input.nextInt();

    }

    //This will accept string message and will return double value it got from user.
    public static double getDouble(String message) {
        printMessage(message);
        return input.nextDouble();
    }

    //This will accept string message and int value and will return char value it got from user.
    //Once advantage is that coder can request to return char in lowercase or uppercase.
    //For example if I set option to 0, then char will be converted to lowercase before returning value.
    //For example if I set option to 1, then char will be converted to uppercase before returning value.
    public static char getChar(String message, int options) {
        char getChar;
        printMessage(message);
        getChar = input.next().charAt(0);
        if (options == 0) {
            return Character.toLowerCase(getChar);
        } else if (options == 1) {
            return Character.toUpperCase(getChar);
        }
        return input.next().charAt(0);
    }

    //This will accept string message and print it on screen.
    public static String getString(String message) {
        printMessage(message);
        if (input.hasNextLine()) {
            input.nextLine();
        }
        return input.nextLine();
    }
}