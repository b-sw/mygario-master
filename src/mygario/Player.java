package mygario;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.Graphics2D;
import java.util.Random;

public class Player {

    public static final int PLAYER_DIAMETER = 25;

    private static final int INIT_VELOCITY = 5;
    private static final int POSITION_RANGE = 500;
    private static final int RGB_RANGE = 255;
    private static final int POSITION_OFFSET = 1500;
    private static final double DELTA_SIZE = 0.9;
    private static final double DELTA_VELOCITY = 0.03;

    private Ellipse2D.Double player;
    private Color playerColor;
    private double velocity = INIT_VELOCITY;
    Random random;

    Player(){

        random = new Random();
        player = new Ellipse2D.Double( random.nextInt( POSITION_RANGE ) + POSITION_OFFSET, random.nextInt( POSITION_RANGE ) + POSITION_OFFSET, PLAYER_DIAMETER, PLAYER_DIAMETER );
        playerColor = new Color( random.nextInt( RGB_RANGE ), random.nextInt( RGB_RANGE ), random.nextInt( RGB_RANGE ) );

    }

    public void drawPlayer( Graphics2D g ){

        g.setColor( playerColor );
        g.fill( player );

    }

    public void increaseSize(){

        player.width += DELTA_SIZE;
        player.height += DELTA_SIZE;
        velocity -= DELTA_VELOCITY;

    }

    public void decreaseSize(){

        player.width -= DELTA_SIZE;
        player.height -= DELTA_SIZE;
        velocity += DELTA_VELOCITY;

    }

    public double   getX()                          { return this.player.x; }
    public void     setX( double x )                { this.player.x = x; };
    public double   getY()                          { return this.player.y; }
    public void     setY( double y )                { this.player.y = y; }
    
    public Ellipse2D.Double getPlayer()             { return this.player; }
    public void setPlayer( Ellipse2D.Double player ){ this.player = player; }

    public double getVelocity()                     { return this.velocity; }
    public void setVelocity( double velocity )      { this.velocity = velocity; }

    public double getRadius()                       { return this.player.width / 2; }

    public double getMidX()                         { return this.player.x + this.player.width / 2; }
    public double getMidY()                         { return this.player.y + this.player.height / 2 ; }

}