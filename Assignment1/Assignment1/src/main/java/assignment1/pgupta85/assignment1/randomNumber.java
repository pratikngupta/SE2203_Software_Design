package assignment1.pgupta85.assignment1;

public class randomNumber {

    private static int[] ramdomNumbersGenerator = new int[97];
    private static int[] intArray;


    public static void main (String [] args) {
        //print the array
        randomNumber();
        //get random number between 0 and 96 inclusive
        int randomNumber;

        intArray = new int[128];
        //fill the array with random numbers from the ramdomNumbersGenerator array
        for (int i = 0; i < 64; i++) {
            randomNumber = (int) (Math.random() * 97);

            //check if the number is already in the array
            for (int j = 0; j < i; j++) {
                if (intArray[j] == ramdomNumbersGenerator[randomNumber]) {
                    randomNumber = (int) (Math.random() * 97);
                    j = -1;
                }
            }
            intArray[i] = ramdomNumbersGenerator[randomNumber];
        }

        //print the array
        for (int i = 0; i < 64; i++) {
            System.out.println(intArray[i] + " ");
        }

    }

    public static void randomNumber() {
        int number = 32;
            for (int i = 0; i < 97; i++) {
                ramdomNumbersGenerator[i] = number;
                System.out.println(ramdomNumbersGenerator[i]);
                number ++;
            }

    }
}
