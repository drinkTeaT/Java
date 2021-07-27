import java.io.Serializable;
import java.util.*;

/**
 * @author 01401951
 */
public class Test {
    public static void main(String[] args) {
        Lru lru = new Lru(2);
        lru.put(1, 1);
        lru.put(2, 2);
        lru.put(3, 3);
        lru.showMap();
    }
}

class Lru {
    private int size;
    private Map<Integer, LruNode> map;
    private LruNode head;
    private LruNode tail;

    public Lru(int size) {
        this.size = size;
        this.map = new HashMap<>();
    }

    public int get(int key) {
        if (map.get(key) == null) {
            return -1;
        }
        LruNode node = map.get(key);
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        LruNode node = map.get(key);
        if (node != null) {
            node.value = value;
            moveToHead(node);
        } else if (map.keySet().size() < size) {
            LruNode newNode = new LruNode();
            newNode.next = head;
            newNode.value = value;
            map.put(key, newNode);
            moveToHead(newNode);
        } else {
            removeTail();
            LruNode newNode = new LruNode();
            newNode.next = head;
            newNode.value = value;
            map.put(key, newNode);
            moveToHead(newNode);
        }
    }

    private void moveToHead(LruNode node) {
        LruNode prev = node.prev;
        LruNode next = node.next;
        if (prev != null) {
            prev.next = next;
        }
        if (next == null) {
            tail = prev;
        }
        node.next = head;
        head = node;
    }

    private void removeTail() {
        if (tail == null) {
            return;
        }
        LruNode prev = tail.prev;
        prev.next = null;
        tail = prev;
    }

    public void showMap() {
        for (LruNode value : map.values()) {
            System.out.println(value.value);
        }
    }
}

class LruNode implements Serializable {
    int value;
    LruNode prev;
    LruNode next;
}
