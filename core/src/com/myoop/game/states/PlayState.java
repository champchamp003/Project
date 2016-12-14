package com.myoop.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.myoop.game.sprites.*;

import java.util.Random;

/**
 * Created by WaveToMe on 11/9/2016 AD.
 */
public class PlayState extends State {
    private Horse horse;
    private Texture background;
    private static Random rand = new Random();
    private static int Obj_SPACE = rand.nextInt(350) + 350;
    private Enemy enemy;
    private boolean endInput = false;
    private boolean canJump = true;
    private boolean  isHeroDead = false;
    private Array<ISprite> obj = new Array<ISprite>();

    private boolean spaceAlreadyPressed = false;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("BGgif.gif");
        horse = new Horse(15, 200);
        cam.setToOrtho(false, 600, 600);

        enemy = new Enemy(1000);
        randomObj(3);
    }

    private void randomObj(int n) {
        for (int i = 0; i < n; i++) {
            int rand = (int) (Math.random() * 7 + 1);
            if (rand < 7) {
                obj.add(new Rock(horse.getPosition().x + (obj.size + 1) * (Obj_SPACE + Rock.ROCK_WIDTH + 150)));
            } else if (rand == 7) {
                obj.add(new Bat(horse.getPosition().x + (obj.size + 1) * (Obj_SPACE + Bat.BAT_WIDTH + 150)));
            }
        }
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)&&canJump) {
            horse.jump();
        }
        if (cam.position.x > enemy.getPosEnemy().x - enemy.ENEMY_WIDTH - 150 && !endInput) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
                endInput = true;
                horse.slash();
                enemy.dispose(1);
                System.out.println("GG");

            } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
                endInput = true;
                horse.slash();
                enemy.dispose(2);
                System.out.println("GG");

            } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
                endInput = true;
                horse.slash();
                enemy.dispose(3);
                System.out.println("GG");
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)) {
                endInput = true;
                horse.slash();
                enemy.dispose(4);
                System.out.println("GG");

            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        horse.update(dt);
        enemy.update(dt);
        enemy.quiz.update(dt);
        cam.position.x = horse.getPosition().x + 220;
        for (ISprite subObj : obj) {
            subObj.update(dt);
            if (cam.position.x - (cam.viewportWidth / 2) > subObj.getPos().x + subObj.getNorTex().getWidth()) {
                obj.removeIndex(0);
                System.out.println("pop");
                randomObj(1);
                System.out.println("Array" + obj.size);
            }

            if (subObj.collides(horse.getBounds())) {
                gsm.set(new GameOverState(gsm));
            }
        }

        if (enemy.collidesSlash(horse.getBounds()) && !enemy.isHit) {
            isHeroDead = true;
            endInput = true;
            canJump = false;
            enemy.slash();

        }
        if(isHeroDead){
            horse.die();
        }

        if (enemy.collides(horse.getBounds())) {
            gsm.set(new GameOverState(gsm));
        }


        if (cam.position.x - (cam.viewportWidth / 2) > enemy.getPosEnemy().x + enemy.ENEMY_WIDTH / 2) {
            endInput = false;
            enemy.quiz.dispose(0);
            enemy = new Enemy(enemy.getPosEnemy().x + (enemy.ENEMY_WIDTH + Obj_SPACE + 500));
            enemy.quiz.reposition(horse.getPosition().x);
        }
        if (obj.get(0).collides(enemy.hitBox) || obj.get(1).collides(enemy.hitBox)) {
            enemy.dispose(-1);
            System.out.println("remove enemy");
        }

        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, cam.position.x - (cam.viewportWidth / 2), 0, 600, 600);
        sb.draw(horse.getHorse(), horse.getPosition().x, horse.getPosition().y);
        sb.draw(enemy.getEnemy(), enemy.getPosEnemy().x, enemy.getPosEnemy().y);
        if (cam.position.x > enemy.getPosEnemy().x - enemy.ENEMY_WIDTH - 150) {
            sb.draw(enemy.quiz.getQuiz(), enemy.quiz.getPosQuiz().x, enemy.quiz.getPosQuiz().y);
            sb.draw(enemy.quiz.getAns1(), enemy.quiz.getPosAns1().x, enemy.quiz.getPosAns1().y);
            sb.draw(enemy.quiz.getAns2(), enemy.quiz.getPosAns2().x, enemy.quiz.getPosAns2().y);
            sb.draw(enemy.quiz.getAns3(), enemy.quiz.getPosAns3().x, enemy.quiz.getPosAns3().y);
            sb.draw(enemy.quiz.getAns4(), enemy.quiz.getPosAns4().x, enemy.quiz.getPosAns4().y);
        }

        for (ISprite subObj : obj) {
            sb.draw(subObj.getTexture(), subObj.getPos().x, subObj.getPos().y);
        }
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
