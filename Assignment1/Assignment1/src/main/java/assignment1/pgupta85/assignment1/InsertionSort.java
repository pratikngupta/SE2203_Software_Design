package assignment1.pgupta85.assignment1;

import javafx.application.Platform;

import static assignment1.pgupta85.method.Debug.*;

public class InsertionSort implements SortingStrategy{

    private SortingHubController sortingHubController;

    private int[] intArray;

    @Override
    public void SortingStrategy(int[] arr, SortingHubController sortingHubController) {
        this.sortingHubController = sortingHubController;
        this.intArray = arr;
    }


    @Override
    public void sort(int[] arr) {
        //write code as a thread
            //create a for loop to iterate through the array
            for (int i = 1; i < arr.length; i++) {
                //create a variable to store the value at i
                int key = arr[i];
                //create a variable to store the value of i - 1
                int j = i - 1;
                //create awhile loop to iterate through the array
                while (j >= 0 && arr[j] > key) {
                    //assign the value at j to the value at j + 1
                    arr[j + 1] = arr[j];
                    //decrement j
                    j = j - 1;
                    //update the graph
                    Platform.runLater(() -> sortingHubController.updateGraph(arr));
                    Platform.runLater(() -> sortingHubController.setStatusBar(true));
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //assign the value at j + 1 to the value at key
                arr[j + 1] = key;
            }
    }
    @Override
    public int getRunNeeded(int[] intArray) {
        printCYAN("Finding the number of loops needed for Insertion Sort", "DEBUG: InsertionSort.java ---> ");
        int loop = 0;
        for (int i = 1; i < intArray.length; i++) {
            //create a variable to store the value at i
            int key = intArray[i];
            //create a variable to store the value of i - 1
            int j = i - 1;
            //create awhile loop to iterate through the array
            while (j >= 0 && intArray[j] > key) {
                //assign the value at j to the value at j + 1
                intArray[j + 1] = intArray[j];
                //decrement j
                j = j - 1;
                loop++;
            }
            intArray[j + 1] = key;
        }
        printCYAN("Number of loops needed for Insertion Sort: " + loop, "DEBUG: InsertionSort.java ---> ");
        return loop;
    }

    @Override
    public void run() {
        new Thread(() -> {
            printPURPLE("Insertion Selection Sort", "DEBUG: InsertionSort.java ---> ");
            sort(intArray);
            printPURPLE("Insertion Sort Complete", "DEBUG: InsertionSort.java ---> ");
            sortingHubController.updateGraph(intArray);
            printLine();
        }).start();
    }

}


