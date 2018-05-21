package com.agar.game.models;

import com.agar.game.models.bacterias.Bacteria;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.awt.*;

public class Projectile extends DynamicUnit {

    public Projectile(SpriteBatch batch, ShapeRenderer shapeRenderer) {

        super(batch, shapeRenderer, null, 1);

        this.mass = 1;
        this.radius = 10;
        this.dirX = 0;
        this.dirY = 0;

        setVelocity(0);
        setColor(Color.BLACK);
    }

    public float getDirX() {
        return this.dirX;
    }
    public float getDirY() {
        return this.dirY;
    }

    public void setPos(float x, float y) {
        this.x = x;
        this.y = y;
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

    public boolean isTouch(Unit unit) {
        return Math.abs(unit.getX() - this.getX()) < (unit.radius + this.radius) &&
                Math.abs(unit.getY() - this.getY()) < (unit.radius + this.radius);
    }

    public void toActOn(Unit unit) { }

    protected float dirX;
    protected float dirY;
}
