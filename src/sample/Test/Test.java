package Lesson_6.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
}


// 1
class A {
    public static void main(String[] args) {
        for (int i = 0; ++i > 0; ) {
            System.out.println(i);
        }
    }
}


















// 2
class B {
    int i = getInt();
    int k = 20;
    public int getInt() {
        return k + 1;
    }

    public static void main(String[] args) {
        B b = new B();
        System.out.println(b.i + " " + b.k);
    }
}















// 3
class C {
    public void process() {
        System.out.println("C ");
    }
}

class D extends C {
    public void process() throws RuntimeException {
        super.process();
        if(true) throw new RuntimeException();
        System.out.println("D ");
    }

    public static void main(String[] args) {
        try {
            ((C)new D()).process();
        } catch (Exception e) {
            System.out.println("Exception print");
        }
    }
}



















// 4

class E {
    public static void main(String[] args) {
        System.out.println((-(byte)128) >> 1 == 128 >> 1);
        System.out.println(128 >> 1);
    }
}

