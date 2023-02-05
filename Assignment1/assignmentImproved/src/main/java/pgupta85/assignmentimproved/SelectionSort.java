package pgupta85.assignmentimproved;

import javafx.application.Platform;

import static pgupta85.method.Debug.*;

public class SelectionSort implements SortingStrategy {

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

        //create a for loop to iterate through the array
        for (int i = 0; i < arr.length - 1; i++) {
            //create a variable to store the value of i
            int minIndex = i;
            //create a for loop to iterate through the array
            for (int j = i + 1; j < arr.length; j++) {
                //create an if statement to check if the value at j is less than the value at minIndex
                if (arr[j] < arr[minIndex]) {
                    //assign the value at j to the value at minIndex
                    minIndex = j;
                }

            }
            //create a variable to store the value at minIndex
            int temp = arr[minIndex];
            //assign the value at i to the value at minIndex
            arr[minIndex] = arr[i];
            //assign the value at temp to the value at i
            arr[i] = temp;

                logic(arr);

            //update the graph
        }
    }

    @Override
    public int getRunNeeded(int[] intArray) {
        printCYAN("Finding the number of loops needed for Selection Sort", "DEBUG: SelectionSort.java ---> ");
        actualRun = false;
        loop = 0;
        sort(intArray);
        printCYAN("Number of loops needed for Selection Sort: " + loop, "DEBUG: SelectionSort.java ---> ");
        return loop;
    }

    @Override
    public void run() {
        new Thread(() -> {
            actualRun = true;
            sortingHubController.disableButtons(true);
            printPURPLE("Starting Selection Sort", "DEBUG: SelectionSort.java ---> ");
            sort(intArray);
            printPURPLE("Selection Sort Complete", "DEBUG: SelectionSort.java ---> ");
            sortingHubController.updateGraph(intArray);
            printLine();
            sortingHubController.disableButtons(false);
            sortingHubController.setStatusBar(false);
        }).start();
    }

    public void logic (int [] arr ) {

        if (actualRun) {
            Platform.runLater(() -> sortingHubController.updateGraph(arr));
            Platform.runLater(() -> sortingHubController.setStatusBar(true));
            logicHelper();
        }
        if (!actualRun) {
            loop++;
        }
    }

    public void logicHelper(){
        String speed = sortingHubController.getSpeed();
        switch (speed) {
            case "Fast" -> {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            case "Medium" -> {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            case "Slow" -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            case "No Delay" -> {
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
