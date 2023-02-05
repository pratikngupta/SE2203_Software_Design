package pgupta85.assignmentimproved;

import javafx.application.Platform;

import static pgupta85.method.Debug.*;

public class MergeSort implements SortingStrategy {

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
        mergeSort(arr, 0, arr.length - 1);
    }

    @Override
    public void run() {
        new Thread(() -> {
            actualRun = true;
            sortingHubController.disableButtons(true);
            printPURPLE("Merge Selection Sort", "DEBUG: MergeSort.java ---> ");
            sort(intArray);
            printPURPLE("Merge Sort Complete", "DEBUG: MergeSort.java ---> ");
            sortingHubController.updateGraph(intArray);
            sortingHubController.disableButtons(false);
            printLine();
        }).start();
    }

    public void mergeSort(int[] arr, int i, int i1) {

        logic(arr);

        //check if the value of i is less than the value of i1
        if (i < i1) {
            //create a variable to store the middle
            int middle = (i + i1) / 2;
            //call the mergeSort method
            mergeSort(arr, i, middle);
            //call the mergeSort method
            mergeSort(arr, middle + 1, i1);
            //call the merge method
            merge(arr, i, middle, i1);
        }
    }

    //create a method to merge the array
    public void merge(int[] arr, int i, int middle, int i1) {

        logic(arr);

        //create a variable to store the size of the left array
        int sizeOfLeftArray = middle - i + 1;
        //create a variable to store the size of the right array
        int sizeOfRightArray = i1 - middle;
        //create a variable to store the left array
        int[] leftArray = new int[sizeOfLeftArray];
        //create a variable to store the right array
        int[] rightArray = new int[sizeOfRightArray];
        //create a for loop to iterate through the left array
        //assign the value of the left array
        System.arraycopy(arr, i, leftArray, 0, sizeOfLeftArray);
        //create a for loop to iterate through the right array
        for (int j = 0; j < sizeOfRightArray; j++) {
            //assign the value of the right array
            rightArray[j] = arr[middle + 1 + j];
        }
        //create a variable to store the index of the left array
        int indexOfLeftArray = 0;
        //create a variable to store the index of the right array
        int indexOfRightArray = 0;
        //create a variable to store the index of the merged array
        int indexOfMergedArray = i;
        //create a while loop to iterate through the left and right array
        while (indexOfLeftArray < sizeOfLeftArray && indexOfRightArray < sizeOfRightArray) {
            //check if the value of the left array is less than the value of the right array
            if (leftArray[indexOfLeftArray] <= rightArray[indexOfRightArray]) {
                //assign the value of the left array to the merged array
                arr[indexOfMergedArray] = leftArray[indexOfLeftArray];
                //increment the value of the left array
                indexOfLeftArray++;
            } else {
                //assign the value of the right array to the merged array
                arr[indexOfMergedArray] = rightArray[indexOfRightArray];
                //increment the value of the right array
                indexOfRightArray++;
            }
            //increment the value of the merged array
            indexOfMergedArray++;
            logic(arr);
        }
        //create a while loop to iterate through the left array
        while (indexOfLeftArray < sizeOfLeftArray) {
            //assign the value of the left array to the merged array
            arr[indexOfMergedArray] = leftArray[indexOfLeftArray];
            //increment the value of the left array
            indexOfLeftArray++;
            //increment the value of the merged array
            indexOfMergedArray++;
            logic(arr);
        }
        //create a while loop to iterate through the right array
        while (indexOfRightArray < sizeOfRightArray) {
            //assign the value of the right array to the merged array
            arr[indexOfMergedArray] = rightArray[indexOfRightArray];
            //increment the value of the right array
            indexOfRightArray++;
            //increment the value of the merged array
            indexOfMergedArray++;
            logic(arr);
        }
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

    @Override
    public int getRunNeeded(int[] intArray) {
        printCYAN("Finding the number of loops needed for Merge Sort", "DEBUG: MergeSort.java ---> ");
        actualRun = false;
        loop = 0;
        sort(intArray);
        printCYAN("Number of loops needed for Merge Sort: " + loop, "DEBUG: MergeSort.java ---> ");
        return loop;
    }
}
