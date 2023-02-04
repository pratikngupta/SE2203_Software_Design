/******************************************************
 Name: Pratik Narendra Gupta
 Student ID: 251211859
 Date: 18th September
 Modified: 14th October
 Task: Create a class which can be imported and make writing code easy.
 What will this class do?
 $$ This is child class of Ask $$
 1) This will be final resting place for myHeader and myFooter method.
 2) It will save space and make code more readable.
 3) Also we would not have to write this method everytime for new questions.
 4) AS this is child class of Ask, it will inherit all the methods of Ask class.
 5) Hence I would only need to import this method and can access any method of Ask class.
 *********************************************************/

package pgupta85.method;

public class Intro extends Ask {

    public static void myHeader(int labNumber,int qNumber,String name, String message){

        //Using printf to print and format message, %d will help to print integers variable
        System.out.printf(blue + """
                ===============================================================================
                Lab Exercise %d
                Question Number %d
                Prepared by: %s
                Student Number: 251211859
                Goal of this exercise: %s\040
                ===============================================================================
                
                """ + reset, labNumber, qNumber,name, message);

    }

    public static void myFooter(int labNumber,int qNumber){

        //Using printf to print and format message, %d will help to print integers variable
        System.out.printf(blue + """
                
                ===============================================================================
                Completion of lab %d, Question %d was successful
                Signing off Pratik
                ===============================================================================
                """ + reset, labNumber, qNumber);
        //System.exit(0);

    }

    public static void intro(){
        System.out.println(blue + """
                 Name: Pratik Narendra Gupta
                 Student ID: 251211859
                 Email ID: pgupta85@uwo.ca
                """ + reset);

    }

}




