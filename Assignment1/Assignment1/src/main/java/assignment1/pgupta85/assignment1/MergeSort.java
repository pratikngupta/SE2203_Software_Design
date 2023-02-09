package assignment1.pgupta85.assignment1;

import javafx.application.Platform;

import static assignment1.pgupta85.method.Debug.*;

public class MergeSort implements SortingStrategy {

    private SortingHubController sortingHubController;

    private int[] list;
    private int loop;
    private boolean actualRun;

    @Override
    public void SortingStrategy(int[] numbers, SortingHubController sortingHubController) {
        this.sortingHubController = sortingHubController;
        this.list = numbers;
    }

    @Override
    public void sort(int[] arr) {
        //implement the in-place merge sort algorithm here
        mergeSort(arr, 0, arr.length - 1);
    }

    void merge(int arr[], int start, int mid, int end) {
        logic(arr);
        int start2 = mid + 1;

        // If the direct merge is already sorted
        if (arr[mid] <= arr[start2]) {
            return;
        }

        // Two pointers to maintain start
        // of both arrays to merge
        while (start <= mid && start2 <= end) {

            // If element 1 is in right place
            if (arr[start] <= arr[start2]) {
                start++;
            } else {
                int value = arr[start2];
                int index = start2;

                // Shift all the elements between element 1
                // element 2, right by 1.
                while (index != start) {
                    arr[index] = arr[index - 1];
                    index--;
                }
                arr[start] = value;

                // Update all the pointers
                start++;
                mid++;
                start2++;
            }
            logic(arr);
        }
    }

    /* l is for left index and r is right index of the
       sub-array of arr to be sorted */
    void mergeSort(int arr[], int l, int r) {
        if (l < r) {

            // Same as (l + r) / 2, but avoids overflow
            // for large l and r
            int m = l + (r - l) / 2;

            // Sort first  and second halves
            mergeSort(arr, l, m);

            mergeSort(arr, m + 1, r);

            merge(arr, l, m, r);
            logic(arr);
        }
    }

    @Override
    public void run() {
        actualRun = true;
        sortingHubController.disableButtons(true);
        printPURPLE("Merge Selection Sort", "DEBUG: MergeSort.java ---> ");
        sort(list);
        printPURPLE("Merge Sort Complete", "DEBUG: MergeSort.java ---> ");
        sortingHubController.updateGraph(list);
        sortingHubController.disableButtons(false);
        printLine();
    }

    public void logic(int[] arr) {
        if (actualRun) {
            Platform.runLater(() -> sortingHubController.updateGraph(arr));
            Platform.runLater(() -> sortingHubController.setStatusBar(true));
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (!actualRun) {
            loop++;
        }
    }

    @Override
    public int getRunNeeded(int[] dummyArray) {
        printCYAN("Finding the number of loops needed for Merge Sort", "DEBUG: MergeSort.java ---> ");
        actualRun = false;
        loop = 0;
        sort(dummyArray);
        printCYAN("Number of loops needed for Merge Sort: " + loop, "DEBUG: MergeSort.java ---> ");
        return loop;
    }
}
