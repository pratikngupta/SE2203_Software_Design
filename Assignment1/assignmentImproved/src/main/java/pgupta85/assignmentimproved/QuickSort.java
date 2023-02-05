package pgupta85.assignmentimproved;

import javafx.application.Platform;

import static pgupta85.method.Debug.*;

public class QuickSort implements SortingStrategy {

    private SortingHubController sortingHubController;

    private int[] intArray;
    private int loop;
    private boolean actualRun;

    @Override
    public void SortingStrategy(int[] arr, SortingHubController sortingHubController) {
        this.sortingHubController = sortingHubController;
        this.intArray = arr;
    }

    @Override
    public void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
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
                logic(arr);
            }
        }
        //create a temporary variable to store the value at index + 1
        int temp = arr[index + 1];
        //assign the value at pivot to the value at index + 1
        arr[index + 1] = arr[i1];
        //assign the value at index + 1 to the value at pivot
        arr[i1] = temp;
        //update the graph
        logic(arr);
        //return the value of index + 1
        return index + 1;
    }

    public void logic (int [] arr ) {

        if (actualRun) {
            Platform.runLater(() -> sortingHubController.updateGraph(arr));
            Platform.runLater(() -> sortingHubController.setStatusBar(true));
            try {
                Thread.sleep(sortingHubController.getSpeed());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (!actualRun) {
            loop++;
        }
    }

    @Override
    public void run() {
        new Thread(() -> {
            sortingHubController.disableDuringSorting(true);
            actualRun = true;

            printPURPLE("Quick Selection Sort", "DEBUG: QuickSort.java ---> ");
            sort(intArray);
            printPURPLE("Quick Sort Complete", "DEBUG: QuickSort.java ---> ");
            sortingHubController.updateGraph(intArray);

            printLine();
            sortingHubController.disableDuringSorting(false);
        }).start();
    }

    @Override
    public int getRunNeeded(int[] intArray) {
        printCYAN("Finding the number of loops needed for Quick Sort", "DEBUG: QuickSort.java ---> ");
        actualRun = false;
        loop = 0;
        sort(intArray);
        printCYAN("Number of loops needed for Quick Sort: " + loop, "DEBUG: QuickSort.java ---> ");
        return loop;
    }
}
