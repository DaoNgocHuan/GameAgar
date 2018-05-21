package com.agar.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.agar.game.AgarGame;
import com.agar.game.GameController;
import com.agar.game.GameRenderer;
import com.agar.game.GameWorld;

public class MainScreen implements Screen {

  public MainScreen (AgarGame game) {
    this.game = game;
    int camHeight = 500;
    int camWidth = 500;
    this.camera = new OrthographicCamera(camWidth, camHeight * Gdx.graphics
        .getHeight() / Gdx.graphics.getWidth());
    this.camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f,
        0);
  }

  @Override
  public void show() {

  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(1, 1, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    this.camera.update();

    if (GameWorld.playerBacteria == null) {
      this.game.setScreen(new StartScreen(this.game));
      dispose();
    } else {
      GameController.update();
      GameRenderer.render();
    }
  }

  @Override
  public void resize(int width, int height) {

  }

  @Override
  public void pause() {

  }

  @Override
  public void resume() {

  }

  @Override
  public void hide() {

  }

  @Override
  public void dispose() {

  }

  private OrthographicCamera camera;
  private AgarGame game;
}
