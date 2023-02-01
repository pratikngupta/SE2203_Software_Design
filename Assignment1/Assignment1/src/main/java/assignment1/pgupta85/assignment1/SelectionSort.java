package assignment1.pgupta85.assignment1;

import javafx.application.Platform;

public class SelectionSort implements SortingStrategy{

    private final SortingHubController sortingHubController;

    private final int[] intArray;

    //create a constructor to get the array from the SortingHubController
    public SelectionSort(SortingHubController sortingHubController, int[] intArray) {
        //assign value to controller
        this.sortingHubController = sortingHubController;
        this.intArray = intArray;
    }

    //create a


    @Override
    public void sort(int[] arr) {
        System.out.println("New Selection Sort");
        //write code using thread
        new Thread(() -> {
            //create a for loop to iterate through the array
            for (int i = 0; i < arr.length - 1; i++) {
                //create a variable to store the value of i
                int minIndex = i;
                //create a for loop to iterate through the array
                for (int j = i + 1; j < arr.length; j++) {
                    //create an if statement to check if the value at j is less than the value at minIndex
                    if (arr[j] < arr[minIndex]) {
                        //assign the value at j to the value at minIndex
                        minIndex = j;
                    }
                }
                //create a variable to store the value at minIndex
                int temp = arr[minIndex];
                //assign the value at i to the value at minIndex
                arr[minIndex] = arr[i];
                //assign the value at temp to the value at i
                arr[i] = temp;
                //update the graph
                Platform.runLater(() -> sortingHubController.updateGraph(arr));
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
