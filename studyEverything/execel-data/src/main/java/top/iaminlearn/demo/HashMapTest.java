package top.iaminlearn.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Date: 2022/5/5 21:49
 */
public class HashMapTest {

    public static void main(String[] args) {
        HashMap map = new HashMap();

        for (int i = 1; i <= 12; i++) {
            map.put(new A(i),  "hello");
        }
    }
}

class A {

    private int num;

    public A(int num) {
        this.num = num;
    }
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        A a = (A) o;
//        return num == a.num;
//    }

    @Override
    public int hashCode() {
        return 100;
    }
}

