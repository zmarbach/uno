package com.improving;

public interface IPlayer extends IPlayerInfo{
    Card draw(IGame game);
    void takeTurn(IGame game);
}
