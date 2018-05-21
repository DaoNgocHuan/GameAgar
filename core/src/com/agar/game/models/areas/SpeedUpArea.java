package com.agar.game.models.areas;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.agar.game.models.DynamicUnit;

import java.util.ArrayList;

public class SpeedUpArea extends SpecialArea {
    public SpeedUpArea(ShapeRenderer shapeRenderer, ArrayList<DynamicUnit> units, float x, float y, float radius) {
        super(shapeRenderer, units, x, y, radius);
    }

    @Override
    protected void onEnter(DynamicUnit unit) {
        unit.setVelocity(unit.getVelocity() * 3);
    }

    @Override
    protected void onLeave(DynamicUnit unit) {
        unit.setVelocity(unit.getVelocity() / 3);
    }
}
