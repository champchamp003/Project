package com.myoop.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.myoop.game.miniGame.Quiz;

/**
 * Created by Champ on 8/12/2559.
 */
public class Enemy {
    public final int ENEMY_WIDTH = 116;
    public final int ENEMY_HIGHT = 144;
    private Texture enemy = new Texture("enemyAni.png");
    private Animation deadAnimation;
    private Animation stand;
    private Vector2 posEnemy;
    public Quiz quiz = new Quiz();
    public Rectangle hitBox;
    boolean isDead = false;

    public Enemy(float x){
        posEnemy = new Vector2(x,29);
        hitBox = new Rectangle(posEnemy.x,posEnemy.y,ENEMY_WIDTH,ENEMY_HIGHT);
        deadAnimation =new Animation(new TextureRegion(new Texture("dead.png")),4, 0.5f );
        stand = new Animation(new TextureRegion(enemy),6,0.8f);
        isDead = false;
    }

    public boolean collides(Rectangle player){
        return player.overlaps(hitBox);
    }

    public Vector2 getPosEnemy() {
        return posEnemy;
    }

    public TextureRegion getEnemy() {
        if(isDead){
            return deadAnimation.getFrames();
        }
        else {
            return stand.getFrames();
        }
    }

    public void dispose(int n){
       if(n==quiz.getRightChoice()){
           hitBox.setSize(0,0);

           isDead = true;
        }if(n==-1){
            enemy = new Texture("empty.png");
            hitBox.setSize(0,0);
        }
        quiz.dispose(n);

    }

    public void update(float dt){
        deadAnimation.update(dt);
        stand.update(dt);
    }
}
