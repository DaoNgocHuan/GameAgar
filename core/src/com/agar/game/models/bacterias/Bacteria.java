package com.agar.game.models.bacterias;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.agar.game.FoodChainObserver;
import com.agar.game.models.DynamicUnit;
import com.agar.game.models.Unit;
import com.agar.game.models.Projectile;
import com.agar.game.models.Gun;

public class Bacteria extends DynamicUnit {
  public Bacteria(SpriteBatch batch, ShapeRenderer shapeRenderer, BitmapFont font) {
    super(batch, shapeRenderer, font, 20);

    this.mass = 20;
    this.radius = 20;
    this.velocity = 1;

    this.glyphLayout = new GlyphLayout();
    this. gun = new Gun(batch, shapeRenderer);
  }

  public void batchRender() {
    this.font.draw(
        batch,
        this.mass + "",
        this.x - this.glyphLayout.width / 2,
        this.y + this.glyphLayout.height / 2
    );
  }

  public boolean canEat(Unit unit) {
    return Math.abs(this.getX() - unit.getX()) < this.getRadius() &&
        Math.abs(this.getY() - unit.getY()) < this.getRadius() &&
        this.isLarger(unit) &&
        FoodChainObserver.canEat(this, unit);
  }

  public boolean isLarger(Unit unit) {
    return this.mass > 1.25 * unit.getMass();
  }

  public void eat(Unit unit) {
    this.mass += unit.getMass();
    radiusInc((float) (0.075 * unit.getMass()));
  }

  public Projectile fireToGun() {
    return this.gun.shot(this.getX(), this.getY());
  }

  public void setGunDirection(float x, float y) {
    this.gun.setDirection(x,y);
  }
  public void toggleGun() {
    this.gun.toggle();
  }

  protected Gun gun;
  private GlyphLayout glyphLayout;
}
