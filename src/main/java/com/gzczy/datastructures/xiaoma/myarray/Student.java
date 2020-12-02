package com.gzczy.datastructures.xiaoma.myarray;

/**
 * @Description
 * @Author chenzhengyu
 * @Date 2020-12-02 13:53
 */
public class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Student{");
        sb.append("name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append('}');
        return sb.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Object is finalize ... ");
    }
}
