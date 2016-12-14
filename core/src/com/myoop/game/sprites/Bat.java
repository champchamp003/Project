package com.myoop.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


/**
 * Created by Champ on 12/13/2016.
 */
public class Bat implements ISprite{
    public static final int BAT_WIDTH = 75;
    public static final int BAT_HIGHT = 56;
    private Texture bat = new Texture("batAni.png");
    private Animation batAni;
    private Vector2 posBat;
    private Rectangle hitBox;


    public Bat(float x){
        posBat = new Vector2(x,200);
        batAni =new Animation(new TextureRegion(bat),5, 0.5f );
        hitBox = new Rectangle(posBat.x-20,posBat.y-50,BAT_WIDTH*0.75f,BAT_HIGHT*0.75f);
    }

    public Texture getNorTex(){
        return new Texture("bat1.png");
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

    public TextureRegion getTexture() {
            return batAni.getFrames();
    }

    public Vector2 getPosBat() {
        return posBat;
    }

    public void dispose(){
    }

    public void update(float dt){
        batAni.update(dt);
    }
}
