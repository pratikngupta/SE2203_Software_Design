package assignment1.pgupta85.assignment1;

import javafx.application.Platform;

public class SelectionSort implements SortingStrategy{

    private SortingHubController sortingHubController;

    private int[] intArray;

    //create a constructor to get the array from the SortingHubController
    public SelectionSort(SortingHubController sortingHubController, int[] intArray) {
        //assign value to controller
        this.sortingHubController = sortingHubController;
        this.intArray = intArray;
    }

    @Override
    public void sort(int[] arr) {
        System.out.println("New Selection Sort");
        //write code as a thread
        new Thread(() -> {
            //create a for loop to iterate through the array
            for (int i = 0; i < arr.length - 1; i++) {
                //create a variable to store the minimum value
                int min = i;
                //create a for loop to iterate through the array
                for (int j = i + 1; j < arr.length; j++) {
                    //check if the value at j is less than the value at min
                    if (arr[j] < arr[min]) {
                        //if it is, assign the value at j to min
                        min = j;
                    }
                }
                //create a temporary variable to store the value at i
                int temp = arr[i];
                //assign the value at min to the value at i
                arr[i] = arr[min];
                //assign the value at i to the value at min
                arr[min] = temp;
                //update the graph
                Platform.runLater(() -> sortingHubController.updateGraph(arr));

                //sleep the thread
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //update the graph
            sortingHubController.updateGraph(arr);
            //print the array
            for (int i = 0; i < arr.length; i++) {
                System.out.println(arr[i] + " ");
            }
            //print the message
            System.out.println("Selection Sort Complete");
            //exit the program
            Platform.exit();
        }).start();
    }

    @Override
    public void run() {
        //make sort method more efficient by calling it, calling updateGraph method, and printing the array using thread here
    }
}
