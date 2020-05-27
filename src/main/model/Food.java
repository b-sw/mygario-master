package main.model;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import main.display.Display;

public class Food {
    
    private static final int RGB_RANGE = 255;
    public static final double PELLETS_DIAMETER = 9.5;

    private Ellipse2D.Double food;
    private Color color;

    public Food(){

        final Random r = new Random();

        this.color = new Color(r.nextInt( RGB_RANGE ), r.nextInt( RGB_RANGE ), r.nextInt( RGB_RANGE ) );

        this.food = new Ellipse2D.Double( r.nextInt( Display.OUTER_AREA_WIDTH ), r.nextInt( Display.OUTER_AREA_HEIGHT ), PELLETS_DIAMETER, PELLETS_DIAMETER );

    }

    public Food( double x, double y ){

        final Random r = new Random();

        this.color = new Color(r.nextInt( RGB_RANGE ), r.nextInt( RGB_RANGE ), r.nextInt( RGB_RANGE ) );

        this.food = new Ellipse2D.Double( x, y, PELLETS_DIAMETER, PELLETS_DIAMETER );

    }

    public Ellipse2D.Double getFood()               { return this.food; }
    public void setFood( Ellipse2D.Double food )    { this.food = food; }

    public Color getFoodColor()                     { return this.color; }
    public void setFoodColor( Color color )         { this.color = color; }

}