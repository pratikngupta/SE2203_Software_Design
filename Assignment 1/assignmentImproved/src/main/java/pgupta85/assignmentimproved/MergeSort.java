package pgupta85.assignmentimproved;

import javafx.application.Platform;

import static pgupta85.assignmentimproved.Debug.*;

public class MergeSort extends Debug implements SortingStrategy  {

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
            sortingHubController.disableDuringSorting(true);
            actualRun = true;
            printPURPLE("Merge Selection Sort", "DEBUG: MergeSort.java ---> ");
            sort(intArray);
            printPURPLE("Merge Sort Complete", "DEBUG: MergeSort.java ---> ");
            sortingHubController.updateGraph(intArray);
            printLine();
            sortingHubController.disableDuringSorting(false);
        }).start();
    }

    public void mergeSort(int[] arr, int i, int i1) {

        //write code as a thread to from the merge sort for left and right array at the same time
        logic(arr);
        //check if the value of i is less than the value of i1
        if (i < i1) {
            //create a variable to store the middle value
            int middle = (i + i1) / 2;
            //create a variable to store the left array
            int[] leftArray = new int[middle - i + 1];
            //create a variable to store the right array
            int[] rightArray = new int[i1 - middle];
            //create a for loop to iterate through the left array
            for (int j = 0; j < leftArray.length; j++) {
                //assign the value of the left array
                leftArray[j] = arr[i + j];
            }
            //create a for loop to iterate through the right array
            for (int j = 0; j < rightArray.length; j++) {
                //assign the value of the right array
                rightArray[j] = arr[middle + 1 + j];
            }
            //create a thread to perform the merge sort for the left array
            Thread leftThread = new Thread(() -> {
                //perform the merge sort for the left array
                mergeSort(leftArray, 0, leftArray.length - 1);
            });
            //create a thread to perform the merge sort for the right array
            Thread rightThread = new Thread(() -> {
                //perform the merge sort for the right array
                mergeSort(rightArray, 0, rightArray.length - 1);
            });
            //start the thread for the left array
            leftThread.start();
            //start the thread for the right array
            rightThread.start();
            //join the thread for the left array
            try {
                leftThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //join the thread for the right array
            try {
                rightThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //create a variable to store the index of the left array
            int indexOfLeftArray = 0;
            //create a variable to store the index of the right array
            int indexOfRightArray = 0;
            //create a variable to store the index of the merged array
            int indexOfMergedArray = i;
            //create a while loop to
            while (indexOfLeftArray < leftArray.length && indexOfRightArray < rightArray.length) {
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
            }
            //create a while loop to iterate through the left array

            while (indexOfLeftArray < leftArray.length) {
                //assign the value of the left array to the merged array
                arr[indexOfMergedArray] = leftArray[indexOfLeftArray];
                //increment the value of the left array
                indexOfLeftArray++;
                //increment the value of the merged array
                indexOfMergedArray++;
            }
            //create a while loop to iterate through the right array
            while (indexOfRightArray < rightArray.length) {
                //assign the value of the right array to the merged array
                arr[indexOfMergedArray] = rightArray[indexOfRightArray];
                //increment the value of the right array
                indexOfRightArray++;
                //increment the value of the merged array
                indexOfMergedArray++;
            }
            //check if the actual run is true
            logic(arr);
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

    public void logic(int[] arr) {

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
    public int getRunNeeded(int[] intArray) {
        printCYAN("Finding the number of loops needed for Merge Sort", "DEBUG: MergeSort.java ---> ");
        actualRun = false;
        loop = 0;
        sort(intArray);
        printCYAN("Number of loops needed for Merge Sort: " + loop, "DEBUG: MergeSort.java ---> ");
        return loop;
    }

}
