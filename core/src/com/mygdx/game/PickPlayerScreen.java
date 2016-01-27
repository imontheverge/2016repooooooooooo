package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import javafx.scene.PerspectiveCamera;

/**
 * Created by christian on 5/29/2015.
 */
public class PickPlayerScreen implements Screen {
    MyGdxGame game;

    OrthographicCamera camera;

    Texture RabbitSelect;
    Texture MouseSelect;
    Texture CatSelect;

    Rectangle CatRect;
    Rectangle RabbitRect;
    Rectangle MouseRect;

    Viewport viewport;

    private ShapeRenderer shapeRenderer;

    public PickPlayerScreen(MyGdxGame game)
    {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 800);
        viewport = new FitViewport(480, 800, camera);
        RabbitSelect = new Texture("rabbitselect.png");
        MouseSelect = new Texture("Mouseselect.png");
        CatSelect = new Texture("catselect.png");

        RabbitRect = new Rectangle(100, 200, RabbitSelect.getWidth(), RabbitSelect.getHeight());
        MouseRect = new Rectangle(1, 250, MouseSelect.getWidth(), MouseSelect.getHeight());
        CatRect = new Rectangle(400, 150, CatSelect.getWidth(), CatSelect.getHeight());

        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(1, 0, 0, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Tap a character", 20, 20);
        game.batch.draw(RabbitSelect, RabbitRect.getX(), RabbitRect.getY());
        game.batch.draw(MouseSelect, MouseRect.getX(), MouseRect.getY());
        game.batch.draw(CatSelect, CatRect.getX(), CatRect.getY());
        game.batch.end();

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(CatRect.x, CatRect.y, CatRect.width, CatRect.height);
        shapeRenderer.rect(MouseRect.x, MouseRect.y, MouseRect.width, MouseRect.height);
        shapeRenderer.rect(RabbitRect.x, RabbitRect.y, RabbitRect.width, RabbitRect.height);
        shapeRenderer.end();

        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            game.setScreen(new PlayGameScreen(game));
            dispose();
        }

        Vector3 tempCoords = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(tempCoords);

        if(RabbitRect.contains(tempCoords.x, tempCoords.y)){
            game.setScreen(new PlayGameScreen(game));
            game.player = new Rabbit();
            game.player.sprite = new Sprite();
            game.player.position = new Vector2();
            game.player.velocity = new Vector2();
            //game.player.sprite.setPosition();position.x = game.startPosX;
            //game.player.position.y = game.startPosY;
            game.player.sprite.setPosition(game.startPosX, game.startPosY);
        }

        if(MouseRect.contains(tempCoords.x, tempCoords.y)){
            game.setScreen(new PlayGameScreen(game));
            game.player = new Mouse();
            game.player.position = new Vector2();
            game.player.velocity = new Vector2();
            //game.player.position.x= game.startPosX;
            //game.player.position.y = game.startPosY;
            game.player.sprite.setPosition(game.startPosX, game.startPosY);
        }

        if(CatRect.contains(tempCoords.x, tempCoords.y)){
            game.setScreen(new PlayGameScreen(game));
            game.player = new Cat();
            game.player.position = new Vector2();
            game.player.velocity = new Vector2();
            //game.player.position.x= game.startPosX;
            //game.player.position.y = game.startPosY;
            game.player.sprite.setPosition(game.startPosX, game.startPosY);
        }
    }

    @Override
    public void resize(int width, int height)
    {
        viewport.update(width, height);
    }

    @Override
    public void show()
    {
        //any music here
    }

    @Override
    public void hide()
    {

    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void dispose()
    {

    }
}
