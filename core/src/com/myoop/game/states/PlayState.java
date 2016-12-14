package com.myoop.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.myoop.game.sprites.*;
import com.myoop.game.miniGame.Quiz;

import java.util.Random;

/**
 * Created by WaveToMe on 11/9/2016 AD.
 */
public class PlayState extends State {
    private Horse horse;
    private Texture background;
    private static Random rand = new Random();
    //private int random = (int) (Math.random() * 1000 + 200);
    private static int Obj_SPACE = rand.nextInt(350) + 350;
    private static int Obj_COUNT = 4;
    //private Array<Rock> rocks;
    private Rock rock;
    private Bat bat;
    private Enemy enemy;
    private boolean endInput = false;
    public boolean canJump = true;
    private Array<ISprite> obj = new Array<ISprite>();

    private boolean spaceAlreadyPressed = false;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("BGgif.gif");
        horse = new Horse(15, 200);
        cam.setToOrtho(false, 600, 600);

        enemy = new Enemy(1000);

        //bat = new Bat(0);
        //rock = new Rock(250);
        //enemy.dispose();
        //bat.dispose();
        //rock.dispose();
        //randomObj();
//        bat = new Bat();
//        rock = new Rock();
        randomObj(3);
//        for (int i = 1; i <= Obj_COUNT; i++) {
//            rocks.add(new Rock(i * (Obj_SPACE + Rock.ROCK_WIDTH)));
//        }
    }

    private void randomObj(int n) {
        for (int i = 0; i < n; i++) {
            int rand = (int) (Math.random() * 5 + 1);
            if (rand == 1 || rand == 2 || rand == 3) {
                obj.add(new Rock(horse.getPosition().x+(obj.size+1)*(Obj_SPACE + Rock.ROCK_WIDTH+150)));
                //rock = new Rock(horse.getPosition().x+ (random = (int) (Math.random() * 1000 + 200)));
            } else if (rand == 4 || rand == 5) {
                obj.add(new Bat(horse.getPosition().x+(obj.size+1)*(Obj_SPACE + Bat.BAT_WIDTH+150)));
                //bat = new Bat(horse.getPosition().x+ (random = (int) (Math.random() * 1000 + 200)));
//            } else if (rand == 9 || rand == 10) {
//                obj.add(new Enemy((obj.size+1)*(Obj_SPACE + Enemy.ENEMY_WIDTH)));
//                //enemy = new Enemy(enemy.getPosEnemy().x + (enemy.ENEMY_WIDTH + (random = (int) (Math.random() * 1000 + 200) + 700)));
//                quiz.reposition(horse.getPosition().x);
            }
        }
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            horse.jump();
        }
        if (cam.position.x > enemy.getPosEnemy().x && !endInput) {
            canJump = false;
            if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
                endInput = true;
                enemy.quiz.choose(1);
                canJump = true;
                enemy.dispose(1);
                System.out.println("GG");

            } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
                endInput = true;
                enemy.quiz.choose(2);
                canJump = true;
                enemy.dispose(2);
                System.out.println("GG");

            } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
                endInput = true;
                enemy.quiz.choose(3);
                canJump = true;
                enemy.dispose(3);
                System.out.println("GG");
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)) {
                endInput = true;
                enemy.quiz.choose(4);
                canJump = true;
                enemy.dispose(4);
                System.out.println("GG");

            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        horse.update(dt);
        enemy.quiz.update(dt);
        cam.position.x = horse.getPosition().x + 220;
        for (ISprite subObj : obj) {
            if (cam.position.x - (cam.viewportWidth / 2) > subObj.getPos().x + subObj.getTexture().getWidth()) {
                endInput = false;
                obj.removeIndex(0);
                System.out.println("pop");
                randomObj(1);
                System.out.println("Array" + obj.size);
                //subObj.reposition(subObj.getPos().x + ((subObj.getTexture().getWidth() + Obj_SPACE) * Obj_COUNT));
            }

            if (subObj.collides(horse.getBounds())) {
                gsm.set(new GameOverState(gsm));
            }
        }

        if (enemy.collides(horse.getBounds())) {
            gsm.set(new GameOverState(gsm));

        }
        if (cam.position.x - (cam.viewportWidth / 2) > enemy.getPosEnemy().x + enemy.ENEMY_WIDTH/2) {
            endInput = false;
            enemy.quiz.dispose(0);
            enemy = new Enemy(enemy.getPosEnemy().x + (enemy.ENEMY_WIDTH + Obj_SPACE +500));
            enemy.quiz.reposition(horse.getPosition().x);
        }
        if (obj.get(0).collides(enemy.hitBox)||obj.get(1).collides(enemy.hitBox)) {
            enemy.dispose(-1);
            System.out.println("remove enemy");
        }
//            if((obj.get(0).getPos().x - enemy.getPosEnemy().x + (enemy.ENEMY_WIDTH + 700))  < -500 || obj.get(0).getPos().x - (enemy.getPosEnemy().x + (enemy.ENEMY_WIDTH + 700))  > 500){

//            }


//        if (cam.position.x - (cam.viewportWidth / 2) > obj.first().getPos().x +150) {
//            endInput = false;
//            obj.pop();
//            System.out.println("pop");
//            randomObj(1);
//        }

//        if (cam.position.x - (cam.viewportWidth / 2) > bat.getPosBat().x +500) {
//            endInput = false;
//            randomObj();
//        }
//
//        if (bat.collides(horse.getBounds())) {
//            gsm.set(new GameOverState(gsm));
//        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, cam.position.x - (cam.viewportWidth / 2), 0, 600, 600);
        sb.draw(horse.getHorse(), horse.getPosition().x, horse.getPosition().y);
        sb.draw(enemy.getEnemy(), enemy.getPosEnemy().x, enemy.getPosEnemy().y);
//        sb.draw(bat.getBat(), bat.getPosBat().x, bat.getPosBat().y);
//        sb.draw(rock.getRock(),rock.getPosRock().x,rock.getPosRock().y);
        if (cam.position.x > enemy.getPosEnemy().x) {
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
