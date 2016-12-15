package com.myoop.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.myoop.game.states.PlayState;

/**
 * Created by WaveToMe on 11/9/2016 AD.
 */
public class Horse {
    private static int GRAVITY = -15;
    public static int MOVEMENT = 150;
    private Animation hourseJumpAnimation;
    private final Animation slashing;
    private Vector3 position;
    private Vector3 velocity;
    private int jumping = 0;
    private Rectangle bounds;
    private Animation hourseAnimation;
    private Animation deadAnimation;
    private Sound HJ;
    private Sound attack;
    public Horse(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        Texture texture = new Texture("HorseAni.png");
        hourseAnimation = new Animation(new TextureRegion(texture), 7, 0.5f, true);
        slashing = new Animation(new TextureRegion(new Texture("slash.png")),6,0.8f);
        bounds = new Rectangle(x, y, texture.getWidth() / 7 - 30, texture.getHeight() -50);
        hourseJumpAnimation = new Animation(new TextureRegion(new Texture("HorseJump.png")), 1, 0.5f);
        deadAnimation = new Animation(new TextureRegion(new Texture("dead.png")), 4, 1f, true);
        HJ = Gdx.audio.newSound(Gdx.files.internal("HJ.mp3"));
        attack = Gdx.audio.newSound(Gdx.files.internal("attack.mp3"));
        MOVEMENT = 150;
    }

    public void update(float dt) {
        hourseAnimation.update(dt);
        slashing.update(dt);
        if (position.y > 22) {
            velocity.add(0, GRAVITY, 0);
        }
        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y, 0);
        if (position.y < 22) {
            position.y = 22;
            jumping = 2;
        }
        velocity.scl(1 / dt);
        bounds.setPosition(position.x, position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getHorse() {
        if(!slashing.isFinish())
            return slashing.getFrames();
        return (jumping == 2) ? hourseAnimation.getFrames() : hourseJumpAnimation.getFrames();
    }

    public void jump() {
        if (jumping > 0) {
            velocity.y = 500;
            jumping--;
        }
        if(jumping == 1){
            HJ.play(0.5f);
        }
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void slash(){
        attack.play();
        slashing.start();
    }

    public void die(){
        position.set(position.x,40,0);
        hourseAnimation = deadAnimation;
        hourseJumpAnimation =deadAnimation;

    }

    public void dispose(){
        HJ.dispose();
        attack.dispose();
    }
}