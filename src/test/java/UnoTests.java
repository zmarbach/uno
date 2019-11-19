
import com.improving.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        @Test
        public void startingHandShouldHave7Cards(){
            //arrange
            Game game = new Game(2);

            //act
            int player1HandSize = game.getPlayerInfo().get(0).handSize();
            int player2HandSize = game.getPlayerInfo().get(1).handSize();

            //assert
            assertEquals(player1HandSize, 7);
            assertEquals(player1HandSize, 7);
        }

        @Test
        public void playerDrawCardShouldRemoveCardFromDrawPile(){
            //arrange
            Game game = new Game(3);
            Player player = new Player(new ArrayList<>());

            //act
            Integer drawPileBeforeDraw = game.getDeckInfo().getDrawPileSize();
            System.out.println(drawPileBeforeDraw);
            player.draw(game);
            Integer drawPileAfterDraw = game.getDeckInfo().getDrawPileSize();
            System.out.println(drawPileAfterDraw);

            //assert
            assertTrue(drawPileBeforeDraw == drawPileAfterDraw + 1);
        }

        @Test
        public void playingACardShouldAddCardToDiscardPile(){
            //arrange
            Game game = new Game(1);
            List<Card> hand = new ArrayList<>();
            var card = new Card(Faces.DRAW_FOUR, Colors.WILD);
            hand.add(card);
            hand.add(new Card(Faces.DRAW_FOUR, Colors.WILD));
            hand.add(new Card(Faces.DRAW_FOUR, Colors.WILD));
            hand.add(new Card(Faces.DRAW_FOUR, Colors.WILD));
            hand.add(new Card(Faces.DRAW_FOUR, Colors.WILD));
            hand.add(new Card(Faces.DRAW_FOUR, Colors.WILD));
            hand.add(new Card(Faces.DRAW_FOUR, Colors.WILD));

            Player player = new Player(hand);

            //act
            Integer discardPileSizeBeforePlay = game.getDeckInfo().getDiscardPile().size();
            System.out.println(discardPileSizeBeforePlay);

            game.playCard(card, Optional.of(Colors.RED));

            Integer discardPileSizeAfterPlay = game.getDeckInfo().getDiscardPile().size();
            System.out.println(discardPileSizeAfterPlay);

            //assert
            assertTrue(discardPileSizeAfterPlay == discardPileSizeBeforePlay + 1);
        }

        @Test
        public void drawShouldAddCardToPlayerHand(){
            //arrange
            Game game = new Game(1);
            List<Card> hand = new ArrayList<>();
            var card = new Card(Faces.DRAW_FOUR, Colors.WILD);
            hand.add(card);
            hand.add(new Card(Faces.DRAW_FOUR, Colors.WILD));
            hand.add(new Card(Faces.DRAW_FOUR, Colors.WILD));
            hand.add(new Card(Faces.DRAW_FOUR, Colors.WILD));
            hand.add(new Card(Faces.DRAW_FOUR, Colors.WILD));
            hand.add(new Card(Faces.DRAW_FOUR, Colors.WILD));
            hand.add(new Card(Faces.DRAW_FOUR, Colors.WILD));

            Player player = new Player(hand);

            //act
            Integer handSizeBeforeDraw = player.handSize();
            player.draw(game);
            Integer handSizeAfterDraw = player.handSize();

            //assert
            assertTrue(handSizeAfterDraw - 1 == handSizeBeforeDraw);
        }

        @Test
        public void drawTopCardShouldRemovesCardFromDeck(){
            //arrange
            Game game = new Game(3);
            Deck deck = new Deck();
            Player player = new Player(new ArrayList<>());

            //act
            var sizeOfDeckBeforeTopDraw = game.getDeckInfo().getDrawPileSize();
            System.out.println(sizeOfDeckBeforeTopDraw);
            player.draw(game);
            var sizeOfDeckAfterTopDraw = game.getDeckInfo().getDrawPileSize();
            System.out.println(sizeOfDeckAfterTopDraw);

            //assert
            assertTrue(sizeOfDeckBeforeTopDraw == sizeOfDeckAfterTopDraw + 1);
        }

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

        @Test
        public void ifTopCardIsRedAndCardInHandIsRedThenPlayerHandWillDecrByOneAfterPlay() {
            //arrange
            List<Card> cards = new ArrayList<>();
            cards.add(new Card(Faces.FIVE, Colors.BLUE));
            cards.add(new Card(Faces.SIX, Colors.BLUE));
            cards.add(new Card(Faces.FIVE, Colors.RED));
            cards.add(new Card(Faces.FOUR, Colors.YELLOW));
            cards.add(new Card(Faces.NINE, Colors.YELLOW));
            cards.add(new Card(Faces.TWO, Colors.BLUE));
            cards.add(new Card(Faces.THREE, Colors.RED));

            Player player = new Player(cards);
            List<Player> players = new ArrayList<>();
            players.add(player);

            Game game = new Game(players, 1, 1);

            List<Card> discardPile = game.getDeckInfo().getDiscardPile();
            discardPile.add(new Card(Faces.EIGHT, Colors.RED));

            var player1HandBefore = game.getPlayerInfo().get(0).handSize();

            //act
            player.takeTurn(game);
            var player1HandAfter = game.getPlayerInfo().get(0).handSize();

            //assert
            assertTrue(player1HandBefore == player1HandAfter + 1);
        }

    @Test
    public void draw2ShouldMakeNextPlayerDraw2() {
        //arrange
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Faces.FIVE, Colors.BLUE));
        cards.add(new Card(Faces.SIX, Colors.BLUE));
        cards.add(new Card(Faces.FIVE, Colors.RED));
        cards.add(new Card(Faces.FOUR, Colors.YELLOW));
        cards.add(new Card(Faces.NINE, Colors.YELLOW));
        cards.add(new Card(Faces.TWO, Colors.BLUE));
        cards.add(new Card(Faces.THREE, Colors.RED));

        Player player = new Player(cards);

        List<Card> player2Cards = new ArrayList<>();
        cards.add(new Card(Faces.FIVE, Colors.BLUE));
        cards.add(new Card(Faces.SIX, Colors.BLUE));
        cards.add(new Card(Faces.FIVE, Colors.RED));
        cards.add(new Card(Faces.FOUR, Colors.YELLOW));
        cards.add(new Card(Faces.NINE, Colors.YELLOW));
        cards.add(new Card(Faces.TWO, Colors.BLUE));
        cards.add(new Card(Faces.THREE, Colors.RED));

        Player player2 = new Player(player2Cards);

        List<Player> players = new ArrayList<>();
        players.add(player);
        players.add(player2);

        Game game = new Game(players, 1, 1);
        Integer player2HandSizeBefore = game.getPlayerInfo().get(1).handSize();
        System.out.println(player2HandSizeBefore);

        //act
        game.playCard(new Card(Faces.DRAW_TWO, Colors.RED), null);
        Integer player2HandSizeAfter = game.getPlayerInfo().get(1).handSize();
        System.out.println(player2HandSizeAfter);

        //assert
        assertTrue(player2HandSizeBefore  == player2HandSizeAfter - 2);
    }
}
