package main.display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import main.game.*;

public class Display extends JPanel implements ActionListener {

    public static final int OUTER_AREA_WIDTH    = 4000;
    public static final int OUTER_AREA_HEIGHT   = 3000;
    public static final int WINDOW_WIDTH        = 800;
    public static final int WINDOW_HEIGHT       = 600;

    private static final int TIMER_SPEED_MS     = 30;

    private JViewport vPort;

    private Menu menu;
    private Game game;
    private long time;

    public Display() {
        
        Timer timer = new Timer( TIMER_SPEED_MS, this );

        menu        = new Menu(this);
        game        = new Game();
        time        = System.nanoTime();

        addMouseListener( menu );
        setFocusable( true );
        requestFocusInWindow();
        
        Dimension newSize = new Dimension( OUTER_AREA_WIDTH, OUTER_AREA_HEIGHT );
        setPreferredSize( newSize );
        
        timer.start();

    }

    public void paintComponent( Graphics g ){

        super.paintComponent( g );
        
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
        
        setBackground( Color.LIGHT_GRAY );

        if( Game.STATE.MENU == Game.getState() ){

            Draw.renderMenu( g2 );

        }
        else if( Game.STATE.GAME == Game.getState() ){

            setBackground( Color.WHITE );

            Draw.renderGame( g2, game, menu, time );

        }
        else if( Game.STATE.WIN == Game.getState() ){
            
            Draw.mainPlayerWin( g2, game );

        }
        else if( Game.STATE.LOSE == Game.getState() ){

            Draw.botPlayerWin( g2, game );

        }
        else if( Game.STATE.TEST == Game.getState() ){

            //menu.printUnitTests( g2, vPort );

        }

    }

    public void actionPerformed( ActionEvent e ){ 

        if( Game.STATE.GAME == Game.getState() ){

            Point mousePosition = getMousePosition();

            game.getMainPlayer().moveManually( mousePosition );

            setViewPosition();

            game.getBotPlayer().moveAutomatically( game.getMainPlayer(), game.getPellets() );

        }

        repaint();

    } 

    private void setViewPosition(){

        Point view = new Point( (int) game.getMainPlayer().getX() - WINDOW_WIDTH / 2, (int) game.getMainPlayer().getY() - WINDOW_HEIGHT / 2 );
        vPort.setViewPosition( view );

    }

    public void setViewPort(JViewport vPort)        { this.vPort = vPort; }

    public void setGame( Game game )                { this.game = game; }
    public Game getGame()                           { return this.game; }

    public void resetTime()                        { this.time = System.nanoTime(); }

}