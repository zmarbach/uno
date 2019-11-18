package com.improving.bootcamp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck implements IDeck{
    private List<Card> drawPile = new ArrayList<>();
    private List<Card> discardPile = new ArrayList<>();

    //deck needs to create cards in its constructor
    public Deck() {
        for (var color : ColorEnum.values()) {
            //create all regular color cards
            if (color != ColorEnum.WILD) {
                for (var face : FaceEnum.values()) {
                    //only create all 4 colors for faces that are NOT wild or Draw 4
                    if (face != FaceEnum.WILD && face != FaceEnum.DRAW_FOUR) {
                        var card = new Card(face, color);
                        //add each of these cards twice
                        for (int i = 0; i < 2; i++) {
                            drawPile.add(card);
                        }
                    }
                }
            }

                //create all WILD color cards
            if (color == ColorEnum.WILD) {
                for (var face : FaceEnum.values()) {
                    //only create WILD color cards for WILD and DRAW FOUR face
                    if (face == FaceEnum.WILD || face == FaceEnum.DRAW_FOUR) {
                        var card = new Card(face, color);
                        //add each of these cards 4 times
                        for (int i = 0; i < 4; i++) {
                            drawPile.add(card);
                        }
                    }
                }
            }
        }
        //shuffle after creating deck
        shuffle(drawPile);
        for(var card: drawPile) {
            System.out.println("Card in drawPile: " + card.toString());
        }

    }

    public List<Card> getDrawPile() {
        return drawPile;
    }

    public int getDrawPileSize(){
        return drawPile.size();
    }

//    public void setDrawPile(List<Card> drawPile) {
//        this.drawPile = drawPile;
//    }

    public ArrayList<Card> getDiscardPile() {
        return (ArrayList<Card>) discardPile;    }

    private void setDiscardPile(List<Card> discardPile) {
        this.discardPile = discardPile;
    }

    //this gets called if deck runs out mid game and needs to be reset with cards from discard pile
    private void shuffleMidGame() {
        shuffle(discardPile);

        this.getDrawPile().addAll(discardPile);
        var topCardFromDrawPile = this.getDrawPile().get(this.getDrawPile().size() - 1);

        var newDiscardPile = new ArrayList<Card>();
        newDiscardPile.add(topCardFromDrawPile);
        this.setDiscardPile(newDiscardPile);

        //put
    }

    public List<Card> shuffle (List<Card> cardsToShuffle){
        Collections.shuffle(cardsToShuffle);
        return cardsToShuffle;

    }

    public Card draw() {
        //if deck is empty, shuffle the discard pile and make it the new deck
        if(drawPile.size() == 0) {
            shuffleMidGame();
        }
        Random random = new Random();
        var ranIndex = random.nextInt(drawPile.size());
        var card = drawPile.get(ranIndex);
        drawPile.remove(card);
        return card;
    }




}
