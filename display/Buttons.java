package display;

import java.awt.Point;
import game.*;

public class Buttons {

    private static final int BUTTON_WIDTH  = 100;
    private static final int BUTTON_HEIGHT = 50;

    private static final int PLAY_X = Display.WINDOW_WIDTH / 2 - BUTTON_WIDTH / 2;
    private static final int PLAY_Y = Display.WINDOW_HEIGHT / 2;
    private static final int QUIT_X = PLAY_X;
    private static final int QUIT_Y = PLAY_Y + BUTTON_HEIGHT * 2;
    private static final int TEST_X = PLAY_X;
    private static final int TEST_Y = PLAY_Y + BUTTON_HEIGHT * 4;
    private static final int BMOV_X = Display.WINDOW_WIDTH / 2 - BUTTON_WIDTH;
    private static final int BMOV_Y = Display.WINDOW_HEIGHT / 2 - BUTTON_HEIGHT / 2;
    private static final int GOBACK_MENU_X = BMOV_X;
    private static final int GOBACK_MENU_Y = BMOV_Y + BUTTON_HEIGHT * 3;

    private static final int NEW_GAME_WIDTH    = 240;
    private static final int NEW_GAME_HEIGHT   = 60;
    private static final int NEW_GAME_OFFSET   = 50;
    private static final int QUIT_GAME_WIDTH   = 120;
    private static final int QUIT_GAME_HEIGHT  = 30;
    private static final int BUTTON_STR_OFFSET = 40;
    private static final int BMOV_WIDTH        = 170;
    private static final int BMOV_HEIGHT       = 30;

    public Buttons(){}

    public static boolean clickedPlay( int mouseX, int mouseY ){

        if( Game.STATE.MENU == Game.getState() &&
            mouseX >= PLAY_X && mouseX <= PLAY_X + BUTTON_WIDTH &&
            mouseY >= PLAY_Y && mouseY <= PLAY_Y + BUTTON_HEIGHT )

            return true;

        else return false;
        
    }

    public static boolean clickedQuit( int mouseX, int mouseY ){

        if( Game.STATE.MENU == Game.getState() &&
            mouseX >= QUIT_X && mouseX <= QUIT_X + BUTTON_WIDTH &&
            mouseY >= QUIT_Y && mouseY <= QUIT_Y + BUTTON_HEIGHT )

            return true;

        else return false;

    }

    public static boolean clickedTestMenu( int mouseX, int mouseY ){

        if( Game.STATE.MENU == Game.getState() &&
            mouseX >= TEST_X && mouseX <= TEST_X + BUTTON_WIDTH &&
            mouseY >= TEST_Y && mouseY <= TEST_Y + BUTTON_HEIGHT )

            return true;

        else return false;

    }

    public static boolean clickedNewGame( int mouseX, int mouseY, Point mainPlayer ){

        int newGameOffset = ( NEW_GAME_OFFSET - BUTTON_STR_OFFSET ) / 2;

        if( ( Game.STATE.WIN == Game.getState() || Game.STATE.LOSE == Game.getState() ) &&
            mouseX >= mainPlayer.x - NEW_GAME_WIDTH / 2 && mouseX <= mainPlayer.x + NEW_GAME_WIDTH / 2 &&
            mouseY >= mainPlayer.y + newGameOffset && mouseY <= mainPlayer.y + newGameOffset + NEW_GAME_HEIGHT )

            return true;

        else return false;

    }

    public static boolean clickedQuitGame( int mouseX, int mouseY, Point mainPlayer ){

        int quitGameOffset = 2 * NEW_GAME_OFFSET - BUTTON_STR_OFFSET / 2;

        if( ( Game.STATE.WIN == Game.getState() || Game.STATE.LOSE == Game.getState() ) &&
            mouseX >= mainPlayer.x - QUIT_GAME_WIDTH / 2 && mouseX <= mainPlayer.x + QUIT_GAME_WIDTH / 2 &&
            mouseY >= mainPlayer.y + quitGameOffset && mouseY <= mainPlayer.y + quitGameOffset + QUIT_GAME_HEIGHT )

            return true;

        else return false;

    }

    public static boolean clickedTestBMov( int mouseX, int mouseY ){

        if( Game.STATE.TEST_MENU == Game.getState() &&
            mouseX >= BMOV_X && mouseX <= BMOV_X + BMOV_WIDTH &&
            mouseY >= BMOV_Y && mouseY <= BMOV_Y + BMOV_HEIGHT )

            return true;

        else return false;


    }

    public static boolean clickedGoBackMenu( int mouseX, int mouseY ){

        if( Game.STATE.TEST_MENU == Game.getState() &&
            mouseX >= GOBACK_MENU_X && mouseX <= GOBACK_MENU_X + BMOV_WIDTH &&
            mouseY >= GOBACK_MENU_Y && mouseY <= GOBACK_MENU_Y + BMOV_HEIGHT )

            return true;

        else return false;

    }

    public static boolean clickedGoBackTestMenu( int mouseX, int mouseY, Point ref ){

        if( Game.STATE.TEST_SUCCESS == Game.getState() &&
            mouseX >= ref.x - Menu.GO_BACK_TEST_OFFSET_X / 2 && mouseX <= ref.x + Menu.GO_BACK_TEST_OFFSET_X / 2 &&
            mouseY >= ref.y && mouseY <= ref.y + Menu.BUTTON_STR_OFFSET )

            return true;

        else return false;

    }
    
}