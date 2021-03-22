package Sorts;

import static Sorts.SortUtils.print;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Youssef Ali (https://github.com/youssefAli11997)
 * @author Podshivalov Nikita (https://github.com/nikitap492)
 */
class CountingSort implements SortAlgorithm {

    @Override
    public <T extends Comparable<T>> T[] sort(T[] unsorted) {
        return sort(Arrays.asList(unsorted)).toArray(unsorted);
    }

    /**
     * This method implements the Generic Counting Sort
     *
     * @param list The list to be sorted
     *             <p>Sorts the list in increasing order The method uses list elements as keys in the
     *             frequency map
     */
    @Override
    public <T extends Comparable<T>> List<T> sort(List<T> list) {

        Map<T, Integer> frequency = new TreeMap<>();
        // The final output array
        List<T> sortedArray = new ArrayList<>(list.size());

        // Counting the frequency of @param array elements
        list.forEach(v -> frequency.put(v, frequency.getOrDefault(v, 0) + 1));

        // Filling the sortedArray
        for (Map.Entry<T, Integer> element : frequency.entrySet()) {
            for (int j = 0; j < element.getValue(); j++) {
                sortedArray.add(element.getKey());
            }
        }

        return sortedArray;
    }

    /**
     * Stream Counting Sort The same as method {@link CountingSort#sort(List)} } but this method uses
     * stream API
     *
     * @param list The list to be sorted
     */
    private static <T extends Comparable<T>> List<T> streamSort(List<T> list) {
        return list.stream()
                .collect(toMap(k -> k, v -> 1, (v1, v2) -> v1 + v2, TreeMap::new))
                .entrySet()
                .stream()
                .flatMap(entry -> IntStream.rangeClosed(1, entry.getValue()).mapToObj(t -> entry.getKey()))
                .collect(toList());
    }

    /**
     * 未优化的计数排序
     *
     * @param a
     * @return
     */
    public static int[] originCountSort(int[] a) {
        int b[] = new int[a.length];
        int max = a[0], min = a[0];
        for (int i : a) {
            if (i > max) {
                max = i;
            }
            if (i < min) {
                min = i;
            }
        }
        //这里k的大小是要排序的数组中，元素大小的极值差+1
        int k = max + 1;
        int c[] = new int[k];
        for (int i = 0; i < a.length; ++i) {
            c[a[i]] += 1;
        }
        //按存取的方式取出c的元素
        int i = 0;
        int count = 0;
        while (i <= k - 1) {
            for (int j = 0; j < c[i]; j++) {
                b[count] = i;
                count++;
            }
            i++;
        }
        return b;
    }

    /**
     * 优化后的计数排序
     *
     * @param a
     * @return
     */
    public static int[] countSort(int[] a) {
        //使用最大值和最小值的方式是一种优化的计数排序
        //可以兼容负数的情况，同时能减少存储的空间，比如最大数是100，但实际上只有90-100这10个数字
        //所以仅仅需要10个存储空间即可
        int max = a[0], min = a[0];
        for (int i : a) {
            max = Math.max(max, i);
            min = Math.min(min, i);
        }
        System.out.println("max:" + max + "  min:" + min);
        int k = max - min + 1;
        System.out.println("count array len：" + k);

        int c[] = new int[k];
        //先是count计数词频
        for (int i = 0; i < a.length; ++i) {
            c[a[i] - min]++;
        }
        System.out.println("count: " + Arrays.toString(c));
        // c[i]的值表示返回数组的位置
        for (int i = 1; i < c.length; ++i) {
            c[i] = c[i] + c[i - 1];
        }

        System.out.println("sumCount：" + Arrays.toString(c));

        //存储最终的排序结果
        int b[] = new int[a.length];
        //这里从后向前遍历，如果从前往后的话，之后每个c[i]都要加1，代表位置往后移了一个位置
        for (int i = a.length - 1; i >= 0; i--) {
            int pos = a[i] - min;
            int sumCount = c[pos];
            System.out.println(a[i] + " 在原数组的排序后的位置是： " + (sumCount - 1));
            b[sumCount - 1] = a[i];
            // 位置减1
            c[pos]--;

        }
        return b;
    }

    // Driver Program
    public static void main(String[] args) {
        // Integer Input
        List<Integer> unsortedInts =
                Stream.of(4, 23, 6, 78, 1, 54, 23, 1, 9, 231, 9, 12).collect(toList());
        CountingSort countingSort = new CountingSort();

        System.out.println("Before Sorting:");
        print(unsortedInts);

        // Output => 1 1 4 6 9 9 12 23 23 54 78 231
        System.out.println("After Sorting:");
        print(countingSort.sort(unsortedInts));
        System.out.println("After Sorting By Streams:");
        print(streamSort(unsortedInts));

        System.out.println("\n------------------------------\n");
        for (int i : CountingSort.countSort(CountingSort.originCountSort(new int[]{4, 23, 6, 78, 1, 54, 23, 1, 9, 231, 9, 12}))) {
            System.out.print(i + " ");
        }
        System.out.println("\n------------------------------\n");

        // String Input
        List<String> unsortedStrings =
                Stream.of("c", "a", "e", "b", "d", "a", "f", "g", "c").collect(toList());

        System.out.println("Before Sorting:");
        print(unsortedStrings);

        // Output => a a b c c d e f g
        System.out.println("After Sorting:");
        print(countingSort.sort(unsortedStrings));

        System.out.println("After Sorting By Streams:");
        print(streamSort(unsortedStrings));
    }
}
