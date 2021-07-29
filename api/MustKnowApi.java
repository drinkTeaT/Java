package api;


import java.util.*;
import java.util.Collections;

/**
 * @Description: 一定要知道的api
 * @Author: 01401951
 * @Date: 2021/7/27
 **/
public class MustKnowApi {
    public static void main(String[] args) {
        SortApi.sort();
        MathApi.math();
        SubListApi.sub();
    }
}

/**
 * 排序API
 */
class SortApi {
    /**
     * 减号可以看做大于号
     */
    public static void sort() {
        // 数字正序排序
        Collections.sort(Data.numbers);
        System.out.println("数字正序排序:" + Data.numbers);
        // 数字逆序排序
        Collections.sort(Data.numbers, (a, b) -> b - a);
        System.out.println("数字逆序排序:" + Data.numbers);
        // 对象正序排序
        Collections.sort(Data.nodes, (a, b) -> a.getNumber() - b.getNumber());
        System.out.println("对象正序排序:" + Data.nodes);
        // 对象逆序排序
        Collections.sort(Data.nodes, (a, b) -> b.getNumber() - a.getNumber());
        System.out.println("对象逆序排序:" + Data.nodes);
    }
}

/**
 * 数学API
 */
class MathApi {
    /**
     * 数学类的api
     */
    public static void math() {
        // 绝对值
        int abs = Math.abs(-1);
        System.out.println(abs);
        // power的缩写，power还有乘方的意思。为了兼容小数输出一定为double
        double pow = Math.pow(2, 2);
        System.out.println(pow);
        // 开2次方根 要记得加上d哦
        double gen = Math.pow(8, 1d / 3d);
        System.out.println(gen);
    }
}

/**
 * 截断API
 */
class SubListApi {
    public static void sub() {
        List<Integer> list = Arrays.asList(27, 59, 8, 72, 19, 8758, 1, 29);
        // 左闭右开
        List<Integer> sub = list.subList(0, 2);
        System.out.println(sub);
        // sub里的值改变会影响list里的值
        sub.set(0, 3);
        System.out.println(list);
    }
}