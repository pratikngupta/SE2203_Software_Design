package pgupta85.assignmentimproved;

public interface SortingStrategy extends Runnable {
    void sort(int[] arr);
    void run();
    void SortingStrategy(int[] arr, SortingHubController sortingHubController );

    int getRunNeeded(int[] intArray);

}
