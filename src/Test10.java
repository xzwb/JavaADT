import java.util.*;

// 多线程同时写集合
public class Test10 {

    public static List<String> list = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {


        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    list.add(Math.random() + "");
                }
            }).start();
        }

        while (true) {
            System.out.println(list);
        }
    }
}
