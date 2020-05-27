package model;

import main.model.Food;
import main.model.Pellets;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PelletsTest {

    private Pellets p;

    @BeforeEach
    void setUp() {

        p = new Pellets( 1 );

    }

    @AfterAll
    static void handleSuccess() {

        System.out.println( "Test pellets successful" );

    }

    @Test
    void getPellets() {

        Food f = p.getPellets()[0];

        assertNotNull( f );

    }

    @Test
    void setPellets() {

        Food f[] = new Food[1];

        p.setPellets( f );

        assertNotNull( p );

    }

    @Test
    void eatPellet() {

        p.eatPellet( 0 );

        assertNull( p.getPellets()[0] );

    }

}