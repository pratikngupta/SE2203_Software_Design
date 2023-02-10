package assignment1.pgupta85.assignment1;

import javafx.application.Platform;

import static assignment1.pgupta85.method.Debug.*;

public class InsertionSort implements SortingStrategy{

    private SortingHubController controller;

    private int[] list;
    private int loop = 0;
    private boolean actualRun;

    @Override
    public void setValue(int[] numbers, SortingHubController controller) {
        this.controller = controller;
        this.list = numbers;
    }

    @Override
    public void sort(int[] numbers) {
        //write code as a thread
            //create a for loop to iterate through the array
            for (int i = 1; i < numbers.length; i++) {
                //create a variable to store the value at i
                int key = numbers[i];
                //create a variable to store the value of i - 1
                int j = i - 1;
                //create awhile loop to iterate through the array
                while (j >= 0 && numbers[j] > key) {
                    //assign the value at j to the value at j + 1
                    numbers[j + 1] = numbers[j];
                    //decrement j
                    j = j - 1;
                    //update the graph
                    logic(numbers);
                }
                //assign the value at j + 1 to the value at key
                numbers[j + 1] = key;
            }
    }

    @Override
    public int getRunNeeded(int[] dummyArray) {
        printCYAN("Finding the number of loops needed for Insertion Sort", "DEBUG: InsertionSort.java ---> ");
        actualRun = false;
        loop = 0;
        sort(dummyArray);
        printCYAN("Number of loops needed for Insertion Sort: " + loop, "DEBUG: InsertionSort.java ---> ");
        return loop;
    }

    @Override
    public void run() {
        controller.disableButtons(true);
            actualRun = true;
            printPURPLE("Insertion Selection Sort", "DEBUG: InsertionSort.java ---> ");
            sort(list);
            printPURPLE("Insertion Sort Complete", "DEBUG: InsertionSort.java ---> ");
            controller.updateGraph(list);
            printLine();
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
                Thread.sleep(50);
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


