package com.myoop.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by WaveToMe on 11/10/2016 AD.
 */
public class Rock implements ISprite{
    public TextureRegion getTexture() {
        return new TextureRegion(rock);
    }
    public Texture getNorTex(){
        return rock;}
    public Vector2 getPos() {
        return posRock;
    }
    public static final int ROCK_WIDTH = 35;
    private Texture rock;
    private Vector2 posRock;
    private Rectangle boundsRock;


    public Rock(float x){
        rock = new Texture("rock.png");
        posRock = new Vector2(x, 32);
        boundsRock = new Rectangle(posRock.x, posRock.y, rock.getWidth()-10,rock.getHeight()-30);
    }

    public void reposition(float x){
        posRock.set(x, 32);
        boundsRock.setPosition(posRock);
    }

    public boolean collides(Rectangle player){
        return player.overlaps(boundsRock);
    }

    public void dispose(){
        rock.dispose();
    }

    public void update(float dt){

    }

    public Rectangle getHitBox() {
        return boundsRock;
    }

}
