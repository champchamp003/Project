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
    private Music pick0 = Gdx.audio.newMusic(Gdx.files.internal("mario.mp3"));
    private Music pick1 = Gdx.audio.newMusic(Gdx.files.internal("dif-sin-cos.mp3"));
    private Music pick2 = Gdx.audio.newMusic(Gdx.files.internal("diffe^x.mp3"));
    private Music pick3 = Gdx.audio.newMusic(Gdx.files.internal("difflogx.mp3"));
    private Music pick4 = Gdx.audio.newMusic(Gdx.files.internal("difflogx.mp3"));
    private Music pick5 = Gdx.audio.newMusic(Gdx.files.internal("diffmultiple.mp3"));
    private Music pick6 = Gdx.audio.newMusic(Gdx.files.internal("diffsec.mp3"));
    private Music pick7 = Gdx.audio.newMusic(Gdx.files.internal("difftan.mp3"));
    private Music pick8 = Gdx.audio.newMusic(Gdx.files.internal("integratecosec.mp3"));
    private Music pick9 = Gdx.audio.newMusic(Gdx.files.internal("integratecot.mp3"));
    private Music pick10 = Gdx.audio.newMusic(Gdx.files.internal("integratesec^x.mp3"));
    private Music pick11 = Gdx.audio.newMusic(Gdx.files.internal("integratesectan.mp3"));
    private Music pick12 = Gdx.audio.newMusic(Gdx.files.internal("integratesincos.mp3"));
    private boolean isPick;

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
        isPick = false;
    }


    public boolean collides(Rectangle player) {
        return player.overlaps(hitBoxItem);
    }

    public void pickUp() {
        item = new Texture("empty.png");
        //pick0.play();
        hitBoxItem.setSize(0, 0);
        int rand = (int) (Math.random() * 12 + 1);
        pick1.setVolume(4f);
        pick2.setVolume(4f);
        pick3.setVolume(4f);
        pick4.setVolume(4f);
        pick5.setVolume(4f);
        pick6.setVolume(4f);
        pick7.setVolume(4f);
        pick8.setVolume(4f);
        pick9.setVolume(4f);
        pick10.setVolume(4f);
        pick11.setVolume(4f);
        pick12.setVolume(4f);
        if (!isPick) {
            isPick = true;
            if (rand == 1) {
                pick1.play();
            } else if (rand == 2) {
                pick2.play();
            } else if (rand == 3) {
                pick3.play();
            } else if (rand == 4) {
                pick4.play();
            } else if (rand == 5) {
                pick5.play();
            } else if (rand == 6) {
                pick6.play();
            } else if (rand == 7) {
                pick7.play();
            } else if (rand == 8) {
                pick8.play();
            } else if (rand == 9) {
                pick9.play();
            } else if (rand == 10) {
                pick10.play();
            } else if (rand == 11) {
                pick11.play();
            } else if (rand == 12) {
                pick12.play();
            }
        }

    }

    public void hide() {
        item = new Texture("empty.png");
        hitBoxItem.setSize(0, 0);
    }

    public void dispose() {
        item.dispose();
        pick0.dispose();
        pick1.dispose();
        pick2.dispose();
        pick3.dispose();
        pick4.dispose();
        pick5.dispose();
        pick6.dispose();
        pick7.dispose();
        pick8.dispose();
        pick9.dispose();
        pick10.dispose();
        pick11.dispose();
        pick12.dispose();
    }

}