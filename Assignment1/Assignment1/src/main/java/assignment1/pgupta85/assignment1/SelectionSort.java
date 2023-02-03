package assignment1.pgupta85.assignment1;

import javafx.application.Platform;

import java.util.HashMap;

public class SelectionSort implements SortingStrategy{

    private SortingHubController sortingHubController;

    private int[] intArray;

    @Override
    public void SortingStrategy(int[] arr, SortingHubController sortingHubController) {
        this.sortingHubController = sortingHubController;
        this.intArray = arr;
    }

    //create a


    @Override
    public void sort(int[] arr) {
        System.out.println("New Selection Sort");
        //write code using thread

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
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //print the message
            System.out.println("Selection Sort Complete");
            //exit the program
    }

    @Override
    public void run() {
        new Thread(() -> {
            //call the quickSort method
            sort(intArray);
            //update the graph
            sortingHubController.updateGraph(intArray);
            //print the message
            System.out.println("Quick Sort Complete");
        }).start();
    }
}
