package model;

import main.model.Food;
import main.model.Pellets;
import main.model.Player;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Ellipse2D;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player p;

    @BeforeEach
    void setUp() {

        p = new Player();

    }

    @AfterAll
    static void handleSuccess() {

        System.out.println( "Test player successful" );

    }

    @Test
    void getPlayer() {

        Player player = new Player();
        player.getPlayer().x = p.getPlayer().x;
        player.getPlayer().y = p.getPlayer().y;

        assertEquals( player.getPlayer(), p.getPlayer() );

    }

    @Test
    void setPlayer() {

        Player player = new Player();
        Ellipse2D.Double e = new Ellipse2D.Double( 0, 0, 0, 0 );

        player.setPlayer( e );
        p.setPlayer( e );

        assertEquals( player.getPlayer(), p.getPlayer() );

    }

    @Test
    void increaseSize() {

        p.increaseSize();
        assertEquals(Player.PLAYER_DIAMETER + Player.DELTA_SIZE, p.getPlayer().width );

    }

    @Test
    void decreaseSize() {

        p.decreaseSize();
        assertEquals( Player.PLAYER_DIAMETER - Player.DELTA_SIZE,  p.getPlayer().width );

    }

    @Test
    void getX() {

        p = new Player( 0, 0 );
        assertEquals( 0, p.getX() );

    }

    @Test
    void setX() {

        p.setX( 0.0 );
        assertEquals(0.0, p.getX() );

    }

    @Test
    void getY() {

        p = new Player( 0, 0 );
        assertEquals(0, p.getY() );

    }

    @Test
    void setY() {

        p.setY( 0.0 );
        assertEquals(0.0, p.getY());
    }

    @Test
    void overlays() {

        Player player = new Player();
        player.increaseSize();

        p.setX( player.getX() );
        p.setY( player.getY() );

        assertTrue( player.overlays( p ) );

    }

    @Test
    void moveManually() {

        p = new Player( 0, 0 );
        Point point = new Point( 30, 30 );

        p.moveManually( point );

        double angle = Math.atan2( 30, 30 );

        Point position = new Point( (int)p.getX(), (int)p.getY() );
        Point expected = new Point( (int)( Player.INIT_VELOCITY * Math.cos(angle) ), (int)( Player.INIT_VELOCITY * Math.sin(angle) ) );

        assertEquals( expected, position );

    }

    @Test
    void moveAutomatically() {

        p               = new Player( 0, 0 );
        Player player   = new Player( 30, 30 );
        Pellets pellets = new Pellets( 0 );

        p.increaseSize();

        p.moveAutomatically( player, pellets );

        double angle = Math.atan2( player.getMidX() - p.getMidX(), player.getMidY() - p.getMidY() );

        Point position = new Point( (int)p.getX(), (int)p.getY() );
        Point expected = new Point( (int)( Player.INIT_VELOCITY * Math.cos(angle) ), (int)( Player.INIT_VELOCITY * Math.sin(angle) ) );

        assertEquals( expected, position );

    }

    @Test
    void getRadius() {

        p.increaseSize();
        assertEquals( ( Player.PLAYER_DIAMETER + Player.DELTA_SIZE ) / 2, p.getRadius() );

    }

    @Test
    void handleEaten() {

        Pellets pellets = new Pellets( 1 );
        Food food[]     = { new Food( 0, 0 ) };
        pellets.setPellets( food );

        p = new Player( 0, 0 );

        p.handleEaten( pellets );
        assertEquals( ( Player.PLAYER_DIAMETER + Player.DELTA_SIZE ) / 2 , p.getRadius() );

    }

    @Test
    void getVelocity() {

        p.increaseSize();
        assertEquals( Player.INIT_VELOCITY - Player.DELTA_VELOCITY, p.getVelocity() );

    }

}