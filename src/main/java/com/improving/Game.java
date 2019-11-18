package com.improving;

import java.util.*;
import java.util.stream.Collectors;

public class Game implements IGame{
    Deck deck = new Deck();
    List<Player> players = new ArrayList<>();
    Optional<Colors> namedColor;
    int numOfPlayers;
    int turnEngine;
    int turnDirection;
    int turnCount;
    Player currentPlayer;

    public Game(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;

        //create hands for each player and add them to list of players
        for (int i = 0; i < numOfPlayers; i++) {
            var playerHand = createHand(deck, new ArrayList<>());
            System.out.println("starting hand: ");
            System.out.println(playerHand.stream().map(card -> card.toString()).collect(Collectors.toList()));

            Player player = new Player(playerHand);
            players.add(player);

            System.out.print("Enter player name >> ");
            Scanner scanner = new Scanner(System.in);
            var name = scanner.nextLine();
            player.setName(name);
        }
    }

    public void play() {
        //get top card from deck and add it as first card in discard pile
        var startingDiscardPileCard = deck.getDrawPile().get(deck.getDrawPile().size() - 1);
        deck.getDrawPile().remove(startingDiscardPileCard);
        deck.getDiscardPile().add(startingDiscardPileCard);
        System.out.println("-----STARTING CARD----- " + startingDiscardPileCard);

        turnCount = 0;
        turnEngine = 0;
        turnDirection = 1;

        //if first card if Wild then assign color Red to namedColor to start
        if(startingDiscardPileCard.getColor().equals(Colors.WILD)) {
            namedColor = Optional.of(Colors.RED);
            System.out.println("starting color was set to " + namedColor + " because first card was WILD color");
        }

        //establish first player in case first card requires action
        currentPlayer = players.get(0);
        if(isSpecialCard(startingDiscardPileCard)){
            performSpecialAction(startingDiscardPileCard);
        }

        boolean gameInProgress = true;
        while (gameInProgress) {
            turnCount++;
            int nextPlayerIndex = getSafeModulo();
            currentPlayer = players.get(nextPlayerIndex);
            currentPlayer.takeTurn(this);
            if (currentPlayer.handSize() == 0) {
                System.out.println(currentPlayer.getName() + " wins!");
                gameInProgress = false;
            }

            for(int i=0 ; i<numOfPlayers; i++) {
                System.out.println(players.get(i).getName() + " hand size after turn " + turnCount + " : " + players.get(i).handSize());
            }

            turnEngine += turnDirection;
        }
    }

    public void playCard(Card card, Optional<Colors> newColor){
        System.out.println(currentPlayer.getName() + " played " + card.toString());
        var discardPile = this.getDeck().getDiscardPile();
        discardPile.add(card);

        if(isSpecialCard(card)) {
            performSpecialAction(card);
        }

        if ((card.getColor() == Colors.WILD) && (newColor.isPresent() == true)) {
            setNamedColor(Optional.of(newColor).orElse(null));
            if(namedColor != null){
                System.out.println("New Color set to...." + newColor.toString());
            }
        }

        //TODO: use equals() R || Y || G || B....maybe create separate method called isValidColor()
        //TODO: need to catch the BAD INPUT
            //color declared must be null if Face is NOT WILD or DRAW FOUR...else throw "YouCheatedException"
            //color declared must be valid color (R,B,G,Y,W)
    }

    public boolean isPlayable(Card card) {
        if (namedColor != null) {
            return (card.getColor().equals(Colors.WILD) || (card.getColor().equals(namedColor.get())));
        } else {
            Card topCardOnDiscardPile = deck.getDiscardPile().get(deck.getDiscardPile().size() - 1);
            return (card.getColor().equals(Colors.WILD)
                    || card.getColor().equals(topCardOnDiscardPile.getColor())
                    || card.getFace().equals(topCardOnDiscardPile.getFace()));
        }
    }

    public Card draw() {
        return deck.draw();
    }

    private Deck getDeck() {
        return deck;
    }

    private List<Card> createHand(Deck deck, List<Card> cards) {
        //deal all 7 cards when creating a hand
        for (int i = 0; i < 7; i++) {
            Random r = new Random();
            var ranIndex = r.nextInt(deck.getDrawPile().size());
            var card = deck.getDrawPile().get(ranIndex);
            deck.getDrawPile().remove(card);
            cards.add(card);
        }
        return cards;
    }

    private int getSafeModulo() {
        return (turnEngine < 0) ? (turnEngine += numOfPlayers) % numOfPlayers : turnEngine % numOfPlayers;
    }

