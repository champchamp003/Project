package com.myoop.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Champ on 8/12/2559.
 */
public class Enemy {
    public Texture getEnemy() {
        return enemy;
    }

    public Vector2 getPosRock() {
        return posenemy;
    }
    public static final int Enemy_WIDTH = 25;
    private Texture enemy;
    private Vector2 posenemy;
    private Random rand;
    private Rectangle enemyHitBox;

    public Enemy(float x){
        enemy = new Texture("enemy1.png");
        rand = new Random();
        posenemy = new Vector2(x, 32);
        enemyHitBox = new Rectangle(posenemy.x, posenemy.y, 30, 90);
    }

    public void reposition(float x){
        posenemy.set(x, 32);
        enemyHitBox.setPosition(posenemy);
    }

    public boolean collides(Rectangle player){
        return player.overlaps(enemyHitBox);
    }
}
