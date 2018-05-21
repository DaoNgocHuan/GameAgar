package com.agar.game.models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Unit {
  public Unit (SpriteBatch batch, ShapeRenderer shapeRenderer, int mass) {
    this.batch = batch;
    this.shapeRenderer = shapeRenderer;

    this.x = (float) Math.random() * 500;
    this.y = (float) Math.random() * 500;
    this.mass = mass;

    setup();
  }

  public int getMass () {
    return this.mass;
  }

  public float getRadius () {
    return this.radius;
  }

  public float getX () {
    return this.x;
  }

  public float getY () {
    return this.y;
  }

  public void setRadius(float radius) {
    this.radius = radius;
  }

  public void setMass(int mass) {
    this.mass = mass;
  }

  public void shapeRender() {
    shapeRenderer.setColor(this.color);
    shapeRenderer.circle((float) this.x, (float) this.y, this.radius);
  };

  protected void setRandomColor() {
    this.color = new Color((float) Math.random(), (float) Math.random(), (float)
        Math.random(), 1);
  }

  public void setColor(Color color) {
    this.color = color;
  }

  protected void setup () {
    setRandomColor();
  }

  protected void radiusInc(float n) {
    this.radius += n;
  };

  public void setPos(float x, float y) {
    this.x = x;
    this.y = y;
  }

  protected Color color;
  protected int mass;
  protected float radius;
  protected float x;
  protected float y;

  protected SpriteBatch batch;
  protected ShapeRenderer shapeRenderer;
}
