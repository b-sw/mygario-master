//package mygario;

import java.awt.Color;
import java.awt.Dimension;
//import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
//import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//import java.text.DecimalFormat;
//import java.util.concurrent.TimeUnit;
import javax.swing.*;

public class DisplayGame extends JPanel implements ActionListener {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int OUTER_AREA_WIDTH = 4000;
    public static final int OUTER_AREA_HEIGHT = 3000;
    public static final int TIMER_SPEED = 30;

    private Rectangle outerArea;
    private JViewport vPort;

    public String[] args;
    public Menu menu;

    public static enum STATE{
        MENU,
        GAME,
        WIN,
        LOSE
    };
    
    public static STATE state = STATE.MENU;

    public DisplayGame() {
        
        menu = new Menu( this );

        setFocusable( true );
        requestFocusInWindow();
        
        Dimension newSize = new Dimension( OUTER_AREA_WIDTH, OUTER_AREA_HEIGHT );
        outerArea = new Rectangle( 0, 0, OUTER_AREA_WIDTH, OUTER_AREA_HEIGHT );

        setPreferredSize( newSize );

    }

    public void setViewPort( JViewport vPort ){

        this.vPort = vPort;

    }

    public void paintComponent( Graphics g ){

        super.paintComponent( g );
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
        setBackground( Color.WHITE );
        
        if( STATE.MENU == state ){

            menu.render( g2 );

        }

    }

    public void actionPerformed( ActionEvent e ){ /* abstract method stub */ } 

}