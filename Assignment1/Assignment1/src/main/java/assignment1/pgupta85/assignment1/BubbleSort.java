/**************************************************************************************************************
 Name: Pratik Narendra Gupta
 Student ID: 251211859
 Date: 9th February
 Task: Bubble Sort Algorithm
 - This class implements the SortingStrategy interface and implements the methods defined in the interface
 - This class implements the Bubble Sort algorithm
 - This class is used to sort the array of numbers using the Bubble Sort algorithm
 *****************************************************************************************************************/

package assignment1.pgupta85.assignment1;

import javafx.application.Platform;

import static assignment1.pgupta85.method.Debug.*;

public class BubbleSort implements SortingStrategy {

    //create a variable to store the controller
    private SortingHubController controller;

    //create a variable to store the list
    private int[] list;
    private int loop;
    private boolean actualRun;

    //create a constructor to initialize the variables
    @Override
    public void SortingStrategy(int[] numbers, SortingHubController controller) {
        this.controller = controller;
        this.list = numbers;
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
    public void sort(int[] numbers) {
        //create a for loop to iterate through the array
        for (int i = 0; i < numbers.length - 1; i++) {
            //create a for loop to iterate through the array
            for (int j = 0; j < numbers.length - i - 1; j++) {
                //check if the value at j is greater than the value at j + 1
                if (numbers[j] > numbers[j + 1]) {
                    //create a temporary variable to store the value at j
                    int temp = numbers[j];
                    //assign the value at j + 1 to the value at j
                    numbers[j] = numbers[j + 1];
                    //assign the value at j to the value at j + 1
                    numbers[j + 1] = temp;
                    //update the graph
                }
                logic(numbers);
            }
        }
    }

    @Override
    public void run() {
        //set the actualRun to true
        actualRun = true;
        //disable the buttons
        controller.disableButtons(true);
        printPURPLE("Starting Bubble Sort", "DEBUG: BubbleSort.java ---> ");
        //call the sort method
        sort(list);
        printPURPLE("Bubble Sort Complete", "DEBUG: BubbleSort.java ---> ");
        //update the graph
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
                Thread.sleep(15);
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

