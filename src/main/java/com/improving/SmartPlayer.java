package com.improving;

import java.util.*;
import java.util.stream.Collectors;

public class SmartPlayer implements IPlayer {
    List<Card> hand;
    String name = "Zach";

    public SmartPlayer(List<Card> hand) {
        this.hand = hand;
    }

    public void takeTurn(IGame iGame) {
        //TODO: make player smarter by having them play playable card WITH MOST COMMON COLOR
        var mostCommonColor = getMostCommonColor();
        List<Card> cardsWithMostCommonColor = hand.stream().filter(card -> card.getColor().equals(mostCommonColor)).collect(Collectors.toList());
        List<Card> otherCards = hand.stream().filter(card -> card.getColor() != mostCommonColor).collect(Collectors.toList());

        //if nextPlayer handsize < 3 play drawFour, drawTwo, skip, reverse
        if (iGame.getNextPlayer().handSize() < 3) {
            screwOtherPlayer(iGame);
            return;
        }

        for (Card card : cardsWithMostCommonColor) {
            if (iGame.isPlayable(card)) {
                if (card.getColor().equals(Colors.WILD)) {
                    var newColor = chooseNewColorToDeclare();
                    hand.remove(card);
                    iGame.playCard(card, Optional.of(newColor));

                    System.out.print("cards in hand for " + this.name + " : ");
                    System.out.println(hand.stream().map(c -> c.toString()).collect(Collectors.toList()));
                    return;
                } else {
                    hand.remove(card);
                    iGame.playCard(card, null);

                    System.out.print("cards in hand for " + this.name + " : ");
                    System.out.println(hand.stream().map(c -> c.toString()).collect(Collectors.toList()));
                    return;
                }
            }
        }

        for (Card card : otherCards) {
            if (iGame.isPlayable(card)) {
                if (card.getColor().equals(Colors.WILD)) {
                    var newColor = this.chooseNewColorToDeclare();
                    hand.remove(card);
                    iGame.playCard(card, Optional.of(newColor));

                    System.out.print("cards in hand for " + this.name + " : ");
                    System.out.println(hand.stream().map(c -> c.toString()).collect(Collectors.toList()));
                    return;
                } else {
                    hand.remove(card);
                    iGame.playCard(card, null);

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
            if (cardDrawn.getColor().equals(Colors.WILD)) {
                var newColor = this.chooseNewColorToDeclare();
                hand.remove(cardDrawn);
                iGame.playCard(cardDrawn, Optional.of(newColor));
                System.out.println(this.name + " played " + cardDrawn + " after drawing ");
            } else {
                hand.remove(cardDrawn);
                iGame.playCard(cardDrawn, null);
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
        var mostCommonColor = getMostCommonColor();

        if (mostCommonColor != null) {
            newColor = mostCommonColor;
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

    public Colors getMostCommonColor() {
        Map<Colors, Integer> colorsMap = new HashMap<>();
        for (var card : hand) {
            if (colorsMap.containsKey(card.getColor())) {
                colorsMap.put(card.getColor(), colorsMap.get(card.getColor()) + 1);
            } else {
                colorsMap.put(card.getColor(), 1);
            }
        }
        Integer maxValue = colorsMap.entrySet().stream().map(color -> color.getValue()).max(Integer::compare).orElse(0);
        Colors mostCommonColor = colorsMap.entrySet().stream().filter(color -> color.getValue().equals(maxValue)).findFirst().map(pair -> pair.getKey()).orElse(Colors.RED);
        return mostCommonColor;
    }

    public List<Card> findAllPlayableCards(IGame iGame) {
        List<Card> playableCards = new ArrayList<>();
        for (var card : hand) {
            if (iGame.isPlayable(card)) {
                playableCards.add(card);
            }
        }
        return playableCards;
    }

    @Override
    public void newHand(List<Card> cards) {
        this.hand.clear();
        this.hand.addAll(cards);
    }

    public void screwOtherPlayer(IGame iGame) {
        Card draw4Card = hand.stream().filter(card -> card.getFace().equals(Faces.DRAW_FOUR)).findFirst().orElse(null);
        Card draw2Card = hand.stream().filter(card -> card.getFace().equals(Faces.DRAW_TWO)).findFirst().orElse(null);
        Card skipCard = hand.stream().filter(card -> card.getFace().equals(Faces.SKIP)).findFirst().orElse(null);
        Card reverseCard = hand.stream().filter(card -> card.getFace().equals(Faces.REVERSE)).findFirst().orElse(null);

        if (draw4Card != null) {
            var newColor = chooseNewColorToDeclare();
            hand.remove(draw4Card);
            iGame.playCard(draw4Card, Optional.of(newColor));

            System.out.print("cards in hand for " + this.name + " : ");
            System.out.println(hand.stream().map(c -> c.toString()).collect(Collectors.toList()));
            return;
        } else if (draw2Card != null) {
            var newColor = chooseNewColorToDeclare();
            hand.remove(draw2Card);
            iGame.playCard(draw2Card, Optional.of(newColor));

            System.out.print("cards in hand for " + this.name + " : ");
            System.out.println(hand.stream().map(c -> c.toString()).collect(Collectors.toList()));
            return;
        } else if (skipCard != null) {
            hand.remove(skipCard);
            iGame.playCard(skipCard, null);

            System.out.print("cards in hand for " + this.name + " : ");
            System.out.println(hand.stream().map(c -> c.toString()).collect(Collectors.toList()));
            return;
        } else if (reverseCard != null) {
            hand.remove(reverseCard);
            iGame.playCard(reverseCard, null);

            System.out.print("cards in hand for " + this.name + " : ");
            System.out.println(hand.stream().map(c -> c.toString()).collect(Collectors.toList()));
            return;
        }
    }
}
