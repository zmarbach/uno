package com.improving.bootcamp;

import java.util.Optional;

public interface IGame {
    void playCard(Card card, Optional<ColorEnum> color);
    boolean isPlayable(Card card);
    Card draw();
}
