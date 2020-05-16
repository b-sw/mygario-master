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

public class Menu implements MouseListener{

    /* CONSTANTS */

    private static final int BUTTON_WIDTH = 100;
    private static final int BUTTON_HEIGHT = 50;

    private static final int NEW_GAME_WIDTH = 240;
    private static final int NEW_GAME_HEIGHT = 60;
    private static final int QUIT_GAME_WIDTH = 120;
    private static final int QUIT_GAME_HEIGHT = 30;

    private static final int BUTTON_STR_OFFSET = 40;
    private static final int OVAL_W_OFFSET = 75;
    private static final int OVAL_H_OFFSET = 250;
    private static final int OVAL_DIAMETER = 150;
    private static final int STRING_OFFSET = 150;
    private static final int MESSAGE_STR_OFFSET = 115;
    private static final int NEW_GAME_OFFSET = 50;

    private static final int PLAY_X = Display.WINDOW_WIDTH / 2 - BUTTON_WIDTH / 2;
    private static final int PLAY_Y = Display.WINDOW_HEIGHT / 2;
    private static final int QUIT_X = PLAY_X;
    private static final int QUIT_Y = PLAY_Y + BUTTON_HEIGHT * 2;

    private static final int FONT_SIZE = 50;

    private Rectangle playButton = new Rectangle( Display.WINDOW_WIDTH / 2 - BUTTON_WIDTH / 2, Display.WINDOW_HEIGHT / 2, BUTTON_WIDTH, BUTTON_HEIGHT );
    private Rectangle quitButton = new Rectangle( Display.WINDOW_WIDTH / 2 - BUTTON_WIDTH / 2, Display.WINDOW_HEIGHT / 2 + BUTTON_HEIGHT * 2, BUTTON_WIDTH, BUTTON_HEIGHT );
    
    private BufferedImage introLogo = null;
    private Point mainPlayerPosition;

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
        g.drawString( "Test mygario", Display.WINDOW_WIDTH / 2 - STRING_OFFSET, Display.WINDOW_HEIGHT / 2 - STRING_OFFSET / 3 );
        
        g.setColor( Color.BLACK );
        g.drawString( "Play", playButton.x, playButton.y + BUTTON_STR_OFFSET );
        g.setColor( Color.GRAY );
        g.drawString( "Quit", quitButton.x, quitButton.y + BUTTON_STR_OFFSET );
        
        g.drawRect( Display.WINDOW_WIDTH / 2 - BUTTON_WIDTH / 2, Display.WINDOW_HEIGHT / 2, BUTTON_WIDTH, BUTTON_HEIGHT ); 
        g.drawRect( Display.WINDOW_WIDTH / 2 - BUTTON_WIDTH / 2, Display.WINDOW_HEIGHT / 2 + BUTTON_HEIGHT * 2, BUTTON_WIDTH, BUTTON_HEIGHT );

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

        g.drawRect( mainPlayerPosition.x - NEW_GAME_WIDTH / 2, mainPlayerPosition.y + NEW_GAME_OFFSET / 2 - BUTTON_STR_OFFSET / 2 , NEW_GAME_WIDTH, NEW_GAME_HEIGHT ); 
        g.drawRect( mainPlayerPosition.x - QUIT_GAME_WIDTH / 2, mainPlayerPosition.y + 2 * NEW_GAME_OFFSET - BUTTON_STR_OFFSET / 2, QUIT_GAME_WIDTH, QUIT_GAME_HEIGHT );

    }

    @Override
    public void mouseClicked( MouseEvent e ){ 
    
        int mouseX = e.getX();
        int mouseY = e.getY();

        if( Game.STATE.MENU == Game.getState() ){

            if( mouseX >= PLAY_X && mouseX <= PLAY_X + BUTTON_WIDTH ){

                if( mouseY >= PLAY_Y && mouseY <= PLAY_Y + BUTTON_HEIGHT ){

                    Game.setState( Game.STATE.GAME );
                }

            }
        
            if( mouseX >= QUIT_X && mouseX <= QUIT_X + BUTTON_WIDTH ){

                if( mouseY >= QUIT_Y && mouseY <= QUIT_Y + BUTTON_HEIGHT ){

                    System.exit( 1 );

                }

            }

        }
        else if( Game.STATE.WIN == Game.getState() || Game.STATE.LOSE == Game.getState() ){

            int playNewOffsetY = ( NEW_GAME_OFFSET - BUTTON_STR_OFFSET ) / 2;
            int quitNewOffsetY = 2 * NEW_GAME_OFFSET - BUTTON_STR_OFFSET / 2;

            if( mouseX >= mainPlayerPosition.x - NEW_GAME_WIDTH / 2 && mouseX <= mainPlayerPosition.x + NEW_GAME_WIDTH / 2 ){

                if( mouseY >= mainPlayerPosition.y + playNewOffsetY && mouseY <= mainPlayerPosition.y + playNewOffsetY + NEW_GAME_HEIGHT ){

                    Game.setState( Game.STATE.GAME );
                    display.setGame( new Game() );
                    display.resetTime();

                }

            }
        
            if( mouseX >= mainPlayerPosition.x - QUIT_GAME_WIDTH / 2 && mouseX <= mainPlayerPosition.x + QUIT_GAME_WIDTH / 2 ){

                if( mouseY >= mainPlayerPosition.y + quitNewOffsetY && mouseY <= mainPlayerPosition.y + quitNewOffsetY + QUIT_GAME_HEIGHT ){

                    System.exit( 1 );

                }

            }

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