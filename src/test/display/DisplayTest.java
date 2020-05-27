package display;

import main.display.Display;
import main.game.Game;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DisplayTest {

    private Display d;

    @BeforeEach
    void setUp() {

        d = new Display();

    }

    @AfterAll
    static void handleSuccess() {

        System.out.println( "Test display successful" );

    }

    @Test
    void getGame() {

        Game g = d.getGame();
        assertNotNull( g );

    }

    @Test
    void setGame() {

        Game g = new Game();
        d.setGame( g );
        assertEquals( g, d.getGame() );

    }

}