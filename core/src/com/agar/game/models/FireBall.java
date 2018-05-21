package com.agar.game.models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class FireBall extends Projectile {

    public FireBall(SpriteBatch batch, ShapeRenderer shapeRenderer) {

        super(batch, shapeRenderer);
        setColor(Color.ORANGE);
    }

    public void toActOn(Unit unit) {

        unit.setRadius((float) (unit.getRadius() * 0.75));
        unit.setMass((int) (unit.getMass() * 0.75));
    }
}
