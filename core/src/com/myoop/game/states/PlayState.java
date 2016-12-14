package com.myoop.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    private Animation BGG;
    private boolean canJump = true;
    private boolean isHeroDead = false;
    private Array<ISprite> obj = new Array<ISprite>();
    private Array<Item> items = new Array<Item>();
    private Music music;
    private static int scorer;
    private Score score0;
    private Score score1;
    private Texture ScorePic;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("newBG.png");
        BGG = new Animation(new TextureRegion(background), 8 ,15f,true);

        music = Gdx.audio.newMusic(Gdx.files.internal("Music.mp3"));
        music.setLooping(true);
        music.setVolume(0.5f);
        music.play();
        horse = new Horse(15, 200);
        score0 = new Score(500,550);
        score1= new Score(480,550);
        ScorePic = new Texture("SCORE.png");
        cam.setToOrtho(false, 600, 600);
        enemy = new Enemy(1000);
        randomItem(2);
        randomObj(3);
        scorer = 0;
    }

    private void randomItem(int n) {
        for (int i = 0; i < n; i++) {
            int randY = (int) (Math.random() * 320 + 35);
            int randX = (int) (Math.random() * 3000 + 1500);
            items.add(new Item(horse.getPosition().x + (items.size + 1) * randX, randY));
        }
    }

    private void randomObj(int n) {
        for (int i = 0; i < n; i++) {
            int rand = (int) (Math.random() * 10 + 1);
            if (rand < 8) {
                obj.add(new Rock(horse.getPosition().x + (obj.size + 1) * (Obj_SPACE + Rock.ROCK_WIDTH + 150)));
            } else if (rand >= 8) {
                obj.add(new Bat(horse.getPosition().x + (obj.size + 1) * (Obj_SPACE + Bat.BAT_WIDTH + 150)));
            }
        }
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && canJump) {
            horse.jump();
        }
        if (cam.position.x > enemy.getPosEnemy().x - enemy.ENEMY_WIDTH - 150 && !endInput) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
                endInput = true;
                horse.slash();
                enemy.dispose(1);
                scorer++;
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
                endInput = true;
                horse.slash();
                enemy.dispose(2);
                scorer++;
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
                endInput = true;
                horse.slash();
                enemy.dispose(3);
                scorer++;
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)) {
                endInput = true;
                horse.slash();
                enemy.dispose(4);
                scorer++;
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        setHighScoreFromFile((double)scorer);
        horse.update(dt);
        score0.update(dt,scorer%10);
        score1.update(dt,scorer/10);
        enemy.update(dt);
        BGG.update(dt);
        enemy.quiz.update(dt);
        cam.position.x = horse.getPosition().x + 220;

        for (ISprite subObj : obj) {
            subObj.update(dt);
            if (cam.position.x - (cam.viewportWidth / 2) > subObj.getPos().x + subObj.getNorTex().getWidth()) {
                obj.removeIndex(0);
                randomObj(1);
            }

            if (subObj.collides(horse.getBounds())) {
                gsm.set(new GameOverState(gsm));
            }

        }

        for (Item subItem : items) {

            if (subItem.collides(horse.getBounds())) {
                subItem.pickUp();
            }

            if (obj.get(0).collides(subItem.hitBoxItem)||obj.get(1).collides(subItem.hitBoxItem)||obj.get(2).collides(subItem.hitBoxItem)) {
                subItem.hide();
            }

            if (cam.position.x - (cam.viewportWidth / 2) > subItem.getPosItem().x + subItem.getItem().getWidth()+250) {
                items.removeIndex(0);
                randomItem(1);
            }


        }
        if (obj.get(0).collides(enemy.hitBox)||obj.get(1).collides(enemy.hitBox)||obj.get(2).collides(enemy.hitBox)) {
            enemy.dispose(-1);
        }

        if (enemy.collidesSlash(horse.getBounds()) && !enemy.isHit) {
            isHeroDead = true;
            endInput = true;
            canJump = false;
            enemy.slash();

        }
        if (isHeroDead) {
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

        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(BGG.getFrames(), cam.position.x - (cam.viewportWidth / 2), 0, 600, 600);
        sb.draw(horse.getHorse(), horse.getPosition().x, horse.getPosition().y);
        sb.draw(enemy.getEnemy(), enemy.getPosEnemy().x, enemy.getPosEnemy().y);
        sb.draw(score0.getScore(),score0.getPos().x,score0.getPos().y);
        sb.draw(score1.getScore(),score1.getPos().x,score1.getPos().y);
        sb.draw(ScorePic,score1.getPos().x-80,score1.getPos().y);
        if (cam.position.x >= enemy.getPosEnemy().x - enemy.ENEMY_WIDTH -150 ) {
            sb.draw(enemy.quiz.getQuiz(), enemy.quiz.getPosQuiz().x, enemy.quiz.getPosQuiz().y);

            sb.draw(enemy.quiz.getAns1(), enemy.quiz.getPosAns1().x, enemy.quiz.getPosAns1().y);
            sb.draw(enemy.quiz.getAns2(), enemy.quiz.getPosAns2().x, enemy.quiz.getPosAns2().y);
            sb.draw(enemy.quiz.getAns3(), enemy.quiz.getPosAns3().x, enemy.quiz.getPosAns3().y);
            sb.draw(enemy.quiz.getAns4(), enemy.quiz.getPosAns4().x, enemy.quiz.getPosAns4().y);

            sb.draw(enemy.quiz.getCh1(), enemy.quiz.getPosCh1().x, enemy.quiz.getPosCh1().y);
            sb.draw(enemy.quiz.getCh2(), enemy.quiz.getPosCh2().x, enemy.quiz.getPosCh2().y);
            sb.draw(enemy.quiz.getCh3(), enemy.quiz.getPosCh3().x, enemy.quiz.getPosCh3().y);
            sb.draw(enemy.quiz.getCh4(), enemy.quiz.getPosCh4().x, enemy.quiz.getPosCh4().y);
        }

        for (ISprite subObj : obj) {
            sb.draw(subObj.getTexture(), subObj.getPos().x, subObj.getPos().y);
        }

        for (Item subItem : items) {
            sb.draw(subItem.getItem(), subItem.getPosItem().x, subItem.getPosItem().y);
        }
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        music.stop();
        music.dispose();
        horse.dispose();
    }

    public static int getScore(){
        return scorer;
    }

    public static int getHighScoreFromFile()
    {
        FileHandle scoreFile = Gdx.files.local("data/high_score.txt");
        String text = scoreFile.readString();
        int highScore = Integer.parseInt(text);
        return highScore;
    }

    public void setHighScoreFromFile(double newScore)
    {
        if(scorer > getHighScoreFromFile()) {
            int integerScore = (int) newScore;
            FileHandle scoreFile = Gdx.files.local("data/high_score.txt");
            String score = Integer.toString(integerScore);
            scoreFile.writeString(score, false);
        }
    }
}
