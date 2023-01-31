package assignment1.pgupta85.assignment1;

import javafx.application.Platform;

public class MergeSort implements SortingStrategy{

    private SortingHubController sortingHubController;

    private int[] intArray;

    //create a constructor to get the array from the SortingHubController
    public MergeSort(SortingHubController sortingHubController, int[] intArray) {
        //assign value to controller
        this.sortingHubController = sortingHubController;
        this.intArray = intArray;
    }

    @Override
    public void sort(int[] arr) {
        System.out.println("New Merge Sort");
        //write code as a thread
        new Thread(() -> {
            //create a for loop to iterate through the array
            for (int i = 0; i < arr.length - 1; i++) {
                //create a for loop to iterate through the array
                //mergeSort(arr, 0, arr.length - 1);
                //sleep the thread
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //update the graph
            sortingHubController.updateGraph(arr);
            //print the array
            for (int j : arr) {
                System.out.println(j + " ");
            }
            //print the message
            System.out.println("Bubble Sort Complete");
            //exit the program
            Platform.exit();
        }).start();
    }

    @Override
    public void run() {
        //make sort method more efficient by calling it, calling updateGraph method, and printing the array using thread here
    }
}


