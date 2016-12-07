package com.myoop.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by WaveToMe on 11/9/2016 AD.
 */
public class Horse {
    private static int GRAVITY = -15;
    public static int MOVEMENT = 150;
    private Vector3 position;
    private Vector3 velocity;
    private int jumping = 0;
    private Texture horse;
    private Rectangle bounds;

    public Horse(int x, int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0, 0, 0);
        horse = new Texture("horse.gif");
        bounds = new Rectangle(x, y, 40, 40);
    }

    public void update(float dt){
        if(position.y > 7)
            velocity.add(0, GRAVITY, 0);
        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y, 0);
        if(position.y < 7){
            position.y = 7;
            jumping = 2;
        }
        velocity.scl(1 / dt);
        bounds.setPosition(position.x, position.y);

    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getHorse() {
        return horse;
    }

    public void jump(){
        if(jumping > 0) {
            velocity.y = 550;
            jumping--;
        }
    }

    public Rectangle getBounds(){
        return bounds;
    }
}
