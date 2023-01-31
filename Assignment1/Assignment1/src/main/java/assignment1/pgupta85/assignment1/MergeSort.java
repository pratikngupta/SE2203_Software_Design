package assignment1.pgupta85.assignment1;

import javafx.application.Platform;

import static java.util.Arrays.*;

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

        }).start();
    }


    @Override
    public void run() {
    }

}
