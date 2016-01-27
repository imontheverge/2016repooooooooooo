package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by christian on 5/29/2015.
 */
public class Rabbit extends Player {

    public Rabbit()
    {
        LoadRabbit();
    }

    private void LoadRabbit()
    {
        sprite = new Sprite();
        playerIdleAnimation = new Animation(0.2f, new TextureRegion(new Texture("Rabbitidle1.png")),
                new TextureRegion(new Texture("Rabbitidle2.png")),
                new TextureRegion(new Texture("Rabbitidle3.png")),
                new TextureRegion(new Texture("Rabbitidle4.png")),
                new TextureRegion(new Texture("Rabbitidle5.png")),
                new TextureRegion(new Texture("Rabbitidle6.png")),
                new TextureRegion(new Texture("Rabbitidle7.png")));
        playerIdleAnimation.setPlayMode(Animation.PlayMode.LOOP);
    }
}
