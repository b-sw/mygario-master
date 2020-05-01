package mygario;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;

public class main {

    public static boolean gamemode;

    public static void main( String[] args ){

            JFrame      frame   = new JFrame( "mygario" );
            JScrollPane pane    = new JScrollPane();
            JViewport   vPort   = new JViewport();
            DisplayGame display   = new DisplayGame();

            //display.menu.setArgs( args );
            vPort.add( display );
            pane.setViewport( vPort );
            display.setViewPort( vPort );
            
            frame.setVisible( true );
            frame.add( pane );
            frame.setSize( DisplayGame.WIDTH, DisplayGame.HEIGHT );
            frame.setResizable( false );

            pane.setVerticalScrollBarPolicy( ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER );
            pane.setHorizontalScrollBarPolicy( ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
            
            frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
    }

}