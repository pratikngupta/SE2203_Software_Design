# Assignment 1

## Check-List

### Basic file names

- [ ]   controller class --> `SortingHubController`
- [ ]  .fxml file --> `SortingHub-view.fxml`
- [ ]   application class --> `SortingHub`
- [ ]   Interface class --> `SortingStrategy`
- [ ]   Merge Sort --> `MergeSort`

- any one of the following
- [ ]   Selection Sort --> `SelectionSort`
- [ ]   Quick Sort --> `QuickSort`
- [ ]   Insertion Sort --> `InsertionSort`


## Relationship
- [ ] `SortingStrategy` extends `Runnable`.
- [ ] All sort method implement `SortingStrategy`.
- [ ] All sorting class have and association with `SortingHubController`

## `SortingHubController`
- [ ] Have private int[] called intArray
- [ ] Have private `SortingStrategy` **object  called** `SortingMethod`
- [ ] Public method called `setSortStrategy`
- [ ] Public method called `updateGraph` **with a parameter :** `int [] data`
- [ ] All other method to make this work

## `SortingStrategy`
- [ ] Extends `Runnable` interface
- [ ] Have declaration for `run()` method
- [ ] Have `void sort(int [] numbers)`

## `Sort Method`
- [ ] Have private int[] called list
- [ ] Have private `SortingHubController` **object  called** `controller`
- [ ] Implement method from interface called `run()`
- [ ] Implement method from interface called `sort(int [] numbers)`
- [ ] All other method to make this work

## Picky details
- [ ] GUI Elements used: `Button`, `ComboBox`, `label`, `Pane`, `Anchor Pane`, `Slider`
- [ ] Rectangle is created using `Rectangle`
- [ ] Program starts with "Merge Sort" selected in the ComboBox and "64" selected in the Slider
- [ ] Slider has a range form 32 - 128
- [ ] generated integers are distinct (no duplications) and range from 1 to the array size.
- [ ] Reset button will reset back to "Merge Sort" and "64"
- [ ] Dimension of Anchor Pane is : `800 x 400`
- [ ] Color of  bars is : `rgb(236,40,3)`
- [ ] Color of pane is : `rgb(229,229,229)`
- [ ] Use the provided Western logo image to set up the icon of the main window.