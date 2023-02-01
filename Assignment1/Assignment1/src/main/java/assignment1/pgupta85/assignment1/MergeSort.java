package assignment1.pgupta85.assignment1;

import javafx.application.Platform;

import static java.util.Arrays.*;

public class MergeSort implements SortingStrategy{

    private final SortingHubController sortingHubController;

    private final int[] intArray;

    //create a constructor to get the array from the SortingHubController
    public MergeSort(SortingHubController sortingHubController, int[] intArray) {
        //assign value to controller
        this.sortingHubController = sortingHubController;
        this.intArray = intArray;
    }

    @Override
    public void sort(int[] arr) {
        //write code as a thread
        new Thread(() -> {
            //call the mergeSort method
            mergeSort(arr, 0, arr.length - 1);
            //update the graph
            //print the array
            for (int i = 0; i < arr.length; i++) {
                System.out.println(arr[i] + " ");
            }
            //print the message
            System.out.println("Merge Sort Complete");
        }).start();

    }

    @Override
    public void run() {

    }

    public void mergeSort(int[] arr, int i, int i1) {
        Platform.runLater(() -> sortingHubController.updateGraph(arr));
        //sleep the thread
        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        Platform.runLater(() -> sortingHubController.updateGraph(arr));
        //sleep the thread
        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        System.arraycopy(arr, i + 0, leftArray, 0, sizeOfLeftArray);
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
            Platform.runLater(() -> sortingHubController.updateGraph(arr));
            //sleep the thread
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //create a while loop to iterate through the left array
        while (indexOfLeftArray < sizeOfLeftArray) {
            //assign the value of the left array to the merged array
            arr[indexOfMergedArray] = leftArray[indexOfLeftArray];
            //increment the value of the left array
            indexOfLeftArray++;
            //increment the value of the merged array
            indexOfMergedArray++;
            Platform.runLater(() -> sortingHubController.updateGraph(arr));
            //sleep the thread
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //create a while loop to iterate through the right array
        while (indexOfRightArray < sizeOfRightArray) {
            //assign the value of the right array to the merged array
            arr[indexOfMergedArray] = rightArray[indexOfRightArray];
            //increment the value of the right array
            indexOfRightArray++;
            //increment the value of the merged array
            indexOfMergedArray++;
            Platform.runLater(() -> sortingHubController.updateGraph(arr));
            //sleep the thread
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
