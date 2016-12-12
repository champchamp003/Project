package com.myoop.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.myoop.game.miniGame.Quiz;
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
    private static int Obj_SPACE = rand.nextInt(350)+350;
    private static int Obj_COUNT = 4;
    private Array<Rock> rocks;
    private Quiz quiz;

    private boolean spaceAlreadyPressed = false;
    protected PlayState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("BGgif.gif");
        horse = new Horse(15, 200);
        cam.setToOrtho(false, 600, 600);
        quiz = new Quiz();

        rocks = new Array<Rock>();
        for(int i = 1; i <= Obj_COUNT; i++){
            rocks.add(new Rock(i * (Obj_SPACE + Rock.ROCK_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            horse.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        horse.update(dt);
        cam.position.x = horse.getPosition().x + 220;
        for(Rock rock : rocks){
            if(cam.position.x - (cam.viewportWidth / 2) > rock.getPosRock().x + rock.getRock().getWidth()){
                rock.reposition(rock.getPosRock().x + ((Rock.ROCK_WIDTH + Obj_SPACE) * Obj_COUNT));
            }

            if(rock.collides(horse.getBounds())){
                gsm.set(new GameOverState(gsm));
            }
        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background,cam.position.x-(cam.viewportWidth / 2),0,600,600);
        sb.draw(horse.getHorse(),horse.getPosition().x, horse.getPosition().y);
        sb.draw(quiz.getQuiz(),quiz.getPosQuiz().x,quiz.getPosQuiz().y);
        sb.draw(quiz.getAns1(),quiz.getPosAns1().x,quiz.getPosAns1().y);
        sb.draw(quiz.getAns2(),quiz.getPosAns2().x,quiz.getPosAns2().y);
        sb.draw(quiz.getAns3(),quiz.getPosAns3().x,quiz.getPosAns3().y);
        sb.draw(quiz.getAns4(),quiz.getPosAns4().x,quiz.getPosAns4().y);
        for(Rock rock : rocks){
            sb.draw(rock.getRock(), rock.getPosRock().x , rock.getPosRock().y);
        }
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
