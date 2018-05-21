package com.agar.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.agar.game.models.bacterias.Bacteria;
import com.agar.game.models.bacterias.MediumBacteria;
import com.agar.game.models.bacterias.StrongBacteria;
import com.agar.game.models.bacterias.WeakBacteria;

import java.util.Random;

public class BacteriaFactory {
  public static Bacteria bacteria (SpriteBatch batch, ShapeRenderer shapeRenderer, BitmapFont bitmapFont) {
    Random rand = new Random();

    int i = rand.nextInt(3);

    switch (i) {
      case 0:
        return new WeakBacteria(batch, shapeRenderer, bitmapFont);
      case 1:
        return new MediumBacteria(batch, shapeRenderer, bitmapFont);
      case 2:
        return new StrongBacteria(batch, shapeRenderer, bitmapFont);
      default:
        return new MediumBacteria(batch, shapeRenderer, bitmapFont);
    }
  }
}
