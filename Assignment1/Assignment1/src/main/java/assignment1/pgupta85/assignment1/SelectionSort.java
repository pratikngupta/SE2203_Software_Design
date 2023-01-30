package assignment1.pgupta85.assignment1;

import javafx.application.Platform;

public class SelectionSort implements SortingStrategy{

    private SortingHubController sortingHubController;

    private int[] intArray;

    //create a constructor to get the array from the SortingHubController
    public SelectionSort(SortingHubController sortingHubController, int[] intArray) {
        //assign value to controller
        this.sortingHubController = sortingHubController;
        this.intArray = intArray;
    }

    @Override
    public void sort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n-1; i++)
        {
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (arr[j] < arr[min_idx])
                    min_idx = j;

            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }

    @Override
    public void run() {
        new Thread(() -> {
            try {
                while (true) {
                    Platform.runLater(() -> {
                        sort(intArray);
                        sortingHubController.updateGraph();
                    });
                    Thread.sleep(10000000);
                }
            } catch (InterruptedException ex) {
            }
        }).start();

    }
}
