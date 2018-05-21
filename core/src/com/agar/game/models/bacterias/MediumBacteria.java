package com.agar.game.models.bacterias;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MediumBacteria extends Bacteria {
  public MediumBacteria(SpriteBatch batch, ShapeRenderer shapeRenderer, BitmapFont font) {
    super(batch, shapeRenderer, font);

    this.color = Color.BLUE;
  }
}