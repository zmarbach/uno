package com.improving.bootcamp;

public class Card {
    private FaceEnum face;
    private ColorEnum color;

    public Card(FaceEnum face, ColorEnum color) {
        this.face = face;
        this.color = color;
    }

    public FaceEnum getFace() {
        return face;
    }

    public ColorEnum getColor() {
        return color;
    }

    @Override
    public String toString() {
        return face + " " + color;
    }
}
