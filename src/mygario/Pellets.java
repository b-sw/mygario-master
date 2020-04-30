package mygario;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Pellets {

    private static final int RGB_RANGE = 255;
    private static final double Pellets_DIAMETER = 9.5;

    private Ellipse2D.Double Pellets[];
    private Color PelletsColors[];

    Pellets( final int number ){

        Pellets = new Ellipse2D.Double[number];
        PelletsColors = new Color[number];

        callOnce();

    }

    private void callOnce(){

        initPelletsColors();
        initPellets();
    
    }

    private void initPelletsColors(){

        final Random r = new Random();

        for( int i = 0; i < PelletsColors.length; ++i ){ 
            
            PelletsColors[i] = new Color( r.nextInt( RGB_RANGE ), r.nextInt( RGB_RANGE ), r.nextInt( RGB_RANGE ) ); 
        
        }

    }

    private void initPellets(){

        final Random r = new Random();

        for (int i = 0; i < Pellets.length; ++i ){

            Pellets[i] = new Ellipse2D.Double( r.nextInt( DisplayGame.OUTER_AREA_WIDTH ), r.nextInt( DisplayGame.OUTER_AREA_HEIGHT ), Pellets_DIAMETER, Pellets_DIAMETER );

        }

    }

    public void drawPellets( final Graphics2D g ){

        for( int i = 0; i < Pellets.length; ++i ){

            if( null != Pellets[i] ){

                g.setColor( PelletsColors[i] );
                g.fill( Pellets[i] );

            }

        }

    }

    public Ellipse2D.Double[] getPellets()                     { return this.Pellets; }

    public void setPellets( final Ellipse2D.Double[] Pellets )  { this.Pellets = Pellets; }

    public Color[] getPelletsColors()                           { return this.PelletsColors; }

    public void setPelletsColors( final Color[] PelletsColors ) { this.PelletsColors = PelletsColors; }

}