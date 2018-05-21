package com.agar.game.models.areas;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.agar.game.models.DynamicUnit;

import java.util.ArrayList;

public abstract class SpecialArea {
    public SpecialArea(ShapeRenderer shapeRenderer, ArrayList<DynamicUnit> units, float x, float y, float radius) {
        this.shapeRenderer = shapeRenderer;
        this.units = units;
        this.x = x;
        this.y = y;
        this.radius = radius;

        this.setRandomColor();
    }

    public void shapeRender() {
        this.shapeRenderer.setColor(this.color);
        this.shapeRenderer.circle(this.x, this.y, this.radius);
    }

    public void update() {
        ArrayList<DynamicUnit> oldInner = new ArrayList<DynamicUnit>(innerUnits);

        for (DynamicUnit u : this.units) {
            if (Math.abs(u.getX() - this.x) <= Math.max(this.radius, u.getRadius()) &&
                Math.abs(u.getY() - this.y) <= Math.max(this.radius, u.getRadius())) {
                innerUnits.add(u);
                if (!oldInner.contains(u)) {
                    this.onEnter(u);
                }
            } else {
                innerUnits.remove(u);
            }
        }

        for (DynamicUnit u : oldInner) {
            if (!innerUnits.contains(u)) {
                this.onLeave(u);
            }
        }
    }

    protected abstract void onEnter(DynamicUnit unit);

    protected abstract void onLeave(DynamicUnit unit);

    private void setRandomColor() {
        this.color = new Color((float) Math.random(), (float) Math.random(), (float)
                Math.random(), 0.5f);
    }

    protected float x;
    protected float y;
    protected float radius;

    protected ArrayList<DynamicUnit> innerUnits = new ArrayList<DynamicUnit>();
    protected ArrayList<DynamicUnit> units;

    protected Color color;
    protected ShapeRenderer shapeRenderer;
}
