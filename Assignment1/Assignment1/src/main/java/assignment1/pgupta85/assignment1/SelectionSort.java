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
        //sort the array using selection sort: send the array to the controller to display after each swap with a delay before the next swap
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
            //print the array
            for (int j : arr) {
                System.out.print(j + " ");
            }
            System.out.println();
           //start a new thread to display the array
            //update the graph in background thread with a delay
        }
    }

    @Override
    public void run() {
        // delay execution very briefly after each redraw to allow the user to see the changes in the display before the next swap
        //Called sort method to sort the array and display it using the controller using thread

        Thread thread = new Thread(() -> {
            sortingHubController.updateGraph(intArray);
            Platform.runLater(() -> {
                sort(intArray);
            });
        });
        thread.start();
    }
}
