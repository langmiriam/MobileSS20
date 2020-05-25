package com.miriamlang.flyingfish;


public class Obstacle {
    public enum Type {
        TOP, BOTTOM;
    }

    public int height;
    public Type type;
    public double x;

    public Obstacle(int height, Type type, double x) {
        this.height = height;
        this.type = type;
        this.x = x;
    }


}
