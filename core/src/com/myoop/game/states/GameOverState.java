package com.myoop.game.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.myoop.game.sprites.Score;

/**
 * Created by WaveToMe on 11/11/2016 AD.
 */
public class GameOverState extends State {
    private Texture background;
    private Texture highSC;
    private Texture yourSC;
    private Music music;
    private int highScore;
    private int myScore;
    private Score myScore0;
    private Score myScore1;
    private Score highScore0;
    private Score highScore1;
    protected GameOverState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("gameover.png");
        cam.setToOrtho(false, 600, 600);
        music = Gdx.audio.newMusic(Gdx.files.internal("endgame.mp3"));
        music.setLooping(true);
        music.setVolume(0.5f);
        music.play();
        myScore = PlayState.getScore();
        highScore = PlayState.getHighScoreFromFile();
        myScore0 = new Score(400,300);
        myScore1 = new Score(380,300);
        highScore0 = new Score(220,300);
        highScore1 = new Score(200,300);
        highSC = new Texture("highscore.png");
        yourSC = new Texture("yourscore.png");
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        myScore0.update(0,myScore%10);
        myScore1.update(0,myScore/10);
        highScore0.update(0,highScore%10);
        highScore1.update(0,highScore/10);
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background,cam.position.x-(cam.viewportWidth / 2),0,600,600);
        sb.draw(myScore0.getScore(),myScore0.getPos().x-295,myScore0.getPos().y-50);
        sb.draw(myScore1.getScore(),myScore1.getPos().x-295,myScore1.getPos().y-50);
        sb.draw(highScore0.getScore(),highScore0.getPos().x-115,highScore0.getPos().y+70);
        sb.draw(highScore1.getScore(),highScore1.getPos().x-115,highScore1.getPos().y+70);
        sb.draw(highSC,highScore0.getPos().x-150,highScore0.getPos().y+110 );
        sb.draw(yourSC,myScore0.getPos().x-330,myScore0.getPos().y-13);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        music.stop();
        music.dispose();
        System.out.println("Play State Disposed.");
    }
}
