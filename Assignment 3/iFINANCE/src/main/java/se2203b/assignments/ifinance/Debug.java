package se2203b.assignments.ifinance;

public class Debug extends Ask{

    public static void printRED(String type, String message){
        type = type.toUpperCase();
        type = type + " ==> ";
        System.out.println(yellow + type + red + message + reset);
    }
    public static void printGREEN(String type, String message){
        type = type.toUpperCase();
        type = type + " ==> ";
        System.out.println(yellow + type + green + message + reset);
    }
    public static void printYELLOW(String type, String message){
        type = type.toUpperCase();
        type = type + " ==> ";
        System.out.println(yellow + type + yellow + message + reset);
    }
    public static void printBLUE(String type, String message){
        type = type.toUpperCase();
        type = type + " ==> ";
        System.out.println(yellow + type + blue + message + reset);
    }
    public static void printPURPLE(String type, String message){
        type = type.toUpperCase();
        type = type + " ==> ";
        System.out.println(yellow + type + purple + message + reset);
    }
    public static void printCYAN(String type, String message){
        type = type.toUpperCase();
        type = type + " ==> ";
        System.out.println(yellow + type + cyan + message + reset);
        //delete previously printed line
    }
    public static void printSameLine(String type, String message){
        System.out.println(yellow + type +": "+ cyan + message + reset);
    }
    public static void printLine()
    {
        System.out.println(yellow + "-----------------------------------------------------------------------------------------------------------------" + reset);
    }
    public static void printArray(String message, int[] arr){
        System.out.print(yellow + message + blue + ": ");
        for (int j : arr) {
            System.out.print(j + " ");
        }
        System.out.println(reset);
    }
}
