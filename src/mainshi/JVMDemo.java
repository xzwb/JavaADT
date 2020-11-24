package mainshi;

public class JVMDemo {
    public static void main(String[] args) throws InterruptedException {
        t2();
    }

    /**
     * 获取jvm内存大小
     */
    static void t1() {
        System.out.println(Runtime.getRuntime().availableProcessors());
        // jvm试图使用的的内存
        long maxMemory = Runtime.getRuntime().maxMemory();
        // jvm的内存总量
        long totalMemory = Runtime.getRuntime().totalMemory();
        System.out.println(maxMemory/(double)1024/1024);
        System.out.println(totalMemory/(double)1024/1024);

    }

    /**
     * 查看某个正在运行的java程序 他的各种信息
     */
    static void t2() throws InterruptedException {
        System.out.println("***hello world");
        Thread.sleep(Integer.MAX_VALUE);
    }
}
