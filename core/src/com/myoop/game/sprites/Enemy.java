package com.myoop.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.myoop.game.miniGame.Quiz;

/**
 * Created by Champ on 8/12/2559.
 */
public class Enemy {
    public final int ENEMY_WIDTH = 500;
    public final int ENEMY_HIGHT = 259;
    private Texture enemy = new Texture("enemy1.png");
    private Vector2 posEnemy;
    public Quiz quiz = new Quiz();
    private Rectangle hitBox;

    public Enemy(float x){
        posEnemy = new Vector2(x,0);
        hitBox = new Rectangle(posEnemy.x+150,posEnemy.y,ENEMY_WIDTH/3,ENEMY_HIGHT*0.7f);
    }

    public void reposition(float x){
        posEnemy.set(x, 0);
        quiz = new Quiz();
        hitBox.setPosition(posEnemy);
    }

    public boolean collides(Rectangle player){
        return player.overlaps(hitBox);
    }

    public Vector2 getPosEnemy() {
        return posEnemy;
    }

    public Texture getEnemy() {
        return enemy;
    }

    public void dispose(){
        enemy = new Texture("empty.png");
        quiz.dispose();
        hitBox.setSize(0,0);
    }
}
