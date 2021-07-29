import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 01401951
 */
public class Test {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(9, 78, 59, 27, 3, 95, 2);
        merge(numbers);
        System.out.println(numbers);
    }
    private static void merge(List<Integer> numbers) {
        if (numbers.size() <= 1) {
            return;
        }
        List<Integer> left = numbers.subList(0, numbers.size() / 2);
        List<Integer> right = numbers.subList(numbers.size() / 2, numbers.size());
        merge(left);
        merge(right);
        doMergeList(left, right, numbers);
    }
    private static void doMergeList(List<Integer> realLeft, List<Integer> realRight, List<Integer> numbers) {
        List<Integer> left = new ArrayList<>(realLeft);
        List<Integer> right = new ArrayList<>(realRight);
        int count = 0;
        int l = 0;
        int r = 0;
        while (l < left.size() && r < right.size()) {
            if (left.get(l) <= right.get(r)) {
                numbers.set(count, left.get(l));
                l++;
            } else {
                numbers.set(count, right.get(r));
                r++;
            }
            count++;
        }
        while (l < left.size()) {
            numbers.set(count, left.get(l));
            l++;
            count++;
        }
        while (r < right.size()) {
            numbers.set(count, right.get(r));
            r++;
            count++;
        }
    }
}