package com.myoop.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.myoop.game.sprites.Bat;
import com.myoop.game.sprites.Enemy;
import com.myoop.game.sprites.Horse;
import com.myoop.game.sprites.Rock;

import java.util.Random;

/**
 * Created by WaveToMe on 11/9/2016 AD.
 */
public class PlayState extends State {
    private Horse horse;
    private Texture background;
    private static Random rand = new Random();
    private int random = (int) (Math.random() * 1000 + 200);
    private static int Obj_SPACE = rand.nextInt(350) + 500;
    private static int Obj_COUNT = 4;
    private Array<Rock> rocks;
    //private Rock rock;
   // private Bat bat;
    private Enemy enemy;
    private boolean endInput = false;
    public boolean canJump = true;
   // private Array<obj> obj = {rock,bat,enemy};

    private boolean spaceAlreadyPressed = false;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("BGgif.gif");
        horse = new Horse(15, 200);
        cam.setToOrtho(false, 600, 600);

        enemy = new Enemy(500);
        //bat = new Bat(0);
        //rock = new Rock(250);
        //enemy.dispose();
        //bat.dispose();
        //rock.dispose();
        //randomObj();
//        bat = new Bat();
//        rock = new Rock();
//        for (int i = 1; i <= Obj_COUNT; i++) {
//            rocks.add(new Rock(i * (Obj_SPACE + Rock.ROCK_WIDTH)));
//        }
    }

//    private void randomObj(){
//        int rand = (int) (Math.random() * 100 + 1);
//        if(rand<=50){
//            rock = new Rock(horse.getPosition().x+ (random = (int) (Math.random() * 1000 + 200)));
//        }else if(rand>50&&rand<=80){
//            bat = new Bat(horse.getPosition().x+ (random = (int) (Math.random() * 1000 + 200)));
//        }else {
//            enemy = new Enemy(enemy.getPosEnemy().x + (enemy.ENEMY_WIDTH + (random = (int) (Math.random() * 1000 + 200) + 700)));
//            enemy.quiz.reposition(horse.getPosition().x);
//        }
//    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && canJump && !enemy.getEnemy().equals("empty.png")) {
            horse.jump();
        }
        if (cam.position.x > enemy.getPosEnemy().x && !endInput) {
            canJump = false;
            if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
                endInput = true;
                if (enemy.quiz.choose(1)) {
                    canJump = true;
                    enemy.dispose();
                    System.out.println("GG");
                }
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
                endInput = true;
                if (enemy.quiz.choose(2)) {
                    canJump = true;
                    enemy.dispose();
                    System.out.println("GG");
                }
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
                endInput = true;
                if (enemy.quiz.choose(3)) {
                    canJump = true;
                    enemy.dispose();
                    System.out.println("GG");
                }
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
                endInput = true;
                if (enemy.quiz.choose(4)) {
                    canJump = true;
                    enemy.dispose();
                    System.out.println("GG");
                }
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        horse.update(dt);
        enemy.quiz.update(dt);
        cam.position.x = horse.getPosition().x + 220;
//        for (Rock rock : rocks) {
//            if (cam.position.x - (cam.viewportWidth / 2) > rock.getPosRock().x + rock.getRock().getWidth()) {
//                rock.reposition(rock.getPosRock().x + ((Rock.ROCK_WIDTH + Obj_SPACE) * Obj_COUNT));
//            }
//
//            if (rock.collides(horse.getBounds())) {
//                gsm.set(new GameOverState(gsm));
//            }
//        }
//        if (rock.collides(horse.getBounds())) {
//                gsm.set(new GameOverState(gsm));
//            }
        if (enemy.collides(horse.getBounds())) {
            gsm.set(new GameOverState(gsm));

        }
        if (cam.position.x - (cam.viewportWidth / 2) > enemy.getPosEnemy().x+500) {
            endInput = false;
            enemy = new Enemy(enemy.getPosEnemy().x + (enemy.ENEMY_WIDTH + (random = (int) (Math.random() * 700 + 300) )));
            enemy.quiz.reposition(horse.getPosition().x);
            //randomObj();
        }

//        if (cam.position.x - (cam.viewportWidth / 2) > rock.getPosRock().x +500) {
//            endInput = false;
//            randomObj();
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

//        for (Rock rock : rocks) {
//            sb.draw(rock.getRock(), rock.getPosRock().x, rock.getPosRock().y);
//        }
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
