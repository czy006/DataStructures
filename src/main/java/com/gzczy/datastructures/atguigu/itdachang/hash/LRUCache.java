package com.gzczy.datastructures.atguigu.itdachang.hash;

import java.util.HashMap;

/**
 * @Description #146 LRU Cache 缓存机制，自行实现
 * @Author chenzhengyu
 * @Date 2021-01-28 13:22
 */
public class LRUCache {

    private HashMap<Integer, Node> map = new HashMap<>();

    private int capacity;

    private int size;

    private Node tail;

    private Node head;

    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node() {
        }

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        int size = 0;
        head = new Node();
        tail = new Node();

        head.next = tail;
        tail.prev = head;
    }

    public int get(Integer key) {
        Node result = map.get(key);
        if (result == null) {
            return -1;
        }
        moveToTail(result);
        return result.value;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        //更新值，并且把值放置末尾
        if (node != null) {
            node.value = value;
            moveToTail(node);
        } else {
            Node newNode = new Node(key,value);
            map.put(key,newNode);
            //追加到末尾
            addToTail(newNode);
            size ++;
            // 判断size是否溢出，溢出则需要移除头节点 腾出空位
            if (size > capacity){
                Node head = removeHead();
                map.remove(head.key);
                size--;
            }
        }
    }

    /**
     * 移动节点到尾部：相当于先移除当前节点，然后再追加到尾部
     * @param node
     */
    public void moveToTail(Node node){
        removeNode(node);
        addToTail(node);
    }

    /**
     * 移除节点
     * @param node
     */
    public void removeNode(Node node){
        //node的上个节点的下个节点指针 = 当前节点的下个节点
        node.prev.next = node.next;
        //node的下个节点的上个节点指针 = 当前节点的上个节点
        node.next.prev = node.prev;
    }

    /**
     * 追加的尾部
     * @param node
     */
    public void addToTail(Node node){
        // 当前节点的下个节点 = 尾部节点
        node.next = tail;
        // 当前节点的上个节点 = 尾部节点的上个节点
        node.prev = tail.prev;
        // 尾部节点的上个节点的下个节点 = 下个节点
        tail.prev.next = node;
        // 尾部节点的上个节点 = 当前节点
        tail.prev = node;
    }

    /**
     * 移除头节点
     * @return
     */
    public Node removeHead(){
        //头节点是哨兵节点，所以下个节点才是真正意义的节点
        Node realHead = head.next;
        // 移除这个节点并返回真正的头节点
        removeNode(realHead);
        return realHead;
    }

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        System.out.println(lRUCache.get(1));
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        System.out.println(lRUCache.get(2));
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(lRUCache.get(1));
        System.out.println(lRUCache.get(3));
        System.out.println(lRUCache.get(4));
    }
}
