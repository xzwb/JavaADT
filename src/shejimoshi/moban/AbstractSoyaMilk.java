package shejimoshi.moban;

public abstract class AbstractSoyaMilk implements SoyaMilk {

    void select() {
        System.out.println("选择好的新鲜的豆子");
    }

    abstract void add();

    void soak() {
        System.out.println("浸泡3小时");
    }

    void beat() {
        System.out.println("打豆浆");
    }

    @Override
    public final void make() {
        select();
        add();
        soak();
        beat();
        System.out.println("制作完毕");
    }
}
