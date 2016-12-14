package com.myoop.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Champ on 12/14/2016.
 */
public interface ISprite {
    Texture getTexture();
    Vector2 getPos();
    void reposition(float x);
    boolean collides(Rectangle player);
}
