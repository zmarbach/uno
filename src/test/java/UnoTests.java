
import com.improving.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class UnoTests {

        @Test
        public void deckShouldCreate112CardsWhenConstructed(){
            //arrange
            Deck deck = new Deck();

            //act
            Integer deckSize = deck.getDrawPile().size();

            //assert
            assertEquals(deckSize, 112);
        }

//        @Test
//        public void startingHandShouldHave7Cards(){
//            //arrange
//            Game game = new Game(2);
//
//            //act
//            int player1HandSize = game.getPlayerHandSize(0);
//            int player2HandSize = game.getPlayerHandSize(1);
//
//            //assert
//            assertEquals(player1HandSize, 7);
//            assertEquals(player1HandSize, 7);
//        }

        @Test
        public void playerDrawCardShouldRemoveCardFromDrawPile(){
            //arrange
            Game game = new Game(3);
            Player player = new Player(new ArrayList<>());

            //act
            Integer drawPileBeforeDraw = game.getDrawPileSize();
            System.out.println(drawPileBeforeDraw);
            player.draw(game);
            Integer drawPileAfterDraw = game.getDrawPileSize();
            System.out.println(drawPileAfterDraw);

            //assert
            assertTrue(drawPileBeforeDraw == drawPileAfterDraw + 1);
        }




//        @Test
//        public void playingACardShouldAddCardToDiscardPile(){
//            //arrange
//            Deck deck = new Deck();
//            var discardPile = deck.getDiscardPile();
//            Player player = new Player();
//            player.createHand(deck);
//            Hand hand = player.getHand();
//
//            //act
//            Integer discardPileSizeBeforePlay = discardPile.size();
//            hand.playCard(hand.getCards().get(1), deck);
//            Integer discardPileSizeAfterPlay = discardPile.size();
//
//            //assert
//            assertTrue(discardPileSizeAfterPlay == discardPileSizeBeforePlay + 1);
//        }

//        @Test
//        public void drawTopCardShouldAddTopCardFromDeckToHand(){
//            //arrange
//            Game game = new Game();
//            Player player = new Player();
//
//            //act
//            var cardAtTopOfDeck= game.getDeck().getDrawPile().get(game.getDeck().getDrawPile().size() - 1);
//            player.draw(game);
//            var cardAddedToHand = hand.getCards().get(hand.getCards().size() - 1);
//
//            //assert
//            assertEquals(cardAtTopOfDeck.getColor(), cardAddedToHand.getColor());
//            assertEquals(cardAtTopOfDeck.getFace(), cardAddedToHand.getFace());
//        }

        @Test
        public void drawTopCardShouldRemovesCardFromDeck(){
            //arrange
            Game game = new Game(3);
            Deck deck = new Deck();
            Player player = new Player(new ArrayList<>());

            //act
            var sizeOfDeckBeforeTopDraw = game.getDrawPileSize();
            System.out.println(sizeOfDeckBeforeTopDraw);
            player.draw(game);
            var sizeOfDeckAfterTopDraw = game.getDrawPileSize();
            System.out.println(sizeOfDeckAfterTopDraw);

            //assert
            assertTrue(sizeOfDeckBeforeTopDraw == sizeOfDeckAfterTopDraw + 1);
        }


//        @Test
//        public void ifDrawTopCardIsCalledAndDeckIsEmptyThenDeckShouldShuffleAndThenRunDrawTopCardVersion2(){
//            //arrange
//            Game game = new Game();
//            Deck deck = new Deck();
//            Player player = new Player();
//            player.createHand(deck);
//            Hand hand = player.getHand();
//
//
//            //act
//            //deal all cards in Deck so it hits 0
//            for(int i=0; i<105; i++){
//                player.draw(game);
//                //play each card to add cards to discard pile
//                hand.playCard(hand.getCards().get(0), deck);
//            }
//
//            //then draw top card with deck at 0 to make sure it shuffles and then runs draw top card
//            player.draw(game);
//
//            //check hand size after drawing top card
//            var handSizeAfterDrawTopCard = hand.getCards().size();
//
//            //assert
//            assertEquals(handSizeAfterDrawTopCard, 8);
//        }

//        @Test
//        public void ifDrawTopCardIsCalledAndDeckIsEmptyThenDiscardPileShouldBeShuffled(){
//            //arrange
//            Game game = new Game();
//            Deck deck = new Deck();
//            Player player = new Player();
//            player.createHand(deck);
//            Hand hand = player.getHand();
//            var discardPile = deck.getDiscardPile();
//
//            //act
//            //deal all cards in Deck so it hits 0
//            for(int i=0; i<105; i++){
//                player.draw(game);
//                //play each card to add cards to discard pile
//                hand.playCard(hand.getCards().get(0), deck);
//            }
//
//            //get random cards in discardPile before drawTopCard to get pre-shuffled version
//            Card ranCard1 = discardPile.get(6);
//            Card ranCard2 = discardPile.get(80);
//            Card ranCard3 = discardPile.get(99);
//
//            //then draw top card with deck at 0 to make sure it shuffles and then runs draw top card
//            player.draw(game);
//
//            //get random cards in discardPile before drawTopCard to get pre-shuffled version
//            Card ranCard1afterShuffle = discardPile.get(7);
//            Card ranCard2afterShuffle = discardPile.get(80);
//            Card ranCard3afterShuffle = discardPile.get(99);
//
//
//            //assert
//            assertNotEquals(ranCard1, ranCard1afterShuffle);
//            assertNotEquals(ranCard2, ranCard2afterShuffle);
//            assertNotEquals(ranCard3, ranCard3afterShuffle);
//        }

        @Test
        public void deckIsShuffledAfterBeingCreated(){
            //arrange
            Deck deck = new Deck();

            //act
            var firstCardInDeck = deck.getDrawPile().get(0);
            var lastCardInDeck = deck.getDrawPile().get(deck.getDrawPile().size() - 1);

            var origFirstCardInDeck = new Card(Faces.ZERO, Colors.RED);
            var origLastCardInDeck = new Card(Faces.WILD, Colors.WILD);

            //assert
            assertNotEquals(origFirstCardInDeck, firstCardInDeck);
            assertNotEquals(origLastCardInDeck, lastCardInDeck);
        }

//        @Test
//        public void ifTopCardIsDraw4ThenPlayerShouldDraw4Cards() throws GameOverException {
//            //arrange
//            Game game = new Game();
//            Player player = new Player();
//            List<Card> discardPile = game.getDeck().getDiscardPile();
//            discardPile.add(new Card(FaceEnum.DRAW_FOUR, ColorEnum.WILD));
//
//            var playerHandBeforeDrawFour = player.getHand().getCards().size();
//            System.out.println(playerHandBeforeDrawFour);
//
//            //act
//
//            player.takeTurn(game);
//            var playerHandAfterDrawFour = player.getHand().getCards().size();
//            System.out.println(playerHandAfterDrawFour);
//
//            //assert
//            assertTrue(playerHandBeforeDrawFour == playerHandAfterDrawFour - 4);
//
//        }

//        @Test
//        public void ifTopCardIsDraw2ThenPlayerShouldDraw2Cards() throws GameOverException {
//            //arrange
//            Game game = new Game();
//            Player player = new Player();
//            List<Card> discardPile = game.getDeck().getDiscardPile();
//            discardPile.add(new Card(FaceEnum.DRAW_TWO, ColorEnum.WILD, false));
//
//            var playerHandBeforeDrawTwo = player.getHand().getCards().size();
//            System.out.println(playerHandBeforeDrawTwo);
//
//            //act
//
//            player.takeTurn(game);
//            var playerHandAfterDrawTwo = player.getHand().getCards().size();
//            System.out.println(playerHandAfterDrawTwo);
//
//            //assert
//            assertTrue(playerHandBeforeDrawTwo == playerHandAfterDrawTwo - 2);
//
//        }

//        @Test
//        public void ifTopCardIsRedAndCardInHandIsRedThenPlayerHandWillDecrByOneAfterPlay() throws GameOverException {
//            //arrange
//            Game game = new Game();
//            Player player = new Player();
//
//            List<Card> cards = new ArrayList<>();
//            cards.add(new Card(FaceEnum.FIVE, ColorEnum.BLUE, false));
//            cards.add(new Card(FaceEnum.SIX, ColorEnum.BLUE, false));
//            cards.add(new Card(FaceEnum.FIVE, ColorEnum.RED, false));
//            cards.add(new Card(FaceEnum.FOUR, ColorEnum.YELLOW, false));
//            cards.add(new Card(FaceEnum.NINE, ColorEnum.YELLOW, false));
//            cards.add(new Card(FaceEnum.TWO, ColorEnum.BLUE, false));
//            cards.add(new Card(FaceEnum.THREE, ColorEnum.RED, false));
//
//            hand.setCards(cards);
//            player.setHand(hand);
//
//            List<Card> discardPile = game.getDeck().getDiscardPile();
//            discardPile.add(new Card(FaceEnum.EIGHT, ColorEnum.RED, false));
//
//            var playerHandBeforePlay = player.getHand().getCards().size();
//
//            //act
//            player.takeTurn(game);
//            var playerHandAfterPlay = player.getHand().getCards().size();
//
//            //assert
//            assertTrue(playerHandBeforePlay == playerHandAfterPlay + 1);
//
//        }

//    @Test
//    public void draw2ShouldMakeNextPlayerDraw2() {
//        //arrange
//        Game game = new Game(2);
//        Deck deck = new Deck();
//
//        List<Card> player1StartingHand = new ArrayList<>();
//        player1StartingHand.add(new Card(FaceEnum.DRAW_FOUR, ColorEnum.WILD));
//        player1StartingHand.add(new Card(FaceEnum.THREE, ColorEnum.YELLOW));
//        player1StartingHand.add(new Card(FaceEnum.FOUR, ColorEnum.GREEN));
//        player1StartingHand.add(new Card(FaceEnum.FIVE, ColorEnum.BLUE));
//        player1StartingHand.add(new Card(FaceEnum.NINE, ColorEnum.RED));
//        player1StartingHand.add(new Card(FaceEnum.DRAW_TWO, ColorEnum.WILD));
//        player1StartingHand.add(new Card(FaceEnum.WILD, ColorEnum.WILD));
//        Player player1 = new Player(player1StartingHand);
//
//        List<Card> player2StartingHand = new ArrayList<>();
//        player2StartingHand.add(new Card(FaceEnum.DRAW_FOUR, ColorEnum.WILD));
//        player2StartingHand.add(new Card(FaceEnum.THREE, ColorEnum.YELLOW));
//        player2StartingHand.add(new Card(FaceEnum.FOUR, ColorEnum.GREEN));
//        player2StartingHand.add(new Card(FaceEnum.FIVE, ColorEnum.BLUE));
//        player2StartingHand.add(new Card(FaceEnum.NINE, ColorEnum.RED));
//        player2StartingHand.add(new Card(FaceEnum.DRAW_TWO, ColorEnum.WILD));
//        player2StartingHand.add(new Card(FaceEnum.WILD, ColorEnum.WILD));
//        Player player2 = new Player(player2StartingHand);
//
//        game.getPlayers().add(player1);
//        game.getPlayers().add(player2);
//
//        //act
//        game.playCard(new Card(FaceEnum.DRAW_TWO, ColorEnum.RED), null);
//        int player2HandSizeAfterDraw2 = player2.handSize();
//
//        //assert
//        assertTrue(player2StartingHand.size() == player2HandSizeAfterDraw2 - 2);
//    }
}
