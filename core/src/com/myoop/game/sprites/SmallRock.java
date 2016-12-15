package com.myoop.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Champ on 12/15/2016.
 */
public class SmallRock implements ISprite {
    public TextureRegion getTexture() {
        return new TextureRegion(smallrock);
    }

    public Texture getNorTex() {
        return smallrock;
    }

    public Vector2 getPos() {
        return posSmallRock;
    }

    private Texture smallrock;
    private Vector2 posSmallRock;
    private Rectangle boundsRock;


    public SmallRock(float x) {
        smallrock = new Texture("smallrock.png");
        posSmallRock = new Vector2(x, 26);
        boundsRock = new Rectangle(posSmallRock.x, posSmallRock.y, smallrock.getWidth() - 15, smallrock.getHeight() - 10);
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(boundsRock);
    }

    public void dispose() {
        smallrock.dispose();
    }

    public void update(float dt) {

    }

    public Rectangle getHitBox() {
        return boundsRock;
    }

}

