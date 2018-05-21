package com.agar.game;

import com.agar.game.models.Projectile;
import com.agar.game.modules.ModuleEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.agar.game.controllers.AIController;
import com.agar.game.models.Agar;
import com.agar.game.models.DynamicUnit;
import com.agar.game.models.Unit;
import com.agar.game.models.areas.SpecialArea;
import com.agar.game.models.bacterias.Bacteria;

import java.util.ArrayList;
import java.util.ListIterator;

public class GameController {

  public static void init () {
    if (instance == null) {
      instance = new GameController();
    }
  }

  public static void update () {
    init();
    handleUserInput();
    handleMoving();
    handleEating();
    handleBallsMoving();
    handleBlast();
    handleSpecialAreas();
    handleRespawn();
  }

  private static void handleRespawn() {
    GameWorld.spawnRandom();
  }

  private static GameController instance;
  public static ArrayList<AIController> controllers = new ArrayList<AIController>();

  private GameController () {
    for (DynamicUnit bacteria: GameWorld.dynamicUnits) {
      if(bacteria == GameWorld.playerBacteria) continue;
      controllers.add(new AIController((Bacteria)bacteria));
    }
  }

  /**
   * Обработчик поедания
   */
  private static void handleEating () {
    ArrayList<Bacteria> bacterias = new ArrayList<Bacteria>();
    bacterias.add(GameWorld.playerBacteria);
    bacterias.addAll(GameWorld.bots);

    for (Bacteria b : bacterias) {
      handleEating(b);
    }
  }

  /**
   * Обработчик поедания для отдельной бактерии
   * @param bacteria обрабатываемая бактерия
   */
  private static void handleEating (Bacteria bacteria) {
    for (ListIterator<Unit> itr = GameWorld.units.listIterator(); itr.hasNext();) {
      Unit unit = itr.next();
      if (unit == bacteria) continue;
      if (bacteria.canEat(unit)) {
        bacteria.eat(unit);
        itr.remove();
        if (unit.getClass() == Agar.class) {
          GameWorld.agars.remove(unit);
        }
        else {
          GameWorld.bots.remove(unit);
        }
      }
    }
  }

  /**
   * Обработчик перемещения всех бактерий, кроме бактерии Игрока
   */
  private static void handleMoving() {
    for (AIController controller: controllers) {
      controller.move();
    }
  }

  /**
   * Обработчик движеня снарядов
   */
  private static void handleBallsMoving() {

    for (Projectile ball: GameWorld.balls) {
      ball.moveTo(ball.getX() + ball.getDirX() * 10,ball.getY() + ball.getDirY() * 10);
    }
  }

  /**
   * Обработчик попадания снаряда
   */
  private static void handleBlast() {

    for (ListIterator<Projectile> itr_ball = GameWorld.balls.listIterator(); itr_ball.hasNext();) {
      Projectile ball = itr_ball.next();

      if (ball.getX() + ball.getRadius() < 0
              || ball.getY() + ball.getRadius() < 0
              || ball.getX() + ball.getRadius() > Gdx.graphics.getWidth()
              || ball.getY() + ball.getRadius() > Gdx.graphics.getHeight()) {
        itr_ball.remove();
        continue;
      }

      for (ListIterator<Bacteria> itr_bot = GameWorld.bots.listIterator(); itr_bot.hasNext(); ) {
        Bacteria bot = itr_bot.next();
        if (ball.isTouch(bot)) {
          ball.toActOn(bot);
          ball.setVelocity(0);
          itr_ball.remove();
          continue;
        }
      }
    }
  }

  /**
   * Обработчик специальных областей
   */
  private static void handleSpecialAreas() {
    for (SpecialArea area : GameWorld.areas) {
      area.update();
    }
  }

  /**
   * Обработчик пользовательского ввода
   */
  private static void handleUserInput() {
    if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
      if (GameWorld.playerBacteria.getY() + GameWorld.playerBacteria.getRadius() < Gdx.graphics.getHeight()) {
        GameWorld.playerBacteria.moveTo(
            GameWorld.playerBacteria.getX(),
            GameWorld.playerBacteria.getY() + GameWorld.playerBacteria.getVelocity()
        );
        GameWorld.playerBacteria.setGunDirection(0,1);
      }
    }
    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
      if (GameWorld.playerBacteria.getX() + GameWorld.playerBacteria.getRadius() < Gdx.graphics.getWidth()) {
        GameWorld.playerBacteria.moveTo(
            GameWorld.playerBacteria.getX() + GameWorld.playerBacteria.getVelocity(),
            GameWorld.playerBacteria.getY()
        );
        GameWorld.playerBacteria.setGunDirection(1,0);
      }
    }
    if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
      if (GameWorld.playerBacteria.getY() - GameWorld.playerBacteria.getRadius() > 0) {
        GameWorld.playerBacteria.moveTo(
            GameWorld.playerBacteria.getX(),
            GameWorld.playerBacteria.getY() - GameWorld.playerBacteria.getVelocity()
        );
        GameWorld.playerBacteria.setGunDirection(0,-1);
      }
    }
    if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
      if (GameWorld.playerBacteria.getX() - GameWorld.playerBacteria.getRadius() > 0) {
        GameWorld.playerBacteria.moveTo(
            GameWorld.playerBacteria.getX() - GameWorld.playerBacteria.getVelocity(),
            GameWorld.playerBacteria.getY()
        );
        GameWorld.playerBacteria.setGunDirection(-1,0);
      }
    }
    if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
      GameWorld.playerFire();
    }
    if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
      GameWorld.playerBacteria.toggleGun();
    }
    if (Gdx.input.isKeyJustPressed(Input.Keys.B)) {
      ModuleEngine.main(null);
    }
  }
}
