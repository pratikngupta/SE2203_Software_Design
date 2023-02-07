package assignment1.pgupta85.assignment1;

import javafx.application.Platform;

import static assignment1.pgupta85.method.Debug.*;

public class MergeSort implements SortingStrategy {

    private SortingHubController sortingHubController;

    private int[] list;
    private int loop;
    private boolean actualRun;

    @Override
    public void SortingStrategy(int[] arr, SortingHubController sortingHubController) {
        this.sortingHubController = sortingHubController;
        this.list = arr;
    }

    @Override
    public void sort(int[] arr) {
    }

    @Override
    public void run() {
        new Thread(() -> {
            actualRun = true;
            sortingHubController.disableButtons(true);
            printPURPLE("Merge Selection Sort", "DEBUG: MergeSort.java ---> ");
            sort(list);
            printPURPLE("Merge Sort Complete", "DEBUG: MergeSort.java ---> ");
            sortingHubController.updateGraph(list);
            sortingHubController.disableButtons(false);
            printLine();
        }).start();
    }

    public void logic (int [] arr ) {
        if (actualRun) {
            Platform.runLater(() -> sortingHubController.updateGraph(arr));
            Platform.runLater(() -> sortingHubController.setStatusBar(true));
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (!actualRun) {
            loop++;
        }
    }

    @Override
    public int getRunNeeded(int[] intArray) {
        printCYAN("Finding the number of loops needed for Merge Sort", "DEBUG: MergeSort.java ---> ");
        actualRun = false;
        loop = 0;
        sort(intArray);
        printCYAN("Number of loops needed for Merge Sort: " + loop, "DEBUG: MergeSort.java ---> ");
        return loop;
    }
}
