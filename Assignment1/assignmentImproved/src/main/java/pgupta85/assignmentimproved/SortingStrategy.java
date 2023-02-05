package pgupta85.assignmentimproved;

import static pgupta85.method.Debug.*;

public interface SortingStrategy extends Runnable {
    void sort(int[] arr);
    void run();
    void SortingStrategy(int[] arr, SortingHubController sortingHubController);

    int getRunNeeded(int[] intArray);

}
