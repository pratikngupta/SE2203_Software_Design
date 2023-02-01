package assignment1.pgupta85.assignment1;

import javafx.application.Platform;

public class BubbleSort implements SortingStrategy{

    private final SortingHubController sortingHubController;

    private final int[] intArray;

    //create a constructor to get the array from the SortingHubController
    public BubbleSort(SortingHubController sortingHubController, int[] intArray) {
        //assign value to controller
        this.sortingHubController = sortingHubController;
        this.intArray = intArray;
    }

    @Override
    public void sort(int[] arr) {
        System.out.println("New Bubble Sort");
        //write code as a thread
        new Thread(() -> {
            //create a for loop to iterate through the array
            for (int i = 0; i < arr.length - 1; i++) {
                //create a for loop to iterate through the array
                for (int j = 0; j < arr.length - i - 1; j++) {
                    //check if the value at j is greater than the value at j + 1
                    if (arr[j] > arr[j + 1]) {
                        //create a temporary variable to store the value at j
                        int temp = arr[j];
                        //assign the value at j + 1 to the value at j
                        arr[j] = arr[j + 1];
                        //assign the value at j to the value at j + 1
                        arr[j + 1] = temp;
                        //update the graph
                        Platform.runLater(() -> sortingHubController.updateGraph(arr));
                    }
                }
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

