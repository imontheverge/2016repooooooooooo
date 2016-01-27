package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.Rectangle;
import java.util.Random;
import java.util.Timer;

public class MyGdxGame extends Game {

	SpriteBatch batch;
	SpriteBatch guiBatch;
	BitmapFont font;
	Player player;
	float startPosX;
	float startPosY;
	Vector2 maxGravity = new Vector2(0, -2);
	Vector2 maxUpwardVelocity = new Vector2(0, 2);
	Rectangle screenRect;

	public void create()
	{
		batch = new SpriteBatch();
		guiBatch = new SpriteBatch();
		font = new BitmapFont();
		this.setScreen(new PickPlayerScreen(this));

		startPosX = Gdx.graphics.getWidth() / 2;
		startPosY = Gdx.graphics.getHeight() / 2;

	}

	public void render()
	{
		super.render();
	}

	public void dispose()
	{
		batch.dispose();
		font.dispose();
	}
}
