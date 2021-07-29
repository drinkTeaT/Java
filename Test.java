import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 01401951
 */
public class Test {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(9, 78, 59, 27, 3, 95, 209, 23, 3, 92, 8, 4, 7, 95, 83, 24, 63, 24, 6, 23, 4, 62, 3, 6, 23, 6);
        int[] n = new int[]{5, 32, 79, 57, 93, 27, 96, 83, 26, 23, 46};
        mySort(n, 0, n.length - 1);
        for (int i : n) {
            System.out.println(i);
        }
        quickSort(numbers, 0, numbers.size() - 1);

        System.out.println(numbers);
        System.out.println(binarySearch(4, numbers));
    }

    /**
     * 二分搜索返回位置
     *
     * @param target
     * @param numbers
     * @return
     */
    private static int binarySearch(int target, List<Integer> numbers) {
        return doBinarySearch(target, 0, numbers.size() - 1, numbers);
    }

    private static int doBinarySearch(int target, int left, int right, List<Integer> numbers) {
        if (left >= right) {
            return numbers.get(left) == target ? left : -1;
        }
        int mid = left + (right - left) / 2;
        if (numbers.get(mid) == target) {
            return mid;
        } else if (numbers.get(mid) > target) {
            return doBinarySearch(target, left, mid - 1, numbers);
        } else {
            return doBinarySearch(target, mid + 1, right, numbers);
        }
    }

    private static void quickSort(List<Integer> numbers, int left, int right) {
        if (left >= right) {
            return;
        }
        int point = partition(numbers, left, right);
        quickSort(numbers, left, point - 1);
        quickSort(numbers, point + 1, right);
    }

    private static int partition(List<Integer> numbers, int left, int right) {
        int point = left + (right - left) / 2;
        swap(numbers, point, left);
        // 开始比较和交换位置
        int lp = left;
        int rp = right;
        while (lp < rp) {
            if (numbers.get(lp) > numbers.get(lp + 1)) {
                swap(numbers, lp, lp + 1);
                lp++;
            } else {
                swap(numbers, lp + 1, rp);
                rp--;
            }
        }
        return lp;
    }

    private static void swap(List<Integer> numbers, int left, int point) {
        int pValue = numbers.get(point);
        int lValue = numbers.get(left);
        numbers.set(point, lValue);
        numbers.set(left, pValue);
    }


    private static void mySort(int[] numbers, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mySort(numbers, left, mid);
        mySort(numbers, mid + 1, right);
        System.out.println(left + " " + mid + " " + right);
        doMerge(numbers, left, mid + 1, right);
    }

    private static void doMerge(int[] numbers, int left, int mid, int right) {
        int[] copy = numbers.clone();
        int l = left;
        int r = mid;
        int p = left;
        while (l < mid && r <= right) {
            if (copy[l] <= copy[r]) {
                numbers[p] = copy[l];
                l++;
            } else {
                numbers[p] = copy[r];
                r++;
            }
            p++;
        }
        while (l < mid) {
            numbers[p] = copy[l];
            l++;
            p++;
        }
        while (r <= right) {
            numbers[p] = copy[r];
            p++;
            r++;
        }
    }

}