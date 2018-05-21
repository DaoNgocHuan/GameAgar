package com.agar.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.agar.game.AgarGame;

public class StartScreen implements Screen {

  public StartScreen (AgarGame game) {
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
    Gdx.gl.glClearColor(0.5f, 0.5f, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    this.camera.update();

    this.game.batch.begin();
    this.game.font.draw(game .batch, "click to start", camera.viewportWidth / 2, camera.viewportHeight / 2);
    this.game.batch.end();

    if (Gdx.input.isTouched()) {
      this.game.setScreen(new MainScreen(this.game));
      dispose();
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

  private SpriteBatch batch;
  private BitmapFont font;
  private AgarGame game;
  private OrthographicCamera camera;
}
