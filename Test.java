import java.util.*;

public class Test {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();


        /* generate 10 random numbers from -50 to 49 */
        Random random = new Random();
        for (int i = 0; i < 100; ++i) {
            int value = random.nextInt(100) - 50;
            list.add(value);
        }

    }

    public static void mergeSort(List<Integer> list) {

    }


}
