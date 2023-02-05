package pgupta85.assignmentimproved;

import javafx.application.Platform;

import static pgupta85.method.Debug.*;

public class InsertionSort implements SortingStrategy{

    private SortingHubController sortingHubController;

    private int[] intArray;
    private int loop = 0;
    private boolean actualRun;

    @Override
    public void SortingStrategy(int[] arr, SortingHubController sortingHubController) {
        this.sortingHubController = sortingHubController;
        this.intArray = arr;
    }

    @Override
    public void sort(int[] arr) {
        //write code as a thread
            //create a for loop to iterate through the array
            for (int i = 1; i < arr.length; i++) {
                //create a variable to store the value at i
                int key = arr[i];
                //create a variable to store the value of i - 1
                int j = i - 1;
                //create awhile loop to iterate through the array
                while (j >= 0 && arr[j] > key) {
                    //assign the value at j to the value at j + 1
                    arr[j + 1] = arr[j];
                    //decrement j
                    j = j - 1;
                    //update the graph
                    logic(arr);
                }
                //assign the value at j + 1 to the value at key
                arr[j + 1] = key;
            }
    }

    @Override
    public int getRunNeeded(int[] intArray) {
        printCYAN("Finding the number of loops needed for Insertion Sort", "DEBUG: InsertionSort.java ---> ");
        actualRun = false;
        loop = 0;
        sort(intArray);
        printCYAN("Number of loops needed for Insertion Sort: " + loop, "DEBUG: InsertionSort.java ---> ");
        return loop;
    }

    @Override
    public void run() {
        new Thread(() -> {
            actualRun = true;
            sortingHubController.disableButtons(true);
            printPURPLE("Insertion Selection Sort", "DEBUG: InsertionSort.java ---> ");
            sort(intArray);
            printPURPLE("Insertion Sort Complete", "DEBUG: InsertionSort.java ---> ");
            sortingHubController.updateGraph(intArray);
            sortingHubController.setStatusBar(false);
            printLine();
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


