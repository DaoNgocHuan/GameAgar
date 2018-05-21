package com.agar.game.models;

import com.agar.game.models.bacterias.Bacteria;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Gun {

    public Gun(SpriteBatch batch, ShapeRenderer shapeRenderer) {

        this.dirX = 0;
        this.dirY = 0;
        this.shellVelocity = 3;
        this.iceMode = false;
        this.batch = batch;
        this.shapeRenderer = shapeRenderer;
    }

    public Projectile shot(float gunPosX, float gunPosY) {

        Projectile shell;
        if(this.iceMode)
            shell = new IceBall(batch, shapeRenderer);
        else
            shell = new FireBall(batch, shapeRenderer);
        shell.setPos(gunPosX, gunPosY);
        shell.setDirection(dirX, dirY);
        shell.setVelocity(shellVelocity);
        return shell;
    }

    public void toggle() {
        this.iceMode = !this.iceMode;
    }

    public float getDirX() {
        return this.dirX;
    }
    public float getDirY() {
        return this.dirY;
    }

    public void setDirection(float x, float y) {
        this.dirX = x;
        this.dirY = y;
    }
    public void setDirectionX(float x) {
        this.dirX = x;
    }
    public void setDirectionY(float y) {
        this.dirY = y;
    }

    protected float dirX;
    protected float dirY;
    protected float shellVelocity;
    protected boolean iceMode;
    protected SpriteBatch batch;
    protected ShapeRenderer shapeRenderer;
}