    private void performSpecialAction(Card card) {
        int playerPosition = players.indexOf(currentPlayer);
        Player impactedPlayer;
        //DRAW TWO
        if (card.getFace().equals(Faces.DRAW_TWO)) {
            if(turnCount == 0) {
                currentPlayer.draw(this);
                currentPlayer.draw(this);
            }
            if (turnDirection == 1 && turnCount != 0) {
                if (playerPosition == players.size() - 1) {
                    impactedPlayer = players.get(0);
                } else {
                    impactedPlayer = players.get(playerPosition + 1);
                }
                impactedPlayer.draw(this);
                impactedPlayer.draw(this);
                System.out.println(impactedPlayer.getName() + " drew 2");
            }
            if (turnDirection == -1) {
                if (playerPosition == 0) {
                    impactedPlayer = players.get(players.size() - 1);
                } else {
                    impactedPlayer = players.get(playerPosition - 1);
                }
                impactedPlayer.draw(this);
                impactedPlayer.draw(this);
                System.out.println(impactedPlayer.getName() + " drew 2");
            }
            turnEngine += turnDirection;
        }
        //DRAW FOUR
        else if (card.getFace().equals(Faces.DRAW_FOUR)) {
            if(turnCount == 0) {
                currentPlayer.draw(this);
                currentPlayer.draw(this);
                currentPlayer.draw(this);
                currentPlayer.draw(this);
            }
            if (turnDirection == 1 && turnCount != 0) {
                if (playerPosition == players.size() - 1) {
                    impactedPlayer = players.get(0);
                } else {
                    impactedPlayer = players.get(playerPosition + 1);
                }
                impactedPlayer.draw(this);
                impactedPlayer.draw(this);
                impactedPlayer.draw(this);
                impactedPlayer.draw(this);
                System.out.println(impactedPlayer.getName() + " drew 4");
            }
            if (turnDirection == -1) {
                if (playerPosition == 0) {
                    impactedPlayer = players.get(players.size() - 1);
                } else {
                    impactedPlayer = players.get(playerPosition - 1);
                }
                impactedPlayer.draw(this);
                impactedPlayer.draw(this);
                impactedPlayer.draw(this);
                impactedPlayer.draw(this);
                System.out.println(impactedPlayer.getName() + " drew 4");
            }
            turnEngine += turnDirection;
        }
        //SKIP
        else if (card.getFace().equals(Faces.SKIP)) {
            System.out.println("SKIPPPPPPPPP!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            turnEngine += turnDirection;
        }
        //REVERSE
        else if (card.getFace().equals(Faces.REVERSE)) {
            System.out.println("REVERSE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            //if turn direction is 1, this will change it to -1 (and vice versa)
            turnDirection *= -1;

            //if first turn then need to switch player turns (normally does this after player turn in the while loop)
            if(turnCount == 0) {
                turnEngine += turnDirection;
            }
        }
    }

    private boolean isSpecialCard(Card card) {
        return (card.getFace().equals(Faces.SKIP) ||
                card.getFace().equals(Faces.REVERSE) ||
                card.getFace().equals(Faces.DRAW_TWO) ||
                card.getFace().equals(Faces.DRAW_FOUR));
    }

    public int getDiscardPileSize() {
        return deck.getDiscardPile().size();
    }

    public int getDrawPileSize() {
        return deck.getDrawPile().size();
    }

    public void setNamedColor(Optional<Colors> namedColor) {
        this.namedColor = namedColor;
    }

    //for testing
    public int getPlayerHandSize(int playerIndex){
        return players.get(playerIndex).getHand().size();
    }

    @Override
    public List<IPlayerInfo> getPlayerInfo() {
        return new ArrayList<>(players);
    }

    @Override
    public IDeck getDeckInfo() {
        return deck;
    }

    @Override
    public IPlayer getNextPlayer() {
        //return first player if current player is last in list
        if(players.indexOf(currentPlayer) == players.size() - 1){
            return players.get(0);
        }
        //otherwise return player at next index
        else {
            return players.get(players.indexOf(currentPlayer) + 1);
        }
    }

    @Override
    public IPlayer getPreviousPlayer() {
        //return last player in list if current player is first in list
        if(players.indexOf(currentPlayer) == 0){
            return players.get(players.size() - 1);
        }
        //otherwise return player at previous index
        else {
            return players.get(players.indexOf(currentPlayer) - 1);
        }
    }

    @Override
    public IPlayer getNextNextPlayer() {
        //return first player if current player is last in list
        if(players.indexOf(currentPlayer) == players.size() - 2){
            return players.get(0);
        }
        //otherwise return player at next next index
        else {
            return players.get(players.indexOf(currentPlayer) + 2);
        }
    }
}
