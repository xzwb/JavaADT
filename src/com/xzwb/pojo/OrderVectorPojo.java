package com.xzwb.pojo;

public class OrderVectorPojo implements Comparable {
    public int anInt;

    public OrderVectorPojo(int anInt) {
        this.anInt = anInt;
    }

    public OrderVectorPojo() {
    }

    public int getAnInt() {
        return anInt;
    }

    public void setAnInt(int anInt) {
        this.anInt = anInt;
    }

    @Override
    public String toString() {
        return "OrderVectorPojo{" +
                "anInt=" + anInt +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return anInt > ((OrderVectorPojo)o).anInt ? 1 : anInt < ((OrderVectorPojo)o).anInt ? -1 : 0;
    }
}
