package com.improving;

public class Card {
    private Faces face;
    private Colors color;

    public Card(Faces face, Colors color) {
        this.face = face;
        this.color = color;
    }

    public Faces getFace() {
        return face;
    }

    public Colors getColor() {
        return color;
    }

    @Override
    public String toString() {
        return face + " " + color;
    }
}
