package com.agar.game.models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class IceBall extends Projectile {

    public IceBall(SpriteBatch batch, ShapeRenderer shapeRenderer) {

        super(batch, shapeRenderer);
        setColor(Color.SKY);
    }

    public void toActOn(Unit unit) {

        unit.setColor(Color.SKY);

        if (unit instanceof DynamicUnit) {
            ((DynamicUnit) unit).setVelocity(0);
        }
    }
}
