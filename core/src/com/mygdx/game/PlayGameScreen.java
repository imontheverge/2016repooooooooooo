package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by christian on 5/29/2015.
 * **/

    public class PlayGameScreen implements Screen {

     // FOR DEBUG/////////
     ////////////////*/
        private ShapeRenderer shapeRenderer;
    ///////////////
    ///////////////
        Enemy enemy;

        final MyGdxGame game;

        private float animationTime;

        Texture background;
        Texture floor;

        Texture[] cloudArray;
        ArrayList<Cloud> cloudsOnScreen;
        int maxClouds;
        int random;
        Random randomNumber;

        private OrthographicCamera camera;
        private OrthographicCamera guiCamera;
        private Viewport viewport;

        float velocityResetTimer = 10f;
        float previousVelSwipe;
        float swipeVelocity = 5f;

        public PlayGameScreen (final MyGdxGame gam) {

            this.game = gam;
            camera = new OrthographicCamera();
            camera.setToOrtho(false, 480, 800);
            viewport = new FitViewport(480, 800, camera);
            guiCamera = new OrthographicCamera();
            guiCamera.setToOrtho(false, 480, 800);
            animationTime = 0.0f;

            background = new Texture("background.png");
            floor = new Texture("floor background.png");

            maxClouds = 4;
            cloudsOnScreen = new ArrayList<Cloud>();
            cloudArray = new Texture[4];
            cloudArray[0] = new Texture("cloud1.png");
            cloudArray[1] = new Texture("cloud2.png");
            cloudArray[2] = new Texture("cloud3.png");
            cloudArray[3] = new Texture("cloud4.png");
            randomNumber = new Random();
            random = randomNumber.nextInt(4);
            enemy = new Enemy();

            cloudsOnScreen.add(new Cloud(new Vector2(-200, -200), cloudArray[randomNumber.nextInt(4)], randomNumber.nextInt(10) / 1));
            cloudsOnScreen.add(new Cloud(new Vector2(-50, 250), cloudArray[randomNumber.nextInt(4)], randomNumber.nextInt(10) / 1));
            cloudsOnScreen.add(new Cloud(new Vector2(150, -100),cloudArray[randomNumber.nextInt(4)], randomNumber.nextInt(10) / 1));
            cloudsOnScreen.add(new Cloud(new Vector2(90, 90), cloudArray[randomNumber.nextInt(4)], randomNumber.nextInt(10) / 1));
            cloudsOnScreen.add(new Cloud(new Vector2(90, 90), cloudArray[randomNumber.nextInt(4)], randomNumber.nextInt(10) / 1));

            Gdx.input.setInputProcessor(new SimpleDirectionGestureDetector(new SimpleDirectionGestureDetector.DirectionListener(){
                @Override
                public void onUp()
                {

                }
                @Override
                public void onDown()
                {

                }
                @Override
                public void onLeft()
                {

                }
                @Override
                public void onRight()
                {
                    if(velocityResetTimer > 0.1f)
                    {
                        previousVelSwipe = swipeVelocity;
                        swipeVelocity = previousVelSwipe / 2;
                        game.player.velocity.y += swipeVelocity;
                    }
                }
            }));

            ///////////////////////////
            shapeRenderer = new ShapeRenderer();
            //////////////////////////
        }

        @Override
        public void render (float delta) {
            Gdx.gl.glClearColor(5 / 255f, 159 / 255f, 233 / 255f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            //this gets confusing. if you dont have seperate methods for drawing and updating the render method
            //turns into a mess. always draw before you update.
            drawScene();
            updateScene();
        }

        private void drawScene()
        {
            camera.position.set(game.player.position.x + 25, game.player.position.y, 0);
            camera.update();
            game.batch.setProjectionMatrix(camera.combined);
            game.batch.begin();

            for(int i = 0; i < cloudsOnScreen.size(); i++)
            {
                if(!camera.frustum.pointInFrustum(cloudsOnScreen.get(i).position.x, cloudsOnScreen.get(i).position.y, 0)) {
                    cloudsOnScreen.get(i).position.y += 800;
                    cloudsOnScreen.get(i).position.x = randomNumber.nextInt(350) - 200;
                    cloudsOnScreen.get(i).sprite.setTexture(cloudArray[randomNumber.nextInt(4)]);//texture = cloudArray[randomNumber.nextInt(4)];

                }
                //cloudsOnScreen.get(i).sprite.draw(game.batch);
                    game.font.draw(game.batch, "" + i, game.player.position.x + 20 * i, game.player.position.y);
                game.font.draw(game.batch, cloudsOnScreen.get(i).sprite.getScaleX() + "", cloudsOnScreen.get(i).position.x, cloudsOnScreen.get(i).position.y);
                game.batch.draw(cloudsOnScreen.get(i).sprite.getTexture(), cloudsOnScreen.get(i).position.x, cloudsOnScreen.get(i).position.y);
            }
            //game.font.draw(game.batch, game.player.position.toString(), game.player.position.x, game.player.position.y);

            game.player.DrawPlayer(game.batch, animationTime);
            enemy.updateEnemy(game.player.position, game.batch);

            game.batch.end();

            /////////////////////
            shapeRenderer.setProjectionMatrix(camera.combined);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.rect(game.player.position.x, game.player.position.y, 100, 100);
            shapeRenderer.end();
            /////////////////////

            game.guiBatch.setProjectionMatrix(guiCamera.combined);
            game.guiBatch.begin();
            game.font.draw(game.guiBatch, "Velocity: " + game.player.velocity, 0, 200);
            game.font.draw(game.guiBatch, "Timer: " + velocityResetTimer, 0, 250);
            game.font.draw(game.guiBatch, "PrevVelocitySwipe: " + previousVelSwipe, 0, 300);
            game.font.draw(game.guiBatch, "CurrentVelocitySwipe: " + swipeVelocity, 0, 350);
            game.font.draw(game.guiBatch, "cloud count:" + game.player.position.x, 0, 400);
            game.font.draw(game.guiBatch, "HEIGHT: " + game.player.position.y / 10, 300, 750);
            game.guiBatch.end();
        }

        private void updateScene()
        {
            float deltaTime = Gdx.graphics.getDeltaTime(); //just getting milliseconds or whatever libgdx uses
            animationTime += deltaTime; //update the animation.

            game.player.velocity.y -= 0.01f;
            if(game.player.velocity.y <= game.maxGravity.y)
            {
                game.player.velocity.y = game.maxGravity.y;
            }

            game.player.UpdatePlayer(animationTime);

            velocityResetTimer -= Gdx.graphics.getDeltaTime();
            if(velocityResetTimer <= 0)
            {
                velocityResetTimer = 5;
                swipeVelocity = 5;
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