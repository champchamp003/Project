package com.myoop.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.myoop.game.miniGame.Quiz;

/**
 * Created by Champ on 8/12/2559.
 */
public class Enemy {
    public final int ENEMY_WIDTH = 500;
    private final Texture enemy = new Texture("enemy1.png");
    private Vector2 posEnemy;
    public Quiz quiz = new Quiz();

    public Enemy(float x){
        posEnemy = new Vector2(x,0);
    }

    public void reposition(float x){
        posEnemy.set(x, 0);
        quiz = new Quiz();
    }

    public Vector2 getPosEnemy() {
        return posEnemy;
    }

    public Texture getEnemy() {
        return enemy;
    }
}
