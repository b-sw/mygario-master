package display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import game.*;

public class Menu implements MouseListener{

    /* CONSTANTS */

    private static final int BUTTON_WIDTH       = 100;
    private static final int BUTTON_HEIGHT      = 50;
    private static final int NEW_GAME_WIDTH     = 240;
    private static final int QUIT_GAME_WIDTH    = 120;

    private static final int OVAL_W_OFFSET          = 75;
    private static final int OVAL_H_OFFSET          = 250;
    private static final int OVAL_DIAMETER          = 150;
    private static final int STRING_OFFSET          = 100;
    private static final int MESSAGE_STR_OFFSET     = 115;
    private static final int NEW_GAME_OFFSET        = 50;
    private static final int TEST_SUCCESS_OFFSET_X  = 200;
    
    public static final int BUTTON_STR_OFFSET       = 40;
    public static final int GO_BACK_TEST_OFFSET_X   = 80;

    private static final int FONT_SIZE = 50;

    private Rectangle playButton = new Rectangle( Display.WINDOW_WIDTH / 2 - BUTTON_WIDTH / 2, Display.WINDOW_HEIGHT / 2, BUTTON_WIDTH, BUTTON_HEIGHT );
    private Rectangle quitButton = new Rectangle( Display.WINDOW_WIDTH / 2 - BUTTON_WIDTH / 2, Display.WINDOW_HEIGHT / 2 + BUTTON_HEIGHT * 2, BUTTON_WIDTH, BUTTON_HEIGHT );
    private Rectangle testButton = new Rectangle( Display.WINDOW_WIDTH / 2 - BUTTON_WIDTH / 2, Display.WINDOW_HEIGHT / 2 + BUTTON_HEIGHT * 4, BUTTON_WIDTH, BUTTON_HEIGHT );

    private Rectangle testFirst  = new Rectangle( Display.WINDOW_WIDTH / 2 - BUTTON_WIDTH, Display.WINDOW_HEIGHT / 2, BUTTON_WIDTH * 2, BUTTON_HEIGHT / 2 );
    
    private BufferedImage introLogo = null;
    private Point mainPlayerPosition;
    private Point stringRefPoint;

    public String[] args;
    Display display;

    public Menu( Display display )  { this.display = display; }

    public void renderMenu( Graphics2D g ){

        g.setFont( new Font( "cambria", Font.BOLD, FONT_SIZE ) );

        try{
            
            introLogo = ImageIO.read( new File( "../img/elka.jpg" ) );
            g.drawImage( introLogo, Display.WINDOW_WIDTH / 2 - OVAL_W_OFFSET, Display.WINDOW_HEIGHT / 2 - OVAL_H_OFFSET, null );

        }
        catch( IOException exc ){

            g.setColor(Color.GREEN);
            g.fillOval(Display.WINDOW_WIDTH / 2 - OVAL_W_OFFSET, Display.WINDOW_HEIGHT / 2 - OVAL_H_OFFSET, OVAL_DIAMETER, OVAL_DIAMETER);

        }
        
        g.setColor( Color.BLUE );
        g.drawString( "mygario", Display.WINDOW_WIDTH / 2 - STRING_OFFSET, Display.WINDOW_HEIGHT / 2 - STRING_OFFSET / 3 );
        
        g.setColor( Color.BLACK );
        g.drawString( "Play", playButton.x, playButton.y + BUTTON_STR_OFFSET );
        g.setColor( Color.GRAY );
        g.drawString( "Quit", quitButton.x, quitButton.y + BUTTON_STR_OFFSET );
        g.setColor( Color.GRAY );
        g.drawString( "Test", testButton.x, testButton.y + BUTTON_STR_OFFSET );

    }

    public void mainPlayerWin( Graphics2D g ){

        g.setColor( Color.GREEN );
        g.setFont( new Font( "cambria", Font.BOLD, FONT_SIZE ) );
        g.drawString( "YOU WON", mainPlayerPosition.x - MESSAGE_STR_OFFSET, mainPlayerPosition.y - NEW_GAME_OFFSET );

        printPlayAgain( g );

    }

