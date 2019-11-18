package com.improving.bootcamp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Player implements IPlayer {
    List<Card> hand;
    String name = "";

    public Player(List<Card> hand) {
        this.hand = hand;
    }

    public void takeTurn(Game game) {
    //do this if not Draw 4 or Draw 2 cards
    for (Card card : hand) {
        if (game.isPlayable(card)) {
            if(card.getColor().equals(ColorEnum.WILD)) {
                var newColor = this.chooseNewColorToDeclare();
                hand.remove(card);
                game.playCard(card, Optional.of(newColor));

                System.out.print("cards in hand for " + this.name + " : ");
                System.out.println(hand.stream().map(c -> c.toString()).collect(Collectors.toList()));
                return;
            }
            else {
                hand.remove(card);
                game.playCard(card,null);

                System.out.print("cards in hand for " + this.name + " : ");
                System.out.println(hand.stream().map(c -> c.toString()).collect(Collectors.toList()));
                return;
            }
        }
    }
    //if CANNOT play then draw...if card matches top card on discardPile, if so play
        var cardDrawn = this.draw(game);
        System.out.println(this.name + " drew a card");
        if (game.isPlayable(cardDrawn)) {
            if(cardDrawn.getColor().equals(ColorEnum.WILD)) {
                var newColor = this.chooseNewColorToDeclare();
                hand.remove(cardDrawn);
                game.playCard(cardDrawn, Optional.of(newColor));
                System.out.println(this.name + " played " + cardDrawn + " after drawing ");
            }
            else {
                hand.remove(cardDrawn);
                game.playCard(cardDrawn,null);
                System.out.println(this.name + " played " + cardDrawn + " after drawing ");
            }
        }
        System.out.print("cards in hand for " + this.name + " : ");
        System.out.println(hand.stream().map(card -> card.toString()).collect(Collectors.toList()));


    }

    public List<Card> getHand() {
        return hand;
    }


    public Card draw(Game game) {
        var cardDrawn = game.draw();
        hand.add(cardDrawn);
        return cardDrawn;
    }

    public int handSize() {
        return hand.size();
    }

    private ColorEnum chooseNewColorToDeclare() {
        ColorEnum newColor;
        var firstNonWildCardInHand = hand.stream().filter(c -> c.getColor() != ColorEnum.WILD).findFirst().orElse(null);

        if (firstNonWildCardInHand != null) {
            newColor = firstNonWildCardInHand.getColor();
        } else {
            newColor = ColorEnum.RED;
        }
        return newColor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
