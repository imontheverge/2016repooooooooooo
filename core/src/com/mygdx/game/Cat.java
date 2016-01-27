package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sun.org.apache.bcel.internal.generic.LoadClass;

/**
 * Created by christian on 6/20/2015.
 */
public class Cat extends Player {
    public Cat()
    {
        LoadCat();
    }

    private void LoadCat()
    {
        sprite = new Sprite();
        playerIdleAnimation = new Animation(0.2f, new TextureRegion(new Texture("catidle1.png")),
                new TextureRegion(new Texture("catidle2.png")),
                new TextureRegion(new Texture("catidle3.png")),
                new TextureRegion(new Texture("catidle4.png")),
                new TextureRegion(new Texture("catidle5.png")),
                new TextureRegion(new Texture("catidle6.png")));
        playerIdleAnimation.setPlayMode(Animation.PlayMode.LOOP); // same for every sprite you have
    }
}
