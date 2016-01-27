package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by roo on 1/14/2016.
 */
public class Enemy {

    private float spawnTimeReset = 3;
    private float spawnTime;
    private Texture enemyTexture;
    private Vector2 enemyPos;
    public boolean enemyAlive;

    public Enemy(){
        spawnTime = 0;
        enemyPos = new Vector2(0, 0);
    }

    public void updateEnemy(Vector2 playerPos, SpriteBatch batch){
        float deltaTime = Gdx.graphics.getDeltaTime();
        spawnTime += deltaTime;

        if(spawnTime >= spawnTimeReset){
            spawnEnemy(new Vector2(playerPos.x - 600, playerPos.y));
            spawnTime = 0;
        }

        if(enemyAlive){
            enemyPos.x += 10;
            drawEnemy(batch);

            if(enemyPos.x >= Gdx.graphics.getWidth() + enemyTexture.getWidth() + 20){
                enemyAlive = false;
            }
        }
    }

    private void spawnEnemy(Vector2 playerPos){
        enemyTexture = new Texture("enemybird.png");
        enemyPos.y = playerPos.y;
        enemyPos.x = -100;
        enemyAlive = true;
    }

    public void drawEnemy(SpriteBatch batch){
        batch.draw(enemyTexture, enemyPos.x, enemyPos.y );
    }
}
