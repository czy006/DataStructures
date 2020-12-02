package com.gzczy.datastructures.xiaoma.myarray;

import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author chenzhengyu
 * @Date 2020-12-02 13:53
 */
public class ArrayListTest {

    public static void main(String[] args) throws Exception{
        MyArrayList<Student> arrayList = new MyArrayList(5);
        Student czy = new Student("czy", 24);
        Student wjr = new Student("wjr", 23);
        Student yejoe = new Student("yejoe", 25);
        arrayList.add(czy);
        arrayList.add(wjr);
        arrayList.add(yejoe);
        System.out.println(arrayList.get(0));
        arrayList.set(1,czy);
        System.out.println(arrayList.toString());
        arrayList.remove(0);
        System.out.println(arrayList.toString());
        TimeUnit.SECONDS.sleep(1);
        arrayList.clear();
        System.gc();
    }
}
