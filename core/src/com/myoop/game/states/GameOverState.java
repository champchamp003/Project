package com.myoop.game.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by WaveToMe on 11/11/2016 AD.
 */
public class GameOverState extends State {
    private Texture background;

    protected GameOverState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("gameover.jpg");
        cam.setToOrtho(false, 600, 600);
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
        cam.position.x = 0;
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background,cam.position.x-(cam.viewportWidth / 2),0,600,600);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        System.out.println("Play State Disposed.");
    }
}
