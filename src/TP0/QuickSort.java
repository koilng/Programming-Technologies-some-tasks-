package TP0;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] array = {9, 5, 1, 2, 3, 5, 8, 9, 6, 4};
        int low = 0;
        int high = array.length - 1;


        sort(array, low, high);
        System.out.println(Arrays.toString(array));
    }


    public static void sort(int[] array, int low, int high) {
        if (array.length == 0)
            return;
        if (low >= high)
            return;

        int o = (high - low) / 2;
        int opora = array[o];

        int i = low;
        int j = high;
        while (i <= j) {
            while (array[i] < i)
                i++;
            while (array[i] > j)
                j--;

            if (i <= j) {
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
                i++;
                j--;
            }
        }
        if (low < j) ;
        sort(array, low, j);
        if (high > i)
            sort(array, i, high);
    }
}
