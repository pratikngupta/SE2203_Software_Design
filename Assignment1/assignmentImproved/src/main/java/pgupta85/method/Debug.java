package pgupta85.method;

public class Debug extends Ask{

    public static void printRED(String message, String type){
        System.out.println(yellow + type + red + message + reset);
    }
    public static void printGREEN(String message, String type){
        System.out.println(yellow + type + green + message + reset);
    }
    public static void printYELLOW(String message, String type){
        System.out.println(yellow + type + yellow + message + reset);
    }
    public static void printBLUE(String message, String type){
        System.out.println(yellow + type + blue + message + reset);
    }
    public static void printPURPLE(String message, String type){
        System.out.println(yellow + type + purple + message + reset);
    }
    public static void printCYAN(String message, String type){
        System.out.println(yellow + type + cyan + message + reset);
        //delete previously printed line
    }
    public static void printSameLine(String message, String type){
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
