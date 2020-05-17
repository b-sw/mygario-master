package game;

import assets.*;

public class Game {
    
    private static final int NUM_OF_PELLETS = 1000;

    private Player mainPlayer;
    private Player botPlayer;
    private Pellets pellets;

    public static enum STATE{

        MENU,
        GAME,
        WIN,
        LOSE,
        TEST

    };

    public static STATE state = STATE.MENU;

    public Game() {

        mainPlayer  = new Player();
        botPlayer   = new Player();
        pellets     = new Pellets(NUM_OF_PELLETS);

    }

    public void handleWinLose() {

        double x1 = mainPlayer.getMidX();
        double y1 = mainPlayer.getMidY();
        double x2 = botPlayer.getMidX();
        double y2 = botPlayer.getMidY();

        double distance = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));

        if (distance < mainPlayer.getRadius() && mainPlayer.getRadius() > botPlayer.getRadius()) {

            state = STATE.WIN;

        } else if (distance < botPlayer.getRadius() && mainPlayer.getRadius() < botPlayer.getRadius()) {

            state = STATE.LOSE;

        }

    }

    public Player getMainPlayer()                   { return this.mainPlayer; }
    public void setMainPlayer( Player player )      { this.mainPlayer = player; }

    public Player getBotPlayer()                    { return this.botPlayer; }
    public void setBotPlayer( Player bot )          { this.botPlayer = bot; }

    public Pellets getPellets()                     { return this.pellets; }
    public void setPellets( Pellets pellets )       { this.pellets = pellets; }

    public static STATE getState()                  { return state; }
    public static void setState( STATE newState )   { state = newState; }

}