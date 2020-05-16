package display;

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
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

public class Display extends JPanel implements ActionListener {

    public static final int OUTER_AREA_WIDTH = 4000;
    public static final int OUTER_AREA_HEIGHT = 3000;
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;

    private static final int TIMER_SPEED_MS = 30;
    private static final int FONT_SIZE = 15;
    private static final int INFO_POS_X_OFFSET = 300;
    private static final int INFO_POS_Y_OFFSET = 200;
    private static final int INFO_LINE_SPACE = 20;

    private Rectangle outerArea;
    private JViewport vPort;

    public String[] args;
    public Menu menu;
    public Game game;

    private long time;

    private Point mainPlayerPosition;

    public Display() {
        
        Timer timer = new Timer( TIMER_SPEED_MS, this );

        menu        = new Menu(this);
        game        = new Game();
        time        = System.nanoTime();

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
        
        if( Game.STATE.MENU == Game.getState() ){

            menu.renderMenu( g2 );

        }
        else if( Game.STATE.GAME == Game.getState() ){

            setBackground( Color.WHITE );

            game.getMainPlayer().drawPlayer( g2 );
            game.getBotPlayer().drawPlayer( g2 );
            
            /* For end-game message */
            mainPlayerPosition = new Point( (int)( game.getMainPlayer().getX() ), (int)( game.getMainPlayer().getY() ) );
            menu.setPoint( mainPlayerPosition );

            game.getPellets().drawPellets( g2 );

            game.getMainPlayer().handleEaten( game.getPellets() );
            game.getBotPlayer().handleEaten( game.getPellets() );
            printInfo( g2 );

            game.handleWinLose();

            g2.draw( outerArea );
            g2.dispose(); // clean resources ?

        }
        else if( Game.STATE.WIN == Game.getState() ){
            
            menu.mainPlayerWin( g2 );

        }
        else if( Game.STATE.LOSE == Game.getState() ){

            menu.botPlayerWin( g2 );

        }

    }

    private void printInfo( Graphics2D g2 ){

        double printTime = TimeUnit.SECONDS.convert(System.nanoTime() - time, TimeUnit.NANOSECONDS);

        g2.setColor( Color.BLUE );
        g2.setFont( new Font( "cambria", Font.BOLD, FONT_SIZE ) );

        int infoX = (int)( game.getMainPlayer().getX() - INFO_POS_X_OFFSET );
        int infoY = (int)( game.getMainPlayer().getY() - INFO_POS_Y_OFFSET );

        g2.drawString( "speed: " + new DecimalFormat( "##.##" ).format( game.getMainPlayer().getVelocity() ), infoX, infoY );
        g2.drawString( "ball radius: " + Math.floor( game.getMainPlayer().getPlayer().height ), infoX, infoY - INFO_LINE_SPACE );
        g2.drawString( "time running: " + printTime, infoX, infoY - INFO_LINE_SPACE * 2 );

    }

    public void actionPerformed( ActionEvent e ){ 

        if( Game.STATE.GAME == Game.getState() ){

            Point mousePosition = getMousePosition();

            game.getMainPlayer().moveManually( vPort, mousePosition );

            game.getBotPlayer().moveAutomatically( game.getMainPlayer(), game.getPellets() );
            
            repaint();

        }

    } 

    public void setViewPort(JViewport vPort)        { this.vPort = vPort; }

    public void setGame( Game game )                { this.game = game; }

    public void resetTime( )                        { this.time = System.nanoTime(); }

}