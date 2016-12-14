package com.myoop.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by WaveToMe on 12/12/2016 AD.
 */
public class Animation {
    private boolean loop;
    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int frame;

    public Animation(TextureRegion region, int frameCount, float cycleTime){
        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth() / frameCount;
        for(int i = 0; i < frameCount; i++){
            frames.add(new TextureRegion(region, i*frameWidth, 0, frameWidth, region.getRegionHeight()));
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        currentFrameTime = 0;
        frame = frameCount;
        loop = false;
    }

    public Animation(TextureRegion region, int frameCount, float cycleTime, boolean loop){
        this(region, frameCount, cycleTime);
        this.loop = loop;
    }

    public void update(float dt){
        currentFrameTime += dt;
        frame = (int) (currentFrameTime / maxFrameTime);
        if(loop) {
            frame =  frame % frameCount;
        }
    }

    public TextureRegion getFrames(){
        return frames.get(Math.min(frameCount-1, frame));
    }

    public void start() {
        currentFrameTime = 0;
    }

    public boolean isFinish() {
        return loop || frame >= frameCount;
    }

}