package mygario;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Pellet {

    private static final int RGB_RANGE = 255;
    private static final double PELLET_DIAMETER = 9.5;

    private Ellipse2D.Double pellets[];
    private Color pelletColors[];

    Pellet( final int number ){

        pellets = new Ellipse2D.Double[number];
        pelletColors = new Color[number];

        callOnce();

    }

    private void callOnce(){

        initPelletColors();
        initPellets();
    
    }

    private void initPelletColors(){

        final Random r = new Random();

        for( int i = 0; i < pelletColors.length; ++i ){ 
            
            pelletColors[i] = new Color( r.nextInt( RGB_RANGE ), r.nextInt( RGB_RANGE ), r.nextInt( RGB_RANGE ) ); 
        
        }

    }

    private void initPellets(){

        final Random r = new Random();

        for (int i = 0; i < pellets.length; ++i ){

            pellets[i] = new Ellipse2D.Double( r.nextInt( DisplayGame.OUTER_AREA_WIDTH ), r.nextInt( DisplayGame.OUTER_AREA_HEIGHT ), PELLET_DIAMETER, PELLET_DIAMETER );

        }

    }

    public void drawPellets( final Graphics2D g ){

        for( int i = 0; i < pellets.length; ++i ){

            if( null != pellets[i] ){

                g.setColor( pelletColors[i] );
                g.fill( pellets[i] );

            }

        }

    }

    public Ellipse2D.Double[] getPellets()              { return this.pellets; }

    public void setPellets( final Ellipse2D.Double[] pellets ){ this.pellets = pellets; }

    public Color[] getPelletColors()                    { return this.pelletColors; }

    public void setPelletColors( final Color[] pelletColors ) { this.pelletColors = pelletColors; }

}