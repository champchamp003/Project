package com.myoop.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Champ on 12/14/2016.
 */
public interface ISprite {
    TextureRegion getTexture();
    Vector2 getPos();
    Texture  getNorTex();
    void update(float dt);
    boolean collides(Rectangle player);
}
