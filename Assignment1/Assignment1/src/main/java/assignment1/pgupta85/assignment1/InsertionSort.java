package assignment1.pgupta85.assignment1;

import javafx.application.Platform;

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
        System.out.println("New InsertionSort Sort");
        //write code as a thread
            //create a for loop to iterate through the array
            for (int i = 1; i < arr.length; i++) {
                //create a variable to store the value at i
                int key = arr[i];
                //create a variable to store the value of i - 1
                int j = i - 1;
                //create a while loop to iterate through the array
                while (j >= 0 && arr[j] > key) {
                    //assign the value at j to the value at j + 1
                    arr[j + 1] = arr[j];
                    //decrement j
                    j = j - 1;
                    //update the graph
                    Platform.runLater(() -> sortingHubController.updateGraph(arr));
                }
                //assign the value at j + 1 to the value at key
                arr[j + 1] = key;
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
            System.out.println("InsertionSort Sort Complete");
            //exit the program
            Platform.exit();

    }
    @Override
    public int getRunNeeded(int[] intArray) {
        return 0;
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


