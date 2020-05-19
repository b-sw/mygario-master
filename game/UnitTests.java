package game;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.*;
import assets.*;
import display.*;

public class UnitTests {
    
    private static final int SPAWN_OFFSET = 200;

    private static Graphics2D g;
    private static Display d;
    private static JViewport vPort;

    private static Player playerA;
    private static Player playerB;
    private static Pellets pellets;

    public UnitTests() {}

    public static void setBotMovement(){

        setViewport( vPort );
        setGraphics( g );

        playerA = new Player();
        playerB = new Player( Display.OUTER_AREA_WIDTH / 2, Display.OUTER_AREA_HEIGHT / 2 );
        pellets = new Pellets( 1 );

        Food[] singleFood = { new Food( playerA.getX() - SPAWN_OFFSET, playerA.getY() ) };
        pellets.setPellets( singleFood );

    }

    public static boolean runBotMovement(){

        d.setBackground( Color.WHITE );

        playerA.drawPlayer( g );
        playerB.drawPlayer( g );

        pellets.drawPellets( g );

        playerB.handleEaten( pellets );
        
        Rectangle outerArea = new Rectangle( 0, 0, Display.OUTER_AREA_WIDTH, Display.OUTER_AREA_HEIGHT );
        g.draw( outerArea );
        g.dispose();

        if( playerB.overlays( playerA ) ){

            Game.setState( Game.STATE.TEST_SUCCESS );
            return true;

        } 
        else return false;

    }

    public static void setViewPlayer(){

        Point view = new Point( (int) playerA.getX() - Display.WINDOW_WIDTH / 2, (int) playerA.getY() - Display.WINDOW_HEIGHT / 2 );
        vPort.setViewPosition( view );

    }

    public static void setGraphics( Graphics2D graphics )   { g = graphics; }

    public static void setDisplay( Display display )        { d = display; }

    public static Player getPlayerA()                       { return playerA; }

    public static Player getPlayerB()                       { return playerB; }

    public static Pellets getPellets()                      { return pellets; }

    public static void setViewport( JViewport viewPort )    { vPort = viewPort; }

}