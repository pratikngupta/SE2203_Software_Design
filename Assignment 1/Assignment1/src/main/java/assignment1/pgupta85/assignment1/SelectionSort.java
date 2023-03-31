package assignment1.pgupta85.assignment1;

import javafx.application.Platform;

import static assignment1.pgupta85.method.Debug.*;

public class SelectionSort implements SortingStrategy {

    private SortingHubController controller;

    private int[] list;
    private int loop;
    private boolean actualRun;

    //create a constructor
    @Override
    public void setValue(int[] numbers, SortingHubController controller) {
        //assign the value of sortingHubController to the value of this.sortingHubController
        this.controller = controller;
        this.list = numbers;
    }

    @Override
    public void sort(int[] numbers) {

        //create a for loop to iterate through the array
        for (int i = 0; i < numbers.length - 1; i++) {
            //create a variable to store the value of i
            int minIndex = i;
            //create a for loop to iterate through the array
            for (int j = i + 1; j < numbers.length; j++) {
                //create an if statement to check if the value at j is less than the value at minIndex
                if (numbers[j] < numbers[minIndex]) {
                    //assign the value at j to the value at minIndex
                    minIndex = j;
                }
            }
            //create a variable to store the value at minIndex
            int temp = numbers[minIndex];
            //assign the value at i to the value at minIndex
            numbers[minIndex] = numbers[i];
            //assign the value at temp to the value at i
            numbers[i] = temp;
            logic(numbers);  //update the graph
        }
    }

    @Override
    public int getRunNeeded(int[] dummyArray) {
        //get the number of loops needed for Selection Sort
        printCYAN("Finding the number of loops needed for Selection Sort", "DEBUG: SelectionSort.java ---> ");
        actualRun = false;
        loop = 0;
        sort(dummyArray);
        printCYAN("Number of loops needed for Selection Sort: " + loop, "DEBUG: SelectionSort.java ---> ");
        //return the number of loops
        return loop;
    }

    @Override
    public void run() {
        //disable the buttons
        controller.disableButtons(true);
        actualRun = true;
        //start the selection sort
        printPURPLE("Starting Selection Sort", "DEBUG: SelectionSort.java ---> ");
        sort(list);
        printPURPLE("Selection Sort Complete", "DEBUG: SelectionSort.java ---> ");
        controller.updateGraph(list);
        printLine();
        //enable the buttons
        controller.disableButtons(false);
    }

    //code to update the graph and sleep the thread
    @Override
    public void logic(int[] arr) {
        //check if the actualRun is true
        if (actualRun) {
            //update the graph
            Platform.runLater(() -> controller.updateGraph(arr));
            Platform.runLater(() -> controller.setStatusBar(true));

            //sleep the thread for 25 milliseconds
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //if the actualRun is false
        if (!actualRun) {
            //increment the loop
            loop++;
        }
    }
}
