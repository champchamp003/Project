package com.myoop.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.myoop.game.OOProject;
import com.myoop.game.sprites.Horse;

/**
 * Created by WaveToMe on 11/11/2016 AD.
 */
public class GameOverState extends State {
    private Texture gameover;
    protected GameOverState(GameStateManager gsm) {
        super(gsm);
        gameover = new Texture("gameover.jpg");
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(gameover,cam.position.x-(cam.viewportWidth / 2),600,600,600);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
