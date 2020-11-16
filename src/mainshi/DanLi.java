package mainshi;

/**
 * 双端检索机制
 */
public class DanLi {
    private static volatile DanLi danLi = null;

    private DanLi() {
        System.out.println("hahahah");
    }

    public static DanLi getInstance() {
        if (danLi == null) {
            synchronized (DanLi.class) {
                if (danLi == null) {
                    danLi = new DanLi();
                }
            }
        }
        return danLi;
    }
}
