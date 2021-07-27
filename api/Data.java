package api;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @Author: 01401951
 * @Date: 2021/7/27
 **/
public class Data {
    public static List<Integer> numbers = Arrays.asList(218, 4, 7, 2, 98, 1, 592, 1, 5, 21, 56, 28, 3);

    public static List<Node> nodes = Arrays.asList(new Node(1, "1"), new Node(2, "2"), new Node(10, "10"), new Node(5, "5"));
}

class Node implements Serializable {
    private int number;
    private String name;

    public Node(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Node{" +
                "number=" + number +
                ", name='" + name + '\'' +
                '}';
    }
}