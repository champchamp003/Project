package com.myoop.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Champ on 12/14/2016.
 */
public class Item {
    private int ITEM_WIDTH = 17;
    private int ITEM_HIGHT = 38;
    private Texture item;
    private Vector2 posItem;
    public Rectangle hitBoxItem;
    private Music pick;

    public Texture getItem() {
        return item;
    }

    public Vector2 getPosItem() {
        return posItem;
    }

    public Item(float x, float y) {
        item = new Texture("item.png");
        posItem = new Vector2(x, y);
        hitBoxItem = new Rectangle(posItem.x, posItem.y, ITEM_WIDTH, ITEM_HIGHT);
        pick = Gdx.audio.newMusic(Gdx.files.internal("mario.mp3"));
    }


    public boolean collides(Rectangle player) {
        return player.overlaps(hitBoxItem);
    }

    public void pickUp() {
        item = new Texture("empty.png");
        pick.play();
    }

    public void hide() {
        item = new Texture("empty.png");
        hitBoxItem.setSize(0, 0);
    }

    public void dispose() {
        item.dispose();
        pick.dispose();
    }

}