package model;

import main.model.Food;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

class FoodTest {

    private Food f;

    @BeforeEach
    void setUp() {

        f = new Food();

    }

    @AfterAll
    static void handleSuccess() {

        System.out.println( "Test food successful" );

    }

    @Test
    void getFood() {

        Food tmpFood = new Food();
        f.setFood( tmpFood.getFood() );

        assertEquals( tmpFood.getFood(), f.getFood() );

    }

    @Test
    void setFood() {

        Food tmpFood = new Food();
        tmpFood.setFood( f.getFood() );

        assertEquals( tmpFood.getFood(), f.getFood() );

    }

    @Test
    void getFoodColor() {

        Food tmpFood = new Food();
        tmpFood.setFoodColor( Color.ORANGE );

        Color c =  tmpFood.getFoodColor();
        assertEquals( Color.ORANGE, c );

    }

    @Test
    void setFoodColor() {

        Food tmpFood = new Food();
        tmpFood.setFoodColor( Color.ORANGE );

        Color c = tmpFood.getFoodColor();
        assertEquals( Color.ORANGE, c );

    }

}