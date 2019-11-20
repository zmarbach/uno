package com.improving;

import java.util.List;

public interface IPlayer extends IPlayerInfo{
    Card draw(IGame game);
    void takeTurn(IGame game);
    void newHand(List<Card> cards);
}
