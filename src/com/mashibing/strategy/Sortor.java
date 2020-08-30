package com.mashibing.strategy;

/**
 * @Description Sortor
 * @Author Radish
 * @Date 2020-08-30 08:49
 */
public class Sortor<T> {
    public void sort(T[] arr, Comparator<T> comparator) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minPos = i;
            for (int j = i + 1; j < arr.length; j++) {
                minPos = comparator.compare(arr[j],arr[minPos]) == -1 ? j : minPos;
            }
            swap(arr,minPos,i);
        }
    }
    void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
