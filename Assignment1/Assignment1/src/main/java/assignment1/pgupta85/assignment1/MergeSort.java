package assignment1.pgupta85.assignment1;

import javafx.application.Platform;

public class MergeSort implements SortingStrategy{

    private SortingHubController sortingHubController;

    private int[] intArray;

    //create a constructor to get the array from the SortingHubController
    public MergeSort(SortingHubController sortingHubController, int[] intArray) {
        //assign value to controller
        this.sortingHubController = sortingHubController;
        this.intArray = intArray;
    }

    @Override
    public void sort(int[] arr) {
        System.out.println("New Merge Sort");
        //write code as a thread
        new Thread(() -> {
            //create a variable to store the length of the array
            int n = arr.length;
            //create a variable to store the middle of the array
            int mid = n / 2;
            //create a variable to store the left side of the array
            int[] left = new int[mid];
            //create a variable to store the right side of the array
            int[] right = new int[n - mid];
            //create a for loop to iterate through the array
            for (int i = 0; i < mid; i++) {
                //assign the value at i to the value at left
                left[i] = arr[i];
            }
            //create a for loop to iterate through the array
            for (int i = mid; i < n; i++) {
                //assign the value at i to the value at right
                right[i - mid] = arr[i];
            }
            //call the mergeSort method
            mergeSort(left);
            //call the mergeSort method
            mergeSort(right);
            //call the merge method
            merge(left, right, arr);
            //update the graph
            sortingHubController.updateGraph(arr);
            //print the array
            for (int i = 0; i < arr.length; i++) {
                System.out.println(arr[i] + " ");
            }
            //print the message
            System.out.println("Merge Sort Complete");
            //exit the program
            Platform.exit();
        }).start();
    }

    @Override
    public void run() {

    }

    //create a method mergeSort
    public void mergeSort(int[] arr) {
        //create a variable to store the length of the array
        int n = arr.length;
        //create a variable to store the middle of the array
        int mid = n / 2;
        //create a variable to store the left side of the array
        int[] left = new int[mid];
        //create a variable to store the right side of the array
        int[] right = new int[n - mid];
        //create a for loop to iterate through the array
        for (int i = 0; i < mid; i++) {
            //assign the value at i to the value at left
            left[i] = arr[i];
        }
        //create a for loop to iterate through the array
        for (int i = mid; i < n; i++) {
            //assign the value at i to the value at right
            right[i - mid] = arr[i];
        }
        //call the mergeSort method
        mergeSort(left);
        //call the mergeSort method
        mergeSort(right);
        //call the merge method
        merge(left, right, arr);
    }

    //create a method to merge the array
    public void merge(int[] left, int[] right, int[] arr) {
        //create a variable to store the length of the left array
        int nL = left.length;
        //create a variable to store the length of the right array
        int nR = right.length;
        //create a variable to store the value of i
        int i = 0;
        //create a variable to store the value of j
        int j = 0;
        //create a variable to store the value of k
        int k = 0;
        //create a while loop to iterate through the array
        while (i < nL && j < nR) {
            //create an if statement to check if the value at i is less than the value at j
            if (left[i] <= right[j]) {
                //assign the value at k to the value at i
                arr[k] = left[i];
                //increment i
                i++;
            } else {
                //assign the value at k to the value at j
                arr[k] = right[j];
                //increment j
                j++;
            }
            //increment k
            k++;
        }
        //create a while loop to iterate through the array
        while (i < nL) {
            //assign the value at k to the value at i
            arr[k] = left[i];
            //increment i
            i++;
            //increment k
            k++;
        }
        //create a while loop to iterate through the array
        while (j < nR) {
            //assign the value at k to the value at j
            arr[k] = right[j];
            //increment j
            j++;
            //increment k
            k++;
        }
    }
}
