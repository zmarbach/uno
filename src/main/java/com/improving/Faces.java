package com.improving;

public enum Faces {
    ZERO(0),
    ONE (1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    REVERSE(20),
    SKIP(20),
    DRAW_TWO(20),
    DRAW_FOUR(50),
    WILD(50);

    private final Integer value;

    Faces(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
