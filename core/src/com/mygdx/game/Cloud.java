package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by roo on 12/5/15.
 */
public class Cloud {
    Vector2 position;
    Texture texture;
    Sprite sprite;

    public Cloud(Vector2 pos, Texture text, float size)
    {
        position = pos;
        texture = text;
        sprite = new Sprite(texture);
        sprite.setScale(size);
    }
}
