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
        score = new Texture("TestNumber.jpg");
    }


    public Vector3 getPos(){
        return position;
    }

    public Texture getScore(){
        return score;
    }

    public void dispose(){

    }
}