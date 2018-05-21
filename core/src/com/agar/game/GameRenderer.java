package com.agar.game;

import com.agar.game.models.Projectile;
import com.agar.game.modules.Module;
import com.agar.game.modules.ModuleEngine;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.agar.game.models.Agar;
import com.agar.game.models.areas.SpecialArea;
import com.agar.game.models.bacterias.Bacteria;

public class GameRenderer {

  public static void init(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
    if (instance == null) {
      instance = new GameRenderer(spriteBatch, shapeRenderer);
    }
  }

  public static void render () {

    if(module != null) {
      module.run();
    }

    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

    for (SpecialArea area : GameWorld.areas) {
      area.shapeRender();
    }

    for (Bacteria bacteria : GameWorld.bots) {
      bacteria.shapeRender();
    }

    for (Agar agar : GameWorld.agars) {
      agar.shapeRender();
    }

    for (Projectile b : GameWorld.balls) {
      b.shapeRender();
    }

    GameWorld.playerBacteria.shapeRender();

    shapeRenderer.end();

    spriteBatch.begin();

    GameWorld.playerBacteria.batchRender();

    for (Bacteria bacteria : GameWorld.bots) {
      bacteria.batchRender();
    }

    spriteBatch.end();
  }

  private static ShapeRenderer shapeRenderer;
  private static SpriteBatch spriteBatch;
  private static GameRenderer instance;
  public static Module module;

  private GameRenderer (SpriteBatch batch, ShapeRenderer renderer) {
    shapeRenderer = renderer;
    spriteBatch = batch;
    module = null;
  }
}
