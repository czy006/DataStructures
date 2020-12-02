package com.gzczy.datastructures.xiaoma.myarray;

import java.util.Objects;

/**
 * @Description 自己实现一个可扩容数组
 * 实现后再去看看底层Java ArrayList 进行对比
 * @Author chenzhengyu
 * @Date 2020-12-01 13:24
 */
@SuppressWarnings("unchecked")
public class MyArrayList<E> {

    /**
     * 元素的数量
     */
    private int size;
    /**
     * 所有的元素
     */
    private E[] elements;

    private static final int DEFAULT_CAPACITY = 10;
    private static final int ELEMENT_NOT_FOUND = -1;

    public MyArrayList(int capacity) {
        capacity = Math.max(capacity, DEFAULT_CAPACITY);
        elements = (E[]) new Object[capacity];
    }

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 添加元素到尾部
     *
     * @param element
     */
    public void add(E element) {
        add(size, element);
    }

    /**
     * 在index位置插入一个元素
     *
     * @param index
     * @param element
     */
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        // 如果当前大小 + 1
        ensureCapacity(index + 1);
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;
    }

    /**
     * 获取index位置的元素
     * @param index
     * @return
     */
    public E get(int index) {
        rangeCheck(index);
        return elements[index];
    }

    /**
     * 根据数组下标设置元素
     *
     * @param index
     * @param element
     * @return
     */
    public E set(int index, E element) {
        rangeCheck(index);
        E data = elements[index];
        elements[index] = element;
        return data;
    }

    /**
     * 根据下标移除元素 移除后元素往前移动，末尾的元素指向null
     *
     * @param index 数组下标
     * @return 返回被移出的元素
     */
    public E remove(int index) {
        rangeCheck(index);
        E data = elements[index];
        // 通过循环, 将index后面的所有元素向前移动一位
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        // 删除最后一个元素
        elements[size--] = null;
        return data;
    }

    /**
     * 清空数组
     */
    public void clear() {
        //Arrays.fill(elements, null);
        for (int i = 0; i < elements.length; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * 是否包含某个元素
     *
     * @param element
     * @return
     */
    public boolean contains(E element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查看元素的索引
     * @param element
     * @return
     */
    public int indexOf(E element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) return i; // n
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    /**
     * 元素数量
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 数组扩容操作
     */
    private void ensureCapacity(int capacity) {
        // 获取数组当前容量
        int oldCapacity = elements.length;
        //  当前数组长度 >= 当前大小 + 1 ，则无需扩容
        if (oldCapacity >= capacity) return;
        // 新数组的容量为原数组容量的1.5倍
        int newCapacity = oldCapacity + oldCapacity >> 1;
        E[] newElementData = (E[]) new Objects[newCapacity];
        //System.arraycopy(elements, 0, newElementData, 0, size);
        for (int i = 0; i < size; i++) {
            newElementData[i] = elements[i];
        }
        //指向新的地址引用
        elements = newElementData;
    }


    /**
     * 数组下标越界检查
     *
     * @param index
     */
    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("size:" + size + ",index:" + index);
        }
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("size:" + size + ",index:" + index);
        }
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("size:").append(size).append(",ListElement[");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                builder.append(",");
            }
            builder.append(elements[i].toString());
        }
        builder.append(']');
        return builder.toString();
    }
}
