package game;

import main.game.Game;
import main.model.Pellets;
import main.model.Player;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game g;

    @BeforeEach
    void setUp() {

        g = new Game();

    }

    @AfterAll
    static void handleSuccess() {

        System.out.println( "Test game successful" );

    }

    @Test
    void handleWinLose() {

        Player pA = new Player( 0, 0 );
        Player pB = new Player( 0, 0 );

        pA.increaseSize();

        g.setMainPlayer( pA );
        g.setBotPlayer( pB );

        g.handleWinLose();
        assertEquals( Game.STATE.WIN, Game.getState() );

    }

    @Test
    void getMainPlayer() {

        Player p = new Player();
        g.setMainPlayer( p );
        assertEquals( p, g.getMainPlayer() );

    }

    @Test
    void getBotPlayer() {

        Player p = new Player();
        g.setBotPlayer( p );
        assertEquals( p, g.getBotPlayer() );

    }

    @Test
    void getPellets() {

        Pellets p = new Pellets( 0 );
        g.setPellets( p );
        assertEquals( p, g.getPellets() );

    }

    @Test
    void getState() {

        assertEquals( Game.STATE.MENU, Game.getState() );

    }

    @Test
    void setState() {

        Game.STATE s = Game.STATE.GAME;
        Game.setState( s );
        assertEquals( s, Game.getState() );

    }

    @Test
    void getMPPosition() {

        Player p = new Player( 0, 0 );
        g.setMainPlayer( p );
        Point expected = new Point( 0, 0 );
        assertEquals( expected, g.getMPPosition() );

    }

}