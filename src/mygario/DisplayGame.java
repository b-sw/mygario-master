package mygario;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

public class DisplayGame extends JPanel implements ActionListener {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int OUTER_AREA_WIDTH = 4000;
    public static final int OUTER_AREA_HEIGHT = 3000;
    
    private static final int TIMER_SPEED_MS = 30;
    private static final int FONT_SIZE = 15;
    private static final int INFO_POS_X_OFFSET = 300;
    private static final int INFO_POS_Y_OFFSET = 200;
    private static final int INFO_LINE_SPACE = 20;
    private static final int NUM_OF_PELLETS = 1000;

    private Rectangle outerArea;
    private JViewport vPort;

    public String[] args;
    public Menu menu;

    private Player mainPlayer;
    private Player botPlayer;
    private long time;

    private Point mainPlayerPosition;
    
    private Pellets pellets;

    public static enum STATE{
        MENU,
        GAME,
        WIN,
        LOSE
    };
    
    public static STATE state = STATE.MENU;

    public DisplayGame() {
        
        Timer timer = new Timer( TIMER_SPEED_MS, this );

        menu        = new Menu(this);
        mainPlayer  = new Player();
        botPlayer   = new Player();

        time = System.nanoTime();

        pellets = new Pellets( NUM_OF_PELLETS );

        addMouseListener( menu );
        setFocusable( true );
        requestFocusInWindow();
        
        Dimension newSize = new Dimension( OUTER_AREA_WIDTH, OUTER_AREA_HEIGHT );

        outerArea = new Rectangle( 0, 0, OUTER_AREA_WIDTH, OUTER_AREA_HEIGHT );

        setPreferredSize( newSize );
        
        timer.start();

    }

    public void paintComponent( Graphics g ){

        super.paintComponent( g );
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
        setBackground( Color.LIGHT_GRAY );
        
        if( STATE.MENU == state ){

            menu.render( g2 );

        }
        else if( STATE.GAME == state ){

            setBackground( Color.WHITE );

            mainPlayer.drawPlayer( g2 );
            botPlayer.drawPlayer( g2 );
            
            /* For end-game message */
            mainPlayerPosition = new Point( (int)( mainPlayer.getX() ), (int)( mainPlayer.getY() ) );
            menu.setPoint( mainPlayerPosition );

            pellets.drawPellets( g2 );

            checkIfEaten();
            printInfo( g2 );

            winOrLose();

            g2.draw( outerArea );
            g2.dispose(); // clean resources ?

        }
        else if( STATE.WIN == state ){
            
            menu.mainPlayerWin( g2 );

        }
        else if( STATE.LOSE == state ){

            menu.mainPlayerWin( g2 );

        }

    }

    private void winOrLose(){

        double x1 = mainPlayer.getMidX();
        double y1 = mainPlayer.getMidY();
        double x2 = botPlayer.getMidX();
        double y2 = botPlayer.getMidY();

        double distance = Math.sqrt( ( x1 - x2 ) * ( x1 - x2 ) + ( y1 - y2 ) * ( y1 - y2 ) );

        if( distance < mainPlayer.getRadius() && mainPlayer.getRadius() > botPlayer.getRadius() ){

            state = STATE.WIN;

        }
        else if( distance < botPlayer.getRadius() && mainPlayer.getRadius() < botPlayer.getRadius() ){

            state = STATE.LOSE;

        }

    }

    private void printInfo( Graphics2D g2 ){

        double printTime = TimeUnit.SECONDS.convert(System.nanoTime() - time, TimeUnit.NANOSECONDS);

        g2.setColor( Color.BLUE );
        g2.setFont( new Font( "cambria", Font.BOLD, FONT_SIZE ) );

        int infoX = (int)( mainPlayer.getX() - INFO_POS_X_OFFSET );
        int infoY = (int)( mainPlayer.getY() - INFO_POS_Y_OFFSET );

        g2.drawString( "speed: " + new DecimalFormat( "##.##" ).format( mainPlayer.getVelocity() ), infoX, infoY );
        g2.drawString( "ball radius: " + Math.floor( mainPlayer.getPlayer().height ), infoX, infoY - INFO_LINE_SPACE );
        g2.drawString( "time running: " + printTime, infoX, infoY - INFO_LINE_SPACE * 2 );

    }

    public void checkIfEaten(){

        for( int i = 0; i < pellets.getPellets().length ; ++i ){

            if( null != pellets.getPellets()[i] && mainPlayer.getPlayer().intersects( pellets.getPellets()[i].getBounds2D() ) ){

                pellets.getPellets()[i] = null;
                mainPlayer.increaseSize();

            }

        }

    }

    public void actionPerformed( ActionEvent e ){ 

        if( STATE.GAME == state ){

            Point mousePosition = getMousePosition();

            if( null == mousePosition ) return;

            double deltaX = mousePosition.x - mainPlayer.getPlayer().getCenterX();
            double deltaY = mousePosition.y - mainPlayer.getPlayer().getCenterY();

            if( Player.PLAYER_DIAMETER / 2 < Math.sqrt( deltaX * deltaX ) + Math.sqrt( deltaY * deltaY ) ){

                    double angle = Math.atan2( deltaY, deltaX );

                    mainPlayer.setX( mainPlayer.getX() + (int)( mainPlayer.getVelocity() * Math.cos( angle ) ) );
                    mainPlayer.setY( mainPlayer.getY() + (int)( mainPlayer.getVelocity() * Math.sin( angle ) ) );

                    /*double move = Math.sqrt( deltaX* deltaX + deltaY * deltaY );

                    double deltaXNormalized = deltaX / move;
                    double deltaYNormalized = deltaY / move;

                    mainPlayer.setX( mainPlayer.getX() + deltaXNormalized * mainPlayer.getVelocity() );
                    mainPlayer.setY( mainPlayer.getY() + deltaYNormalized * mainPlayer.getVelocity() );*/

                    Point view = new Point((int) mainPlayer.getX() - WIDTH / 2, (int) mainPlayer.getY() - HEIGHT / 2);
                    vPort.setViewPosition(view);

            }
            
            
            repaint();

        }

    } 

    public Player getMainPlayer()               { return this.mainPlayer; }

    public void setMainPlayer( Player player )  { this.mainPlayer = player; }

    public void setViewPort(JViewport vPort)    { this.vPort = vPort; }

}