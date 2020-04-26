//package mygario;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
//import java.awt.Point;
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

    private static final int FONT_SIZE = 50;

    private Rectangle playButton = new Rectangle( DisplayGame.WIDTH / 2 - BUTTON_WIDTH / 2, DisplayGame.HEIGHT / 2, BUTTON_WIDTH, BUTTON_HEIGHT );
    private Rectangle quitButton = new Rectangle( DisplayGame.WIDTH / 2 - BUTTON_WIDTH / 2, DisplayGame.HEIGHT / 2 + BUTTON_HEIGHT * 2, BUTTON_WIDTH, BUTTON_HEIGHT );

    //private DisplayGame displayGame;
    public String[] args;

    public Menu( DisplayGame displayGame )  { /*this.displayGame = displayGame;*/ }
    //public void setArgs( String[] a )    { this.args = a; }

    public void render( Graphics2D g ){

        Font font = new Font( "cambria", Font.BOLD, FONT_SIZE );
        g.setFont( font );

        g.setColor( Color.GREEN );
        g.fillOval( DisplayGame.WIDTH / 2 - OVAL_W_OFFSET, DisplayGame.HEIGHT / 2 - OVAL_H_OFFSET, OVAL_DIAMETER, OVAL_DIAMETER );
       
        g.setColor( Color.BLUE );
        g.drawString( "Test mygario", DisplayGame.WIDTH / 2 - STRING_OFFSET, DisplayGame.HEIGHT / 2 - STRING_OFFSET / 3 );
        
        g.setColor( Color.BLACK );
        g.drawString( "Play", playButton.x, playButton.y + BUTTON_STR_OFFSET );
        g.drawString( "Quit", quitButton.x, quitButton.y + BUTTON_STR_OFFSET );

    }

    //public void setDisplayGame( DisplayGame dg ){ displayGame = dg; }

    @Override
    public void mouseClicked( MouseEvent e )    { /* abstract method stub */ }

    @Override
    public void mouseReleased( MouseEvent e )   { /* abstract method stub */ }
    
    @Override
    public void mouseExited( MouseEvent e )     { /* abstract method stub */ }

    @Override
    public void mousePressed( MouseEvent e )    { /* abstract method stub */ }

    @Override
    public void mouseEntered( MouseEvent e )    { /* abstract method stub */ }

}