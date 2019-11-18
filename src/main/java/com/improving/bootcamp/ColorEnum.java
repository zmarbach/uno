package com.improving.bootcamp;

public enum ColorEnum {
    RED (1),
    BLUE (2),
    YELLOW (3),
    GREEN (4),
    WILD (5);

    private final Integer value;

    ColorEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}


