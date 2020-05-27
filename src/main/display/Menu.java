package main.display;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.game.*;

public class Menu implements MouseListener{

    /* CONSTANTS */

    public static final int BUTTON_STR_OFFSET       = 40;
    public static final int GO_BACK_TEST_OFFSET_X   = 80;

    public String[] args;
    Display display;

    public Menu( Display display )  { this.display = display; }

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

        if( Buttons.clickedTest( mouseX, mouseY ) ){

            Game.setState( Game.STATE.TEST );

        }

        if( Buttons.clickedNewGame( mouseX, mouseY, display.getGame() ) ){

            Game.setState( Game.STATE.GAME );
            display.setGame( new Game() );
            display.resetTime();

        }
        
        if( Buttons.clickedQuitGame( mouseX, mouseY, display.getGame() ) ){

            System.exit( 1 );

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