    public void botPlayerWin( Graphics2D g ){

        g.setColor( Color.RED );
        g.setFont( new Font( "cambria", Font.BOLD, FONT_SIZE ) );
        g.drawString( "YOU LOST", mainPlayerPosition.x - MESSAGE_STR_OFFSET, mainPlayerPosition.y - NEW_GAME_OFFSET );

        printPlayAgain( g );

    }

    public void printPlayAgain( Graphics2D g ){

        g.setColor( Color.BLACK );
        g.drawString( "Play again", mainPlayerPosition.x - NEW_GAME_WIDTH / 2, mainPlayerPosition.y + NEW_GAME_OFFSET );

        g.setFont( new Font( "cambria", Font.BOLD, FONT_SIZE / 2 ) );
        g.setColor( Color.GRAY );
        g.drawString( "Quit game", mainPlayerPosition.x - QUIT_GAME_WIDTH / 2, mainPlayerPosition.y + 2 * NEW_GAME_OFFSET );        

    }

    public void printUnitTests( Graphics2D g, JViewport vPort ){

        Point p = new Point( testFirst.x - Display.WINDOW_WIDTH / 2 + BUTTON_WIDTH, testFirst.y - Display.WINDOW_HEIGHT / 2 );
        vPort.setViewPosition( p );

        g.setFont( new Font( "cambria", Font.BOLD, FONT_SIZE / 2 ) );
        g.setColor( Color.BLACK );
        g.drawString( "bot movement", testFirst.x, testFirst.y );


        g.drawString( "go back", testFirst.x, testFirst.y + BUTTON_HEIGHT * 3 );

    }

    public void printTestSuccessful( Graphics2D g ){

        g.setColor( Color.GREEN );
        g.setFont( new Font( "cambria", Font.BOLD, FONT_SIZE ) );
        g.drawString( "TEST SUCCESSFUL", (int)UnitTests.getPlayerB().getX() - TEST_SUCCESS_OFFSET_X, (int)UnitTests.getPlayerB().getY() );

        g.setColor( Color.GRAY );
        g.drawString( "go back", (int)UnitTests.getPlayerB().getX() - GO_BACK_TEST_OFFSET_X, (int)UnitTests.getPlayerB().getY() + BUTTON_STR_OFFSET );

        stringRefPoint = new Point( (int)( UnitTests.getPlayerB().getX() ), (int)( UnitTests.getPlayerB().getY() ) );

    }

    @Override
    public void mouseClicked( MouseEvent e ){ 
    
        int mouseX = e.getX();
        int mouseY = e.getY();

        if( Buttons.clickedPlay( mouseX, mouseY ) ){
            
            Game.setState( Game.STATE.GAME );

        }

        if( Buttons.clickedQuit( mouseX, mouseY ) ){

            System.exit( 1 );

        }

        if( Buttons.clickedTestMenu( mouseX, mouseY ) ){

            Game.setState( Game.STATE.TEST_MENU );

        }

        if( Buttons.clickedNewGame( mouseX, mouseY, mainPlayerPosition ) ){

            Game.setState( Game.STATE.GAME );
            display.setGame( new Game() );
            display.resetTime();

        }
        
        if( Buttons.clickedQuitGame( mouseX, mouseY, mainPlayerPosition ) ){

            System.exit( 1 );

        }
        
        if( Buttons.clickedTestBMov( mouseX, mouseY ) ){

            UnitTests.setBotMovement();
            Game.setState( Game.STATE.TEST );

        }

        if( Buttons.clickedGoBackMenu( mouseX, mouseY ) ){

            Game.setState( Game.STATE.MENU );

        }

        if( Buttons.clickedGoBackTestMenu( mouseX, mouseY, stringRefPoint ) ){

            Game.setState( Game.STATE.TEST_MENU );

        }
    
    }

    @Override
    public void mouseReleased( MouseEvent e )   { /* abstract method stub */ }
    
    @Override
    public void mouseExited( MouseEvent e )     { /* abstract method stub */ }

    @Override
    public void mousePressed( MouseEvent e )    { /* abstract method stub */ }

    @Override
    public void mouseEntered( MouseEvent e )    { /* abstract method stub */ }

    public void setPoint( Point mainPlayerPos ) { this.mainPlayerPosition = mainPlayerPos; }

}