//package mygario;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Menu implements MouseListener{

    /* CONSTANTS */

    private static final int BUTTON_WIDTH = 100;
    private static final int BUTTON_HEIGHT = 50;
    private static final int BUTTON_STR_OFFSET = 40;
    private static final int OVAL_W_OFFSET = 75;
    private static final int OVAL_H_OFFSET = 250;
    private static final int OVAL_DIAMETER = 150;
    private static final int STRING_OFFSET = 150;

    private static final int PLAY_X = DisplayGame.WIDTH / 2 - BUTTON_WIDTH / 2;
    private static final int PLAY_Y = DisplayGame.HEIGHT / 2;
    private static final int QUIT_X = PLAY_X;
    private static final int QUIT_Y = PLAY_Y + BUTTON_HEIGHT * 2;

    private static final int FONT_SIZE = 50;

    private Rectangle playButton = new Rectangle( DisplayGame.WIDTH / 2 - BUTTON_WIDTH / 2, DisplayGame.HEIGHT / 2, BUTTON_WIDTH, BUTTON_HEIGHT );
    private Rectangle quitButton = new Rectangle( DisplayGame.WIDTH / 2 - BUTTON_WIDTH / 2, DisplayGame.HEIGHT / 2 + BUTTON_HEIGHT * 2, BUTTON_WIDTH, BUTTON_HEIGHT );

    //private DisplayGame displayGame;
    private Point centreCamera;
    public String[] args;

    public Menu( DisplayGame displayGame )  { /*this.displayGame = displayGame;*/ }
    //public void setArgs( String[] a )    { this.args = a; }

    public void render( Graphics2D g ){

        g.setFont( new Font( "cambria", Font.BOLD, FONT_SIZE ) );

        g.setColor( Color.GREEN );
        g.fillOval( DisplayGame.WIDTH / 2 - OVAL_W_OFFSET, DisplayGame.HEIGHT / 2 - OVAL_H_OFFSET, OVAL_DIAMETER, OVAL_DIAMETER );
        
        g.setColor( Color.BLUE );
        g.drawString( "Test mygario", DisplayGame.WIDTH / 2 - STRING_OFFSET, DisplayGame.HEIGHT / 2 - STRING_OFFSET / 3 );
        
        g.setColor( Color.BLACK );
        g.drawString( "Play", playButton.x, playButton.y + BUTTON_STR_OFFSET );
        g.setColor( Color.GRAY );
        g.drawString( "Quit", quitButton.x, quitButton.y + BUTTON_STR_OFFSET );
        
        g.drawRect( DisplayGame.WIDTH / 2 - BUTTON_WIDTH / 2, DisplayGame.HEIGHT / 2, BUTTON_WIDTH, BUTTON_HEIGHT ); 
        g.drawRect( DisplayGame.WIDTH / 2 - BUTTON_WIDTH / 2, DisplayGame.HEIGHT / 2 + BUTTON_HEIGHT * 2, BUTTON_WIDTH, BUTTON_HEIGHT );

    }

    //public void setDisplayGame( DisplayGame dg ){ displayGame = dg; }

    public void setPoint( Point point ){ this.centreCamera = point; }

    @Override
    public void mouseClicked( MouseEvent e ){ 
    
        int mouseX = e.getX();
        int mouseY = e.getY();

        if( mouseX >= PLAY_X && mouseX <= PLAY_X + BUTTON_WIDTH ){

            if( mouseY >= PLAY_Y && mouseY <= PLAY_Y + BUTTON_HEIGHT ){

                DisplayGame.state = DisplayGame.STATE.GAME;

            }

        }
        
        if( mouseX >= QUIT_X && mouseX <= QUIT_X + BUTTON_WIDTH ){

            if( mouseY >= QUIT_Y && mouseY <= QUIT_Y + BUTTON_HEIGHT ){

                System.exit( 1 );

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

}