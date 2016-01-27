package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by christian on 5/29/2015.
 */
public class Mouse extends Player {

    public Mouse()
    {
        LoadMouse();
    }

    private void LoadMouse()
    {
        sprite = new Sprite();
        playerIdleAnimation = new Animation(0.2f, new TextureRegion(new Texture("Mouseidle1.png")),
                new TextureRegion(new Texture("Mouseidle2.png")),
                new TextureRegion(new Texture("Mouseidle3.png")),
                new TextureRegion(new Texture("Mouseidle4.png")),
                new TextureRegion(new Texture("Mouseidle5.png")));
        playerIdleAnimation.setPlayMode(Animation.PlayMode.LOOP); // same for every sprite you have
    }
}
