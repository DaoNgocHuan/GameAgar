package com.agar.game.models;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class DynamicUnit  extends Unit {
  public DynamicUnit(SpriteBatch batch, ShapeRenderer shapeRenderer,
                     BitmapFont font, int mass) {
    super(batch, shapeRenderer, mass);
    this.font = font;
  }

  public void moveTo(float x, float y) {
    if (this.x - x < 0) {
      this.x += this.velocity;
    } else if (this.x - x > 0) {
      this.x -= this.velocity;
    }

    if (this.y - y < 0) {
      this.y += this.velocity;
    } else if (this.y - y > 0) {
      this.y -= this.velocity;
    }
  }

  public float getVelocity () {
    return this.velocity;
  }

  protected float velocity;
  protected BitmapFont font;

  public void setVelocity(float velocity) {
    this.velocity = velocity;
  }
}
