package assignment1.pgupta85.assignment1;

import javafx.application.Platform;

public class QuickSort implements SortingStrategy{

        private SortingHubController sortingHubController;

        private int[] intArray;

        //create a constructor to get the array from the SortingHubController
        public QuickSort(SortingHubController sortingHubController, int[] intArray) {
            //assign value to controller
            this.sortingHubController = sortingHubController;
            this.intArray = intArray;
        }

        @Override
        public void sort(int[] arr) {
            System.out.println("New Quick Sort");
            //write code as a thread
            new Thread(() -> {
                //create a variable to store the value of the first index
                int low = 0;
                //create a variable to store the value of the last index
                int high = arr.length - 1;
                //call the quickSort method
                quickSort(arr, low, high);
                //update the graph
                sortingHubController.updateGraph(arr);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //print the array
                for (int i = 0; i < arr.length; i++) {
                    System.out.println(arr[i] + " ");
                }
                //print the message
                System.out.println("Quick Sort Complete");
                //exit the program
                Platform.exit();
            }).start();
        }

    @Override
    public void run() {

    }

    //create a method to sort the array
        public void quickSort(int[] arr, int low, int high) {
            //create a variable to store the value of the low index
            int i = low;
            //create a variable to store the value of the high index
            int j = high;
            //create a variable to store the value of the pivot
            int pivot = arr[low + (high - low) / 2];
            //create a while loop to iterate through the array
            while (i <= j) {
                //create a while loop to iterate through the array
                while (arr[i] < pivot) {
                    //increment i
                    i++;
                }
                //create a while loop to iterate through the array
                while (arr[j] > pivot) {
                    //decrement j
                    j--;
                }
                //create an if statement to check if i is less than or equal to j
                if (i <= j) {
                    //create a variable to store the value at i
                    int temp = arr[i];
                    //assign the value at j to the value at i
                    arr[i] = arr[j];
                    //assign the value at temp to the value at j
                    arr[j] = temp;
                    //increment i
                    i++;
                    //decrement j
                    j--;
                    //update the graph
                    Platform.runLater(() -> sortingHubController.updateGraph(arr));
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
}
