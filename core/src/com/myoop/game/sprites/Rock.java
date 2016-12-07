package com.myoop.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by WaveToMe on 11/10/2016 AD.
 */
public class Rock {
    public Texture getRock() {
        return rock;
    }

    public Vector2 getPosRock() {
        return posRock;
    }
    public static final int ROCK_WIDTH = 30;
    private Texture rock;
    private Vector2 posRock;
    private Random rand;
    private Rectangle boundsRock;


    public Rock(float x){
        rock = new Texture("rock.png");
        rand = new Random();
        posRock = new Vector2(x, 32);
        boundsRock = new Rectangle(posRock.x, posRock.y, 40, 130);
    }

    public void reposition(float x){
        posRock.set(x, 32);
        boundsRock.setPosition(posRock);
    }

    public boolean collides(Rectangle player){
        return player.overlaps(boundsRock);
    }
}
