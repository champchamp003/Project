package com.myoop.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.myoop.game.states.PlayState;

/**
 * Created by Champ on 12/13/2016.
 */
public class Bat {
    public final int BAT_WIDTH = 75;
    public final int BAT_HIGHT = 56;
    private Texture bat = new Texture("bat1.png");
    private Vector2 posBat;
    private Rectangle hitBox;


    public Bat(float x){
        posBat = new Vector2(x,200);
        hitBox = new Rectangle(posBat.x-20,posBat.y-50,BAT_WIDTH*0.75f,BAT_HIGHT*0.75f);
    }

    public void reposition(float x){
        posBat.set(x, 200);
        hitBox.setPosition(posBat);
    }

    public boolean collides(Rectangle player){
        return player.overlaps(hitBox);
    }

    public Vector2 getPos() {
        return posBat;
    }

    public Texture getBat() {
        return bat;
    }

    public Vector2 getPosBat() {
        return posBat;
    }

    public void dispose(){
        bat = new Texture("empty.png");
        hitBox.setSize(0,0);
    }
}
