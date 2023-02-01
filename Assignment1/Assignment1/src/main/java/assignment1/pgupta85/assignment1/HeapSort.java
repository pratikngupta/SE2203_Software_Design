package assignment1.pgupta85.assignment1;

import javafx.application.Platform;

public class HeapSort implements SortingStrategy{

        private final SortingHubController sortingHubController;

        private final int[] intArray;

        //create a constructor to get the array from the SortingHubController
        public HeapSort(SortingHubController sortingHubController, int[] intArray) {
            //assign value to controller
            this.sortingHubController = sortingHubController;
            this.intArray = intArray;
        }

        @Override
        public void sort(int[] arr) {
            System.out.println("New Heap Sort");
            //write code as a thread
            new Thread(() -> {
                //call the heapSort method
                heapSort(arr);
                //update the graph
                sortingHubController.updateGraph(arr);
                //print the array
                for (int i = 0; i < arr.length; i++) {
                    System.out.println(arr[i] + " ");
                }
                //print the message
                System.out.println("Heap Sort Complete");
                //exit the program
                Platform.exit();
            }).start();

        }

    @Override
    public void run() {
        //call the heapSort method
        heapSort(intArray);
        //update the graph
        sortingHubController.updateGraph(intArray);
        //print the array
        for (int i = 0; i < intArray.length; i++) {
            System.out.println(intArray[i] + " ");
        }
        //print the message
        System.out.println("Heap Sort Complete");
        //exit the program
        Platform.exit();
    }

    private void heapSort(int[] arr) {
            //create a variable to store the length of the array
            int n = arr.length;
            //create a for loop to iterate through the array
            for (int i = n / 2 - 1; i >= 0; i--) {
                //call the heapify method
                heapify(arr, n, i);
            }
            //create a for loop to iterate through the array
            for (int i = n - 1; i >= 0; i--) {
                //create a variable to store the value at 0
                int temp = arr[0];
                //assign the value at i to the value at 0
                arr[0] = arr[i];
                //assign the value at temp to the value at i
                arr[i] = temp;
                //call the heapify method
                heapify(arr, i, 0);
                //update the graph while sorting is in progress
                Platform.runLater(() -> sortingHubController.updateGraph(arr));
                //sleep the thread
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void heapify(int[] arr, int n, int i) {
            //create a variable to store the largest value
            int largest = i;
            //create a variable to store the value of 2 * i + 1
            int left = 2 * i + 1;
            //create a variable to store the value of 2 * i + 2
            int right = 2 * i + 2;
            //check if the value of left is less than the value of n and the value at left is greater than the value at largest
            if (left < n && arr[left] > arr[largest]) {
                //assign the value at left to the value at largest
                largest = left;
            }
            //check if the value of right is less than the value of n and the value at right is greater than the value at largest
            if (right < n && arr[right] > arr[largest]) {
                //assign the value at right to the value at largest
                largest = right;
            }
            //check if the value of largest is not equal to the value of i
            if (largest != i) {
                //create a variable to store the value at i
                int temp = arr[i];
                //assign the value at largest to the value at i
                arr[i] = arr[largest];
                //assign the value at temp to the value at largest
                arr[largest] = temp;
                //call the heapify method
                heapify(arr, n, largest);

            }
            //in HeapSort, the heapify method is called in the heapSort method
            //what is difference between heapsort and selection sort
            //selection sort is not a stable sort
            //heapsort is a stable sort
            //heapsort is not an in-place sort
            //selection sort is an in-place sort
            //heapsort is not a comparison sort
            //selection sort is a comparison sort
            //heapsort is not a recursive sort
            //selection sort is a recursive sort
            //heapsort is not a stable sort
            //selection sort is a stable sort
            //Where is the largest item in heapsort after first pass?
            //the largest item is at the root of the tree


        }


}
