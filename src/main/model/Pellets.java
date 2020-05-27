package main.model;

public class Pellets {

    private Food pellets[];

    public Pellets( final int number ){

        pellets = new Food[number];
        
        for( int i = 0; i < pellets.length; ++i ){

            pellets[i] = new Food();

        }

    }

    public void eatPellet( int which )                          { pellets[which] = null ; }

    public Food[] getPellets()                                  { return this.pellets; }

    public void setPellets( final Food[] pellets )              { this.pellets = pellets; }

}