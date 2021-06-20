import java.util.*;

public class Test {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();


        /* generate 10 random numbers from -50 to 49 */
        Random random = new Random();
        for (int i = 0; i < 100; ++i) {
            int value = random.nextInt(100)  - 50;
            list.add(value);
        }

    }

    public static void bucketSort(List<Integer> list) {
        // 计算桶
        int max = Collections.max(list);
        int min = Collections.min(list);
        int num = (int) Math.sqrt(max - min) + 1;
        List<List<Integer>> buckets = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            buckets.add(new ArrayList<>());
        }
        // 入桶
        for (int i : list) {
            int each = (i - min) / num;
            buckets.get(each).add(i);
        }
        // 桶内排序
        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket, (a, b) -> {
                if (a == b) {
                    return 0;
                } else if (a - b > 0) {
                    return 1;
                } else {
                    return -1;
                }
            });
        }
        // 桶合并
        List<Integer> result = new ArrayList<>(list.size());
        for (List<Integer> bucket : buckets) {
            result.addAll(bucket);
        }
        result.forEach(System.out::println);
    }



}
