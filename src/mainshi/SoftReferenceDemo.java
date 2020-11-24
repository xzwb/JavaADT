package mainshi;

import java.lang.ref.SoftReference;

public class SoftReferenceDemo {
    public static void main(String[] args) {
        System.gc();
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;
        System.gc();
        System.out.println(o1);
        System.out.println(softReference.get());
        try {
            byte[] bytes = new byte[30 * 1024 * 1024];
        } finally {
            System.out.println(o1);
            System.out.println(softReference.get());
        }

    }
}
