package com.agar.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.agar.game.views.StartScreen;


/**
 * Главный класс игры
 */
public class AgarGame extends Game {
	@Override
	public void create() {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		font = new BitmapFont();

		GameWorld.init(batch, shapeRenderer);
		GameRenderer.init(batch, shapeRenderer);
		FoodChainObserver.init();

		this.setScreen(new StartScreen(this));
	}

	@Override
	public void dispose() {
		batch.dispose();
		shapeRenderer.dispose();
		font.dispose();
	}

	@Override
	public void render() {
		super.render();
	}

	public SpriteBatch batch;
	public BitmapFont font;
	private ShapeRenderer shapeRenderer;
}
