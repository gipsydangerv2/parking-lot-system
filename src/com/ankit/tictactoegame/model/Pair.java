package com.ankit.tictactoegame.model;

public class Pair<K,V> {
    private K valueOne;
    private V valueTwo;

    public Pair(K valueOne, V valueTwo) {
        this.valueOne = valueOne;
        this.valueTwo = valueTwo;
    }

    public K getValueOne() {
        return valueOne;
    }

    public void setValueOne(K valueOne) {
        this.valueOne = valueOne;
    }

    public V getValueTwo() {
        return valueTwo;
    }

    public void setValueTwo(V valueTwo) {
        this.valueTwo = valueTwo;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "valueOne=" + valueOne +
                ", valueTwo=" + valueTwo +
                '}';
    }
}
