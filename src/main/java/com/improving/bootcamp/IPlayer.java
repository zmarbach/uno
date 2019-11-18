package com.improving.bootcamp;

public interface IPlayer {
    int handSize();
    Card draw(Game game);
    void takeTurn(Game game);
}
