package Sorts;

import java.util.Arrays;

class RadixSort {

    private static int getMax(int[] arr, int n) {
        int mx = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > mx) {
                mx = arr[i];
            }
        }
        return mx;
    }

    private static void countSort(int[] arr, int n, int exp) {
        int[] output = new int[n];
        int i;
        int[] count = new int[10];
        Arrays.fill(count, 0);
        // count[个位] = 次数
        for (i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
        }
        // 表示前面还有几位数，优化后的计数排序
        for (i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        // 从后往前
        for (i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        for (i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }

    private static void radixsort(int[] arr, int n) {

        int m = getMax(arr, n);

        // 比较个位 十位 百位进行比较
        for (int exp = 1; m / exp > 0; exp *= 10) {
            countSort(arr, n, exp);
        }
    }

    static void print(int[] arr, int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        int[] arr = {170, 45, 75, 90, 802, 24, 2, 66};
        int n = arr.length;
        radixsort(arr, n);
        print(arr, n);
    }
}
// Written by James Mc Dermott(theycallmemac)
