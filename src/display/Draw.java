package display;

import java.awt.Graphics2D;
import java.util.concurrent.TimeUnit;
import java.awt.Font;
import java.awt.Color;
import java.text.DecimalFormat;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import game.*;
import model.*;

public class Draw {
  
    public static final int OUTER_AREA_WIDTH    = 4000;
    public static final int OUTER_AREA_HEIGHT   = 3000;

    private static final int FONT_SIZE          = 50;
    private static final int INFO_POS_X_OFFSET  = 300;
    private static final int INFO_POS_Y_OFFSET  = 200;
    private static final int INFO_LINE_SPACE    = 20;

    private static final int BUTTON_WIDTH       = 100;
    private static final int BUTTON_HEIGHT      = 50;
    private static final int NEW_GAME_WIDTH     = 240;
    private static final int QUIT_GAME_WIDTH    = 120;

    // $$$$$$$$$$$$$$$$$$$$$$$$$

    private static final int OVAL_W_OFFSET          = 75;
    private static final int OVAL_H_OFFSET          = 250;
    private static final int OVAL_DIAMETER          = 150;
    private static final int STRING_OFFSET          = 100;
    private static final int MESSAGE_STR_OFFSET     = 115;
    private static final int NEW_GAME_OFFSET        = 50;
    
    public static final int BUTTON_STR_OFFSET       = 40;
    public static final int GO_BACK_TEST_OFFSET_X   = 80;

    private static final int INFO_FONT_SIZE = 15;
    // ########################


    private static Rectangle outerArea = new Rectangle( 0, 0, OUTER_AREA_WIDTH, OUTER_AREA_HEIGHT );

    public Draw(){}

    public static void drawPlayer( Graphics2D g, Player player ){

        g.setColor( player.getColor() );
        g.fill( player.getPlayer() );

    }

    public static void printInfo( Graphics2D g2, Game game, long time ){

        double printTime = TimeUnit.SECONDS.convert(System.nanoTime() - time, TimeUnit.NANOSECONDS);

        g2.setColor( Color.BLUE );
        g2.setFont( new Font( "cambria", Font.BOLD, INFO_FONT_SIZE ) );

        int infoX = (int)( game.getMainPlayer().getX() - INFO_POS_X_OFFSET );
        int infoY = (int)( game.getMainPlayer().getY() - INFO_POS_Y_OFFSET );

        g2.drawString( "speed: " + new DecimalFormat( "##.##" ).format( game.getMainPlayer().getVelocity() ), infoX, infoY );
        g2.drawString( "ball radius: " + Math.floor( game.getMainPlayer().getPlayer().height ), infoX, infoY - INFO_LINE_SPACE );
        g2.drawString( "time running: " + printTime, infoX, infoY - INFO_LINE_SPACE * 2 );

    }

    public static void drawPellets( Graphics2D g2, Game game ){

        Food[] pellets = game.getPellets().getPellets();

        for( int i = 0; i < pellets.length; ++i ){

            if( null != pellets[i] ){

                g2.setColor( pellets[i].getFoodColor() );
                g2.fill( pellets[i].getFood() );

            }

        }

    }

    public static void renderGame( Graphics2D g2, Game game, Menu menu, long time ){

        drawPlayer( g2, game.getMainPlayer() );
        drawPlayer( g2, game.getBotPlayer() );

        drawPellets( g2, game );
        
        game.getMainPlayer().handleEaten( game.getPellets() );
        game.getBotPlayer().handleEaten( game.getPellets() );
        Draw.printInfo( g2, game, time );

        game.handleWinLose();

        g2.draw( outerArea );
        g2.dispose(); // clean resources ?

    }

    public static void renderMenu( Graphics2D g ){

        g.setFont( new Font( "cambria", Font.BOLD, FONT_SIZE ) );

        try{
            
            BufferedImage introLogo = ImageIO.read( new File( "../img/elka.jpg" ) );
            g.drawImage( introLogo, Display.WINDOW_WIDTH / 2 - OVAL_W_OFFSET, Display.WINDOW_HEIGHT / 2 - OVAL_H_OFFSET, null );

        }
        catch( IOException exc ){

            g.setColor(Color.GREEN);
            g.fillOval(Display.WINDOW_WIDTH / 2 - OVAL_W_OFFSET, Display.WINDOW_HEIGHT / 2 - OVAL_H_OFFSET, OVAL_DIAMETER, OVAL_DIAMETER);

        }
        
        g.setColor( Color.BLUE );
        g.drawString( "mygario", Display.WINDOW_WIDTH / 2 - STRING_OFFSET, Display.WINDOW_HEIGHT / 2 - STRING_OFFSET / 3 );
        
        Rectangle playButton = new Rectangle( Display.WINDOW_WIDTH / 2 - BUTTON_WIDTH / 2, Display.WINDOW_HEIGHT / 2, BUTTON_WIDTH, BUTTON_HEIGHT );
        Rectangle quitButton = new Rectangle( Display.WINDOW_WIDTH / 2 - BUTTON_WIDTH / 2, Display.WINDOW_HEIGHT / 2 + BUTTON_HEIGHT * 2, BUTTON_WIDTH, BUTTON_HEIGHT );
        Rectangle testButton = new Rectangle( Display.WINDOW_WIDTH / 2 - BUTTON_WIDTH / 2, Display.WINDOW_HEIGHT / 2 + BUTTON_HEIGHT * 4, BUTTON_WIDTH, BUTTON_HEIGHT );

        g.setColor( Color.BLACK );
        g.drawString( "Play", playButton.x, playButton.y + BUTTON_STR_OFFSET );
        g.setColor( Color.GRAY );
        g.drawString( "Quit", quitButton.x, quitButton.y + BUTTON_STR_OFFSET );
        g.setColor( Color.GRAY );
        g.drawString( "Test", testButton.x, testButton.y + BUTTON_STR_OFFSET );

    }

    public static void mainPlayerWin( Graphics2D g, Game game ){

        Point mainPlayerPosition = game.getMPPosition();

        g.setColor( Color.GREEN );
        g.setFont( new Font( "cambria", Font.BOLD, FONT_SIZE ) );
        g.drawString( "YOU WON", mainPlayerPosition.x - MESSAGE_STR_OFFSET, mainPlayerPosition.y - NEW_GAME_OFFSET );

        printPlayAgain( g, game );

    }

    public static void botPlayerWin( Graphics2D g, Game game ){

        Point mainPlayerPosition = game.getMPPosition();

        g.setColor( Color.RED );
        g.setFont( new Font( "cambria", Font.BOLD, FONT_SIZE ) );
        g.drawString( "YOU LOST", mainPlayerPosition.x - MESSAGE_STR_OFFSET, mainPlayerPosition.y - NEW_GAME_OFFSET );

        printPlayAgain( g, game );

    }

    private static void printPlayAgain( Graphics2D g, Game game ){

        Point mainPlayerPosition = game.getMPPosition();

        g.setColor( Color.BLACK );
        g.drawString( "Play again", mainPlayerPosition.x - NEW_GAME_WIDTH / 2, mainPlayerPosition.y + NEW_GAME_OFFSET );

        g.setFont( new Font( "cambria", Font.BOLD, FONT_SIZE / 2 ) );
        g.setColor( Color.GRAY );
        g.drawString( "Quit game", mainPlayerPosition.x - QUIT_GAME_WIDTH / 2, mainPlayerPosition.y + 2 * NEW_GAME_OFFSET );        

    }

}