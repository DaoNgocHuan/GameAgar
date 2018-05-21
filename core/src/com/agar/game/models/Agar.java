package com.agar.game.models;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Agar extends Unit {
  public Agar (SpriteBatch batch, ShapeRenderer shape) {
    super(batch, shape, 5);
    this.radius = 5;
  }
}
