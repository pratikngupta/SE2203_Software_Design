/**************************************************************************************************************
 Name: Pratik Narendra Gupta
 Student ID: 251211859
 Date: 9th February
 Task: Interface for Sorting Strategy
    - This interface is used to implement the sorting algorithms
    - This extends the Runnable interface to implement the run method
 *****************************************************************************************************************/

package assignment1.pgupta85.assignment1;

import static assignment1.pgupta85.method.Debug.*;

public interface SortingStrategy extends Runnable {
    void sort(int[] numbers);
    void run();
    void SortingStrategy(int[] numbers, SortingHubController controller);
    int getRunNeeded(int[] intArray);
    void logic(int[] arr);
}
