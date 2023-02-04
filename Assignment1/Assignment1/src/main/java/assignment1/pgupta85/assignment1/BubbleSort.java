package assignment1.pgupta85.assignment1;

import javafx.application.Platform;

public class BubbleSort implements SortingStrategy{
    private SortingHubController sortingHubController;

    private int[] intArray;
    private int loop;

    @Override
    public void SortingStrategy(int[] arr, SortingHubController sortingHubController) {
        this.sortingHubController = sortingHubController;
        this.intArray = arr;
    }

    @Override
    public int getRunNeeded(int[] intArray) {
        System.out.println("New Bubble Sort");
        //write code as a thread
        int loop = 0;
        //create a for loop to iterate through the array
        for (int i = 0; i < intArray.length - 1; i++) {
            //create a for loop to iterate through the array
            for (int j = 0; j < intArray.length - i - 1; j++) {

                //check if the value at j is greater than the value at j + 1
                if (intArray[j] > intArray[j + 1]) {
                    //create a temporary variable to store the value at j
                    int temp = intArray[j];
                    //assign the value at j + 1 to the value at j
                    intArray[j] = intArray[j + 1];
                    //assign the value at j to the value at j + 1
                    intArray[j + 1] = temp;
                    //update the graph
                }
                loop++;
            }
        }
        return loop;
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
                    //check if the value at j is greater than the value at j + 1
                    if (arr[j] > arr[j + 1]) {
                        //create a temporary variable to store the value at j
                        int temp = arr[j];
                        //assign the value at j + 1 to the value at j
                        arr[j] = arr[j + 1];
                        //assign the value at j to the value at j + 1
                        arr[j + 1] = temp;
                        //update the graph
                    }
                    Platform.runLater(() -> sortingHubController.updateGraph(arr));
                    Platform.runLater(() -> sortingHubController.setStatusBar(true));
                    //sleep the thread
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
            //update the graph
            sortingHubController.updateGraph(arr);//print the array
            //print the message
            System.out.println("Bubble Sort Complete");
    }


    @Override
    public void run() {
        new Thread(() -> {
            sort(intArray);
            //update the graph
            sortingHubController.updateGraph(intArray);
            //print the message
            System.out.println("Quick Sort Complete");
        }).start();
    }
}

