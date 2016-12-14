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
    public int ENEMY_WIDTH = 116;
    public int ENEMY_HIGHT = 144;
    private Texture enemy = new Texture("enemyAni.png");
    private Animation deadAnimation;
    private Animation stand;
    public Animation slashing;
    private Vector2 posEnemy;
    private Rectangle enemySlashHitBox;
    public Quiz quiz = new Quiz();
    public Rectangle hitBox;
    public boolean isHit = false;
    boolean isDead = false;
    boolean hide = false;

    public Enemy(float x) {
        posEnemy = new Vector2(x, 29);
        hitBox = new Rectangle(posEnemy.x + 50, posEnemy.y, (ENEMY_WIDTH + 60), (ENEMY_HIGHT + 500));
        enemySlashHitBox = new Rectangle((posEnemy.x - 60), posEnemy.y, ENEMY_WIDTH, (ENEMY_HIGHT + 500));
        deadAnimation = new Animation(new TextureRegion(new Texture("dead.png")), 4, 1f, true);
        slashing = new Animation(new TextureRegion(new Texture("enemySlash.png")), 7, 1.15f);
        stand = new Animation(new TextureRegion(enemy), 6, 0.8f, true);
    }

    public boolean collides(Rectangle player) {
        return (player.overlaps(hitBox) && !isDead);
    }

    public boolean collidesSlash(Rectangle player) {
        return (player.overlaps(enemySlashHitBox) && !isDead);
    }


    public Vector2 getPosEnemy() {
        return posEnemy;
    }

    public TextureRegion getEnemy() {
        if (!slashing.isFinish()) {
            return slashing.getFrames();
        } else if (isDead) {
            return deadAnimation.getFrames();
        } else if (hide) {
            return new TextureRegion(new Texture("empty.png"));
        } else {
            return stand.getFrames();
        }
    }

    public void dispose(int n) {
        if (n == quiz.getRightChoice()) {
            hitBox.setSize(0, 0);
            enemySlashHitBox.setSize(0, 0);
            posEnemy.set(posEnemy.x, posEnemy.y + 20);
            isDead = true;
        }
        if (n == -1) {
            hide = true;
            enemy = new Texture("empty.png");
            hitBox = new Rectangle(0, 0, 0, 0);
            enemySlashHitBox = new Rectangle(0, 0, 0, 0);
        }
        quiz.dispose(n);

    }

    public void update(float dt) {
        stand.update(dt);
        slashing.update(dt);
        deadAnimation.update(dt);
        if (slashing.isFinish() && isHit) {
            isHit = false;
            posEnemy.set(posEnemy.x + 145, posEnemy.y + 32);
        }
    }

    public void slash() {
        isHit = true;
        slashing.start();
        posEnemy.set(posEnemy.x - 145, posEnemy.y - 32);
    }

    public void dispose() {
        enemy.dispose();
    }
}
