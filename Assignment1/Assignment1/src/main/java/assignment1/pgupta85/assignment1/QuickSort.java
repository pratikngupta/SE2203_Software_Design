package assignment1.pgupta85.assignment1;

import javafx.application.Platform;

public class QuickSort implements SortingStrategy{

        private SortingHubController sortingHubController;

        private int[] intArray;

        @Override
        public void SortingStrategy(int[] arr, SortingHubController sortingHubController) {
        this.sortingHubController = sortingHubController;
        this.intArray = arr;
        }

        @Override
        public void sort(int[] arr) {
            System.out.println("New Quick Sort");
            //write code as a thread
                //call the quickSort method
                quickSort(arr, 0, arr.length - 1);
                //update the graph
                sortingHubController.updateGraph(arr);
                //print the array
                for (int i = 0; i < arr.length; i++) {
                    System.out.println(arr[i] + " ");
                }
                //print the message
                System.out.println("Quick Sort Complete");
                //exit the program
                Platform.exit();


        }

    private void quickSort(int[] arr, int i, int i1) {
        //check if the value of i is less than the value of i1
        if (i < i1) {
            //create a variable to store the partition
            int partition = partition(arr, i, i1);
            //call the quickSort method
            quickSort(arr, i, partition - 1);
            //call the quickSort method
            quickSort(arr, partition + 1, i1);
        }
    }

    private int partition(int[] arr, int i, int i1) {
        //create a variable to store the pivot
        int pivot = arr[i1];
        //create a variable to store the index
        int index = i - 1;
        //create a for loop to iterate through the array
        for (int j = i; j < i1; j++) {
            //check if the value at j is less than the value at pivot
            if (arr[j] < pivot) {
                //increment the value of index
                index++;
                //create a temporary variable to store the value at index
                int temp = arr[index];
                //assign the value at j to the value at index
                arr[index] = arr[j];
                //assign the value at index to the value at j
                arr[j] = temp;
                //update the graph
                Platform.runLater(() -> sortingHubController.updateGraph(arr));
                //sleep the thread
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        //create a temporary variable to store the value at index + 1
        int temp = arr[index + 1];
        //assign the value at pivot to the value at index + 1
        arr[index + 1] = arr[i1];
        //assign the value at index + 1 to the value at pivot
        arr[i1] = temp;
        //update the graph
        Platform.runLater(() -> sortingHubController.updateGraph(arr));
        //sleep the thread
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //return the value of index + 1
        return index + 1;
    }

    @Override
    public void run() {
            new Thread(() -> {
                //call the quickSort method
                quickSort(intArray, 0, intArray.length - 1);
                //update the graph
                sortingHubController.updateGraph(intArray);
                //print the message
                System.out.println("Quick Sort Complete");
            }).start();
    }
    @Override
    public int getRunNeeded(int[] intArray) {
        return 0;
    }
}
