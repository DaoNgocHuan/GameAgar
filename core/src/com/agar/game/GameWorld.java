package com.agar.game;

import com.agar.game.models.Projectile;
import com.agar.game.modules.Module;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import com.agar.game.models.Agar;
import com.agar.game.models.DynamicUnit;
import com.agar.game.models.Unit;
import com.agar.game.models.areas.SlowDownArea;
import com.agar.game.models.areas.SpecialArea;
import com.agar.game.models.areas.SpeedUpArea;
import com.agar.game.models.bacterias.Bacteria;
import com.agar.game.models.Gun;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Класс, описывающий игровой мир
 */
public class GameWorld {
  /**
   * Список корма
   */
  public static ArrayList<Agar> agars;
  /**
   * Список подвижных юнитов
   */
  public static ArrayList<DynamicUnit> dynamicUnits;
  /**
   * Список всех бактерий, кроме бактерии Игрока
   */
  public static ArrayList<Bacteria> bots;
  /**
   * Список всех юнитов (корм и бактерии)
   */
  public static ArrayList<Unit> units;
  /**
   * Бактерия Игрока
   */
  public static Bacteria playerBacteria;
  /**
   * Список специальных областей
   */
  public static ArrayList<SpecialArea> areas;

  /**
   * Снаряды игры
   */
  public static ArrayList<Projectile> balls;

  /**
   * @return Объект игрового мира
   */
  public static void init (SpriteBatch batch, ShapeRenderer shapeRenderer) {
    if (instance == null) {
      instance = new GameWorld(batch, shapeRenderer);
    }
  }

  public static Unit nearestVictimFor(Bacteria bacteria) {
    Unit victim = null;
    for (Unit u: units) {
      if (u == bacteria || u == GameWorld.playerBacteria) continue;
      if (bacteria.isLarger(u) && FoodChainObserver.canEat(bacteria, u)) {
        if (victim == null) {
          victim = u;
        } else {
          if (Math.abs(u.getX() - bacteria.getX()) < Math.abs(victim.getX() - bacteria.getX()) &&
              Math.abs(u.getY() - bacteria.getY()) < Math.abs(victim.getY() - bacteria.getY())) {
            victim = u;
          }
        }
      }
    }
    return victim;
  }

  public static void playerFire() {
    balls.add(playerBacteria.fireToGun());
  }

  public static void spawnRandom() {
    if (new Random().nextInt(10) == 7) {
      agars.add(new Agar(spriteBatch, shapeRenderer));
      units = new ArrayList<Unit>();
      units.addAll(agars);
      units.addAll(bots);
    }
  }

  public static SpriteBatch spriteBatch;
  public static ShapeRenderer shapeRenderer;
  private static GameWorld instance;

  private GameWorld(SpriteBatch batch, ShapeRenderer renderer) {
    spriteBatch = batch;
    shapeRenderer = renderer;

    playerBacteria = new Bacteria(batch, shapeRenderer, new BitmapFont());
    playerBacteria.setVelocity(5);

    balls = new ArrayList<Projectile>();

    // Initialize agars array
    agars = new ArrayList<Agar>();
    for (int i = 0; i < 100; ++i) {
      agars.add(new Agar(batch, shapeRenderer));
    }

    // Initialize bots array
    bots = new ArrayList<Bacteria>();
    for (int i = 0; i < 10; i++) {
      bots.add(BacteriaFactory.bacteria(batch, shapeRenderer, new BitmapFont()));
    }

    units = new ArrayList<Unit>();
    units.addAll(agars);
    units.addAll(bots);
    units.add(playerBacteria);

    dynamicUnits = new ArrayList<DynamicUnit>();
    for (Unit u : units) {
      if (u.getClass() != Agar.class) {
        dynamicUnits.add((DynamicUnit) u);
      }
    }

    areas = new ArrayList<SpecialArea>();
    areas.add(new SlowDownArea(shapeRenderer, dynamicUnits, 150, 250, 100));
    areas.add(new SpeedUpArea(shapeRenderer, dynamicUnits, 450, 150, 100));
  }
}
