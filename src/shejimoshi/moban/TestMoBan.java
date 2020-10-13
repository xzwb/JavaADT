package shejimoshi.moban;

public class TestMoBan {
    public static void main(String[] args) {
        SoyaMilk soyaMilk = new GreenBean();
        soyaMilk.make();
        System.out.println("-----------------");
        SoyaMilk soyaMilk1 = new BlackBeanSoyaMilk();
        soyaMilk1.make();
    }
}
