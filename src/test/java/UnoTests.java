
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
            SmartPlayer player = new SmartPlayer(new ArrayList<>());

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

            SmartPlayer player = new SmartPlayer(hand);

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

            SmartPlayer player = new SmartPlayer(hand);

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
            SmartPlayer player = new SmartPlayer(new ArrayList<>());

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

            SmartPlayer player = new SmartPlayer(cards);
            List<SmartPlayer> players = new ArrayList<>();
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

        SmartPlayer player = new SmartPlayer(cards);

        List<Card> player2Cards = new ArrayList<>();
        cards.add(new Card(Faces.FIVE, Colors.BLUE));
        cards.add(new Card(Faces.SIX, Colors.BLUE));
        cards.add(new Card(Faces.FIVE, Colors.RED));
        cards.add(new Card(Faces.FOUR, Colors.YELLOW));
        cards.add(new Card(Faces.NINE, Colors.YELLOW));
        cards.add(new Card(Faces.TWO, Colors.BLUE));
        cards.add(new Card(Faces.THREE, Colors.RED));

        SmartPlayer player2 = new SmartPlayer(player2Cards);

        List<SmartPlayer> players = new ArrayList<>();
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

    @Test
    public void gameNamedColorShouldBeDeclaredAsMostCommonCard(){
        //arrange
        List<Card> cards = new ArrayList<>();
        Card wildCard = new Card(Faces.WILD, Colors.WILD);

        cards.add(wildCard);
        cards.add(new Card(Faces.SIX, Colors.RED));
        cards.add(new Card(Faces.FIVE, Colors.YELLOW));
        cards.add(new Card(Faces.FOUR, Colors.GREEN));
        cards.add(new Card(Faces.NINE, Colors.GREEN));
        cards.add(new Card(Faces.TWO, Colors.GREEN));
        cards.add(new Card(Faces.THREE, Colors.GREEN));
        SmartPlayer player = new SmartPlayer(cards);

        List<SmartPlayer> players = new ArrayList<>();
        players.add(player);
        Game game = new Game(players, 1, 1);

        //act
        Colors mostCommonColor = player.getMostCommonColor();

        //assert
        assertEquals(mostCommonColor, Colors.GREEN);
    }

    @Test
    public void findAllPlayableCardsReturnsListOfAllPlayableCards() {
        //arrange
        List<Card> cards = new ArrayList<>();
        Card card = new Card(Faces.EIGHT, Colors.YELLOW);

        cards.add(card);
        cards.add(new Card(Faces.SIX, Colors.RED));
        cards.add(new Card(Faces.FIVE, Colors.RED));
        cards.add(new Card(Faces.FOUR, Colors.GREEN));
        cards.add(new Card(Faces.EIGHT, Colors.GREEN));
        cards.add(new Card(Faces.TWO, Colors.GREEN));
        cards.add(new Card(Faces.THREE, Colors.GREEN));
        SmartPlayer player = new SmartPlayer(cards);

        List<SmartPlayer> players = new ArrayList<>();
        players.add(player);
        Game game = new Game(players, 1, 1);

        List<Card> expectedPlayableCards = new ArrayList<>();
        expectedPlayableCards.add(new Card(Faces.EIGHT, Colors.YELLOW));
        expectedPlayableCards.add(new Card(Faces.SIX, Colors.RED));
        expectedPlayableCards.add(new Card(Faces.FIVE, Colors.RED));
        expectedPlayableCards.add(new Card(Faces.EIGHT, Colors.GREEN));

        //act
        game.getDeckInfo().getDiscardPile().add(new Card(Faces.EIGHT, Colors.RED));
        var actualPlayableCards = player.findAllPlayableCards(game);

        //assert
        assertEquals(expectedPlayableCards.get(0).getColor(), actualPlayableCards.get(0).getColor());
        assertEquals(expectedPlayableCards.get(0).getFace(), actualPlayableCards.get(0).getFace());
        assertEquals(expectedPlayableCards.get(1).getColor(), actualPlayableCards.get(1).getColor());
        assertEquals(expectedPlayableCards.get(1).getFace(), actualPlayableCards.get(1).getFace());
        assertEquals(expectedPlayableCards.get(2).getColor(), actualPlayableCards.get(2).getColor());
        assertEquals(expectedPlayableCards.get(2).getFace(), actualPlayableCards.get(2).getFace());
        assertEquals(expectedPlayableCards.get(3).getColor(), actualPlayableCards.get(3).getColor());
        assertEquals(expectedPlayableCards.get(3).getFace(), actualPlayableCards.get(3).getFace());
    }

    @Test
    public void takeTurnPlaysCardWithMostCommonHandColorThatIsAlsoPlayable() {
        //arrange
        List<Card> cards = new ArrayList<>();
        Card card = new Card(Faces.EIGHT, Colors.YELLOW);

        cards.add(card);
        cards.add(new Card(Faces.SIX, Colors.RED));
        cards.add(new Card(Faces.FIVE, Colors.RED));
        cards.add(new Card(Faces.FOUR, Colors.GREEN));
        cards.add(new Card(Faces.EIGHT, Colors.GREEN));
        cards.add(new Card(Faces.TWO, Colors.GREEN));
        cards.add(new Card(Faces.THREE, Colors.GREEN));
        SmartPlayer player = new SmartPlayer(cards);

        List<SmartPlayer> players = new ArrayList<>();
        players.add(player);
        Game game = new Game(players, 1, 1);
        var discardPile = game.getDeckInfo().getDiscardPile();

        //act
        discardPile.add(new Card(Faces.EIGHT, Colors.RED));
        player.takeTurn(game);
        var topCard = discardPile.get(discardPile.size() - 1);

        //assert
        assertEquals(topCard.getColor(), Colors.GREEN);
        assertEquals(topCard.getFace(), Faces.EIGHT);
    }

    @Test
    public void playerShouldPlayDrawFourIfPossibleWhenNextPlayerLessThan3CardsLeft(){
        //arrange
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Faces.FIVE, Colors.BLUE));
        cards.add(new Card(Faces.SIX, Colors.RED));
        cards.add(new Card(Faces.DRAW_FOUR, Colors.WILD));
        cards.add(new Card(Faces.FOUR, Colors.YELLOW));
        cards.add(new Card(Faces.NINE, Colors.YELLOW));
        cards.add(new Card(Faces.TWO, Colors.BLUE));
        cards.add(new Card(Faces.THREE, Colors.RED));

        SmartPlayer player = new SmartPlayer(cards);

        List<Card> player2Cards = new ArrayList<>();
        cards.add(new Card(Faces.FIVE, Colors.BLUE));
        cards.add(new Card(Faces.SIX, Colors.BLUE));
        cards.add(new Card(Faces.FIVE, Colors.RED));
        cards.add(new Card(Faces.FOUR, Colors.YELLOW));
        cards.add(new Card(Faces.NINE, Colors.YELLOW));
        cards.add(new Card(Faces.TWO, Colors.BLUE));
        cards.add(new Card(Faces.THREE, Colors.RED));

        SmartPlayer player2 = new SmartPlayer(player2Cards);

        List<SmartPlayer> players = new ArrayList<>();
        players.add(player);
        players.add(player2);

        Game game = new Game(players, 1, 1);

        List<Card> player2NewHand = new ArrayList<>();
        player2NewHand.add(new Card(Faces.EIGHT, Colors.RED));
        player2NewHand.add(new Card(Faces.DRAW_FOUR, Colors.WILD));

        //act
        game.getDeckInfo().getDiscardPile().add(new Card(Faces.EIGHT, Colors.RED));
        player2.newHand(player2NewHand);
        player.takeTurn(game);
        int player2HandSizeAfter = game.getPlayerInfo().get(1).handSize();

        //assert
        assertEquals(player2HandSizeAfter, 6);
    }
}
