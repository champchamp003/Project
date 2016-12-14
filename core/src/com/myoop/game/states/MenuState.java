package com.myoop.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.myoop.game.OOProject;

/**
 * Created by WaveToMe on 11/9/2016 AD.
 */
public class MenuState extends State {
    private Texture background;
    private Texture playBtn;
    private Music music;
    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("tutorial.jpg");
        playBtn = new Texture("play.png");
        music = Gdx.audio.newMusic(Gdx.files.internal("Music.mp3"));
        music.setLooping(true);
        music.setVolume(0.5f);
        music.play();
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background,0,0, OOProject.WIDTH, OOProject.HEIGHT);
        sb.draw(playBtn, (OOProject.WIDTH/2)+230, OOProject.HEIGHT/2 - 270,125,125);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        music.stop();
        music.dispose();
    }
}
