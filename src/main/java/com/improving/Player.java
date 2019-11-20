package com.improving;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Player implements IPlayer {
    List<Card> hand;
    String name = "";

    public Player(List<Card> hand) {
        this.hand = hand;
    }

    public void takeTurn(IGame iGame) {
    //do this if not Draw 4 or Draw 2 cards
    for (Card card : hand) {
        if (iGame.isPlayable(card)) {
            if(card.getColor().equals(Colors.WILD)) {
                var newColor = this.chooseNewColorToDeclare();
                hand.remove(card);
                iGame.playCard(card, Optional.of(newColor));

                System.out.print("cards in hand for " + this.name + " : ");
                System.out.println(hand.stream().map(c -> c.toString()).collect(Collectors.toList()));
                return;
            }
            else {
                hand.remove(card);
                iGame.playCard(card,null);

                System.out.print("cards in hand for " + this.name + " : ");
                System.out.println(hand.stream().map(c -> c.toString()).collect(Collectors.toList()));
                return;

            }
        }
    }
    //if CANNOT play then draw...if card matches top card on discardPile, if so play
        var cardDrawn = this.draw(iGame);
        System.out.println(this.name + " drew a card");
        if (iGame.isPlayable(cardDrawn)) {
            if(cardDrawn.getColor().equals(Colors.WILD)) {
                var newColor = this.chooseNewColorToDeclare();
                hand.remove(cardDrawn);
                iGame.playCard(cardDrawn, Optional.of(newColor));
                System.out.println(this.name + " played " + cardDrawn + " after drawing ");
            }
            else {
                hand.remove(cardDrawn);
                iGame.playCard(cardDrawn,null);
                System.out.println(this.name + " played " + cardDrawn + " after drawing ");
            }
        }
        System.out.print("cards in hand for " + this.name + " : ");
        System.out.println(hand.stream().map(card -> card.toString()).collect(Collectors.toList()));


    }

    private List<Card> getHand() {
        return hand;
    }


    public Card draw(IGame iGame) {
        var cardDrawn = iGame.draw();
        hand.add(cardDrawn);
        return cardDrawn;
    }

    public int handSize() {
        return hand.size();
    }

    private Colors chooseNewColorToDeclare() {
        Colors newColor;
        var firstNonWildCardInHand = hand.stream().filter(c -> c.getColor() != Colors.WILD).findFirst().orElse(null);

        if (firstNonWildCardInHand != null) {
            newColor = firstNonWildCardInHand.getColor();
        } else {
            newColor = Colors.RED;
        }
        return newColor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void newHand(List<Card> cards) {
        this.hand.clear();
        this.hand.addAll(cards);
    }
}
