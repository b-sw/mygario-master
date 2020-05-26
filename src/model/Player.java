package model;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import java.awt.Point;

public class Player {

    public static final int     PLAYER_DIAMETER     = 25;
    public static final int     WINDOW_WIDTH        = 800;
    public static final int     WINDOW_HEIGHT       = 600;
    
    private static final int    INIT_VELOCITY       = 5;
    private static final int    POSITION_RANGE      = 500;
    private static final int    RGB_RANGE           = 255;
    private static final int    POSITION_OFFSET     = 1500;
    private static final double DELTA_SIZE          = 0.9;
    private static final double DELTA_VELOCITY      = 0.03;
    private static final int    INITIAL_PELLET_DIST = 1000;

    private Ellipse2D.Double player;
    private Color playerColor;

    private double velocity = INIT_VELOCITY;
    
    Random random;

    public Player(){

        random = new Random();
        
        player = new Ellipse2D.Double( random.nextInt( POSITION_RANGE ) + POSITION_OFFSET, random.nextInt( POSITION_RANGE ) + POSITION_OFFSET, PLAYER_DIAMETER, PLAYER_DIAMETER );
        playerColor = new Color( random.nextInt( RGB_RANGE ), random.nextInt( RGB_RANGE ), random.nextInt( RGB_RANGE ) );

    }

    public Player( double x, double y ){

        random = new Random();
        
        player = new Ellipse2D.Double( x , y, PLAYER_DIAMETER, PLAYER_DIAMETER );
        playerColor = new Color( random.nextInt( RGB_RANGE ), random.nextInt( RGB_RANGE ), random.nextInt( RGB_RANGE ) );

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

    private void move( double angle ){

        this.setX( this.getX() + (int) ( this.getVelocity() * Math.cos( angle ) ) );
        this.setY( this.getY() + (int) ( this.getVelocity() * Math.sin( angle ) ) );

    }

    public boolean overlays( Player player ){

        double x1 = this.getMidX();
        double y1 = this.getMidY();
        double x2 = player.getMidX();
        double y2 = player.getMidY();

        double distance = Math.sqrt( (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) );

        if( distance < this.getRadius() && this.getRadius() > player.getRadius() ){

            return true;

        }
        else return false;

    }

    public void moveManually( Point mousePosition ){

        if( null == mousePosition ) return;

        double deltaX = mousePosition.x - this.getPlayer().getCenterX();
        double deltaY = mousePosition.y - this.getPlayer().getCenterY();

        if( Player.PLAYER_DIAMETER / 2 < Math.sqrt( deltaX * deltaX  +  deltaY * deltaY ) ){

            double angle = Math.atan2( deltaY, deltaX );

            this.move( angle );
            
        }

    }

    public void moveAutomatically( Player mainPlayer, Pellets pellets ) {

        double angle = 0;

        if (this.getRadius() <= mainPlayer.getRadius()) {

            angle = this.handlePelletForBot( pellets );

        } else if (this.getRadius() > mainPlayer.getRadius()) {

            angle = this.handleAngleToPlayer( mainPlayer );

        }

        this.move(angle);

    }

    private double handlePelletForBot( Pellets pellets ) { /* returns angle */

        double angle = 0;
        double dist = INITIAL_PELLET_DIST;

        for (int i = 0; i < pellets.getPellets().length; ++i) {

            if (null != pellets.getPellets()[i]) {

                double deltaX = pellets.getPellets()[i].getFood().getCenterX() - this.getMidX();
                double deltaY = pellets.getPellets()[i].getFood().getCenterY() - this.getMidY();

                if (Math.sqrt(deltaX * deltaX + deltaY * deltaY) < dist) {

                    dist = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
                    angle = Math.atan2(deltaY, deltaX);

                }

            }

        }

        return angle;

    }

    private double handleAngleToPlayer( Player player ){

        double deltaX = player.getMidX() - this.getMidX();
        double deltaY = player.getMidY() - this.getMidY();

        return Math.atan2(deltaY, deltaX);

    }

    public void handleEaten( Pellets pellets ) {

        for (int i = 0; i < pellets.getPellets().length; ++i) {

            if (null != pellets.getPellets()[i] && this.getPlayer().intersects( pellets.getPellets()[i].getFood().getBounds2D() ) ) {

                pellets.eatPellet( i );
                this.increaseSize();

            }

        }

    }

    public double   getX()                          { return this.player.x; }
    public void     setX( double x )                { this.player.x = x; }
    public double   getY()                          { return this.player.y; }
    public void     setY( double y )                { this.player.y = y; }
    
    public Ellipse2D.Double getPlayer()             { return this.player; }
    public void setPlayer( Ellipse2D.Double player ){ this.player = player; }

    public double getVelocity()                     { return this.velocity; }
    public void setVelocity( double velocity )      { this.velocity = velocity; }

    public double getRadius()                       { return this.player.width / 2; }

    public double getMidX()                         { return this.player.getCenterX(); }
    public double getMidY()                         { return this.player.getCenterY(); }

    public Color getColor()                         { return this.playerColor; }

}