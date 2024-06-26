package assignment1.pgupta85.assignment1;

import javafx.application.Platform;

import static assignment1.pgupta85.method.Debug.*;

public class MergeSort implements SortingStrategy {

    private SortingHubController controller;

    private int[] list;
    private int loop;
    private boolean actualRun;

    @Override
    public void setValue(int[] numbers, SortingHubController controller) {
        this.controller = controller;
        this.list = numbers;
    }

    @Override
    public void sort(int[] numbers) {
        //implement the in-place merge sort algorithm
        mergeHelper(numbers, 0, numbers.length - 1);
    }

    void merge(int[] array, int start, int mid, int end) {

        //create a variable to store the start of the second array
        int start2 = mid + 1;

        // If the direct merge is already sorted
        if (array[mid] <= array[start2]) {
            //update the graph
            return;
        }

        // Two pointers to maintain start
        // of both arrays to merge
        while (start <= mid && start2 <= end) {

            // If element 1 is in right place
            if (array[start] <= array[start2]) {
                start++;
            } else {
                int value = array[start2];
                int index = start2;

                // Shift all the elements between element 1
                // element 2, right by 1.
                while (index != start) {
                    array[index] = array[index - 1];
                    index--;
                }
                array[start] = value;

                // Update all the pointers
                start++;
                mid++;
                start2++;
                logic(array);
            }
        }
    }

    /* l is for left index and right is right index of the
       sub-array of array to be sorted */
    void mergeHelper(int[] array, int left, int right) {
        if (left < right) {
            // Same as (l + right) / 2, but avoids overflow
            // for large l and right
            int m = left + (right - left) / 2;

            // Sort first  and second halves
            mergeHelper(array, left, m);
            mergeHelper(array, m + 1, right);

            //merge the two halves
            merge(array, left, m, right);

            //update the graph
            logic(array);
        }
    }

    @Override
    public void run() {
        actualRun = true;
        controller.disableButtons(true);
        printPURPLE("Merge Selection Sort", "DEBUG: MergeSort.java ---> ");
        sort(list);
        printPURPLE("Merge Sort Complete", "DEBUG: MergeSort.java ---> ");
        controller.updateGraph(list);
        controller.disableButtons(false);
        printLine();
    }

    //code to update the graph and sleep the thread
    @Override
    public void logic(int[] arr) {
        //check if the actualRun is true
        if (actualRun) {
            //update the graph
            Platform.runLater(() -> controller.updateGraph(arr));
            Platform.runLater(() -> controller.setStatusBar(true));

            //sleep the thread for 20 milliseconds
            try {
                Thread.sleep(50);
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

    @Override
    public int getRunNeeded(int[] dummyArray) {
        //find the number of loops needed for the algorithm
        printCYAN("Finding the number of loops needed for Merge Sort", "DEBUG: MergeSort.java ---> ");
        actualRun = false;
        loop = 0;
        sort(dummyArray);
        printCYAN("Number of loops needed for Merge Sort: " + loop, "DEBUG: MergeSort.java ---> ");
        return loop; //return the number of loops
    }
}
