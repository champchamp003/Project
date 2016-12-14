package com.myoop.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by WaveToMe on 12/14/2016 AD.
 */
public class Score {
    private Vector3 position;
    private Texture score;

    public Score(int x,int y){
        position = new Vector3(x, y, 0);
        score = new Texture("num0.png");
    }


    public Vector3 getPos(){
        return position;
    }

    public Texture getScore(){
        return score;
    }

    public void update(float dt, int sc){
        position.add(150 * dt,0, 0);
        if(sc == 0) score = new Texture("num0.png");
        if(sc == 1) score = new Texture("num1.png");
        if(sc == 2) score = new Texture("num2.png");
        if(sc == 3) score = new Texture("num3.png");
        if(sc == 4) score = new Texture("num4.png");
        if(sc == 5) score = new Texture("num5.png");
        if(sc == 6) score = new Texture("num6.png");
        if(sc == 7) score = new Texture("num7.png");
        if(sc == 8) score = new Texture("num8.png");
        if(sc == 9) score = new Texture("num9.png");

    }

    public void dispose(){
        score.dispose();
    }
}