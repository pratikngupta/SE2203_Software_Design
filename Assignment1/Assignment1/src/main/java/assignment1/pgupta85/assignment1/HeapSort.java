package assignment1.pgupta85.assignment1;

import javafx.application.Platform;

import static assignment1.pgupta85.method.Debug.*;

public class HeapSort implements SortingStrategy {

    //create a controller object
    private SortingHubController controller;

    //create an array to store the list
    private int[] list;
    private int loop;
    private boolean actualRun;

    //create a constructor
    @Override
    public void SortingStrategy(int[] numbers, SortingHubController controller) {
        this.controller = controller;
        this.list = numbers;
    }

    //Sorting method
    @Override
    public void sort(int[] numbers) {
        int n = numbers.length;
        //create a for loop to iterate through the array
        for (int i = n / 2 - 1; i >= 0; i--) {
            //call the heapify method
            heapify(numbers, n, i);
            logic(numbers);
        }
        //create a for loop to iterate through the array
        for (int i = n - 1; i >= 0; i--) {
            //create a variable to store the value at 0
            int temp = numbers[0];
            //assign the value at i to the value at 0
            numbers[0] = numbers[i];
            //assign the value at temp to the value at i
            numbers[i] = temp;
            //call the heapify method
            heapify(numbers, i, 0);
            //update the graph while sorting is in progress
            logic(numbers);
        }
    }

    //method to get run needed
    @Override
    public int getRunNeeded(int[] dummyArray) {
        printCYAN("Finding the number of loops needed for Heap Sort", "DEBUG: HeapSort.java ---> ");
        actualRun = false;
        loop = 0;
        sort(dummyArray);
        printCYAN("Number of loops needed for Heap Sort: " + loop, "DEBUG: HeapSort.java ---> ");
        return loop;
    }

    //method to update the graph
    @Override
    public void run() {
        controller.disableButtons(true);
            actualRun = true;
            printPURPLE("Heap Selection Sort", "DEBUG: HeapSort.java ---> ");
            sort(list);
            printPURPLE("Heap Sort Complete", "DEBUG: HeapSort.java ---> ");
            controller.updateGraph(list);
            printLine();
        controller.disableButtons(false);
    }

    //method to sort the array
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
    }

    //code to update the graph and sleep the thread
    @Override
    public void logic(int[] arr) {
        //check if the actualRun is true
        if (actualRun) {
            //update the graph
            Platform.runLater(() -> controller.updateGraph(arr));
            Platform.runLater(() -> controller.setStatusBar(true));

            //sleep the thread for 25 milliseconds
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //if the actualRun is false
        if (!actualRun) {
            //increment the loop
            loop++;
        }
    }
}
