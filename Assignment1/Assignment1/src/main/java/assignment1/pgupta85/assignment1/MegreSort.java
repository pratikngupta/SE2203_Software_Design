package assignment1.pgupta85.assignment1;

public class MegreSort implements SortingStrategy {

    //private int[] intArray;
    private int[] tempMergArr;

    private SortingHubController controller;

    @Override
    public void sort(int[] arr) {
        //assign controller
        //merge sort method recursively divides the array into two halves and then merges them
        this.tempMergArr = new int[arr.length];
        doMergeSort(0, arr.length - 1, arr);
        //now show how the sorting is done using the controller
    }

    //create a method to divide the array into two halves
    //and then merge them
    private void doMergeSort(int lowerIndex, int higherIndex, int[] arr) {
        controller.updateGraph();
        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            // Below step sorts the left side of the array
            doMergeSort(lowerIndex, middle, arr);
            // Below step sorts the right side of the array
            doMergeSort(middle + 1, higherIndex, arr);
            // Now merge both sides
            mergeParts(lowerIndex, middle, higherIndex, arr);
        }
    }
    //create a method to merge the two halves
    private void mergeParts(int lowerIndex, int middle, int higherIndex, int[] arr) {
        for (int i = lowerIndex; i <= higherIndex; i++) {
            tempMergArr[i] = arr[i];
        }
        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        while (i <= middle && j <= higherIndex) {
            if (tempMergArr[i] <= tempMergArr[j]) {
                arr[k] = tempMergArr[i];
                i++;
            } else {
                arr[k] = tempMergArr[j];
                j++;
            }
            k++;
        }
        while (i <= middle) {
            arr[k] = tempMergArr[i];
            k++;
            i++;
        }
    }


    @Override
    public void run() {
        //use treading to show the sorting
        Thread thread = new Thread(() -> {
            sort(controller.getArray());
        });
        thread.start();

    }
}
