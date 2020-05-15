package assets;

import java.awt.Graphics2D;

public class Pellets {

    private Food pellets[];

    public Pellets( final int number ){

        pellets = new Food[number];
        
        for( int i = 0; i < pellets.length; ++i ){

            pellets[i] = new Food();

        }

    }

    public void drawPellets( final Graphics2D g ){

        for( int i = 0; i < pellets.length; ++i ){

            if( null != pellets[i] ){

                g.setColor( pellets[i].getFoodColor() );
                g.fill( pellets[i].getFood() );

            }

        }

    }

    public Food[] getPellets()                                  { return this.pellets; }

    public void setPellets( final Food[] pellets )              { this.pellets = pellets; }

}