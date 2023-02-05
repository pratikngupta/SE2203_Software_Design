package assignment1.pgupta85.assignment1;

import javafx.application.Platform;

import static assignment1.pgupta85.method.Debug.*;

public class BubbleSort implements SortingStrategy {
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
    public int getRunNeeded(int[] intArray) {
        printCYAN("Finding the number of loops needed for Bubble Sort", "DEBUG: BubbleSort.java ---> ");
        actualRun = false;
        loop = 0;
        sort(intArray);
        printCYAN("Number of loops needed for Selection Sort: " + loop, "DEBUG: BubbleSort.java ---> ");
        return loop;
    }

    @Override
    public void sort(int[] arr) {

        //write code as a thread
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
                logic(arr);
            }

        }
    }


    @Override
    public void run() {
        new Thread(() -> {
            actualRun = true;
            printPURPLE("Starting Bubble Sort", "DEBUG: BubbleSort.java ---> ");
            sort(intArray);
            printPURPLE("Bubble Sort Complete", "DEBUG: BubbleSort.java ---> ");
            sortingHubController.updateGraph(intArray);
            printLine();
        }).start();
    }

    public void logic (int [] arr ) {
        if (actualRun) {
            Platform.runLater(() -> sortingHubController.updateGraph(arr));
            Platform.runLater(() -> sortingHubController.setStatusBar(true));
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (!actualRun) {
            loop++;
        }
    }
}

