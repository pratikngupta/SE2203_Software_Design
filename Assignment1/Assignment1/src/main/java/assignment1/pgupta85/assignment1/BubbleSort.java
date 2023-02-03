package assignment1.pgupta85.assignment1;

import javafx.application.Platform;

public class BubbleSort implements SortingStrategy{
    private SortingHubController sortingHubController;

    private int[] intArray;

    @Override
    public void SortingStrategy(int[] arr, SortingHubController sortingHubController) {
        this.sortingHubController = sortingHubController;
        this.intArray = arr;
    }


    @Override
    public void sort(int[] arr) {
        System.out.println("New Bubble Sort");
        //write code as a thread
        int loop;
            //create a for loop to iterate through the array
            for (int i = 0; i < arr.length - 1; i++) {
                //create a for loop to iterate through the array
                for (int j = 0; j < arr.length - i - 1; j++) {
                    loop = i;
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
                        Platform.runLater(() -> sortingHubController.setStatusBar(loop, arr.length));
                        //sleep the thread
                        try {
                            Thread.sleep(25);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
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

