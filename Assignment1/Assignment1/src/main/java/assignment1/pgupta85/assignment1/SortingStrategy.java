package assignment1.pgupta85.assignment1;

import static assignment1.pgupta85.method.Debug.*;

public interface SortingStrategy extends Runnable {
    void sort(int[] arr);
    void run();
    void SortingStrategy(int[] numbers, SortingHubController sortingHubController);

    int getRunNeeded(int[] intArray);

}
