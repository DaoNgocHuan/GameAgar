package test.java.com.agar.game;

import com.agar.game.FoodChainObserver;
import com.agar.game.ModuleGame;
import com.agar.game.models.DynamicUnit;
import com.agar.game.models.Projectile;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.agar.game.BacteriaFactory;
import com.agar.game.GameWorld;
import com.agar.game.models.Agar;
import com.agar.game.models.Unit;
import com.agar.game.models.bacterias.Bacteria;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ModuleGameTest {

    @Before
    public void initialize() {
        GameWorld.playerBacteria = new Bacteria(null, null, null);

        // Initialize projectiles array
        GameWorld.balls = new ArrayList<Projectile>();

        // Initialize agars array
        GameWorld.agars = new ArrayList<Agar>();

        // Initialize bots array
        GameWorld.bots = new ArrayList<Bacteria>();

        // Fill in units array
        GameWorld.units = new ArrayList<Unit>();
        GameWorld.units.addAll(GameWorld.agars);
        GameWorld.units.addAll(GameWorld.bots);
        GameWorld.units.add(GameWorld.playerBacteria);

        // Fill in dynamic units array
        GameWorld.dynamicUnits = new ArrayList<DynamicUnit>();
        for (Unit u : GameWorld.units) {
            if (u.getClass() != Agar.class && u != GameWorld.playerBacteria) {
                GameWorld.dynamicUnits.add((DynamicUnit) u);
            }
        }

        // Init food chain
        FoodChainObserver.init();
    }

    @Test
    public void goUp(){

        GameWorld.playerBacteria.setPos(30, 50);

        Agar agarUp = new Agar(null, null);
        agarUp.setPos(50, 100);

        GameWorld.agars.add(agarUp);
        GameWorld.units.add(agarUp);

        ModuleGame bot = new ModuleGame();
        bot.load(null);
        bot.run();

        Assert.assertEquals(GameWorld.playerBacteria.getX(), 30, 0.0);
        Assert.assertEquals(GameWorld.playerBacteria.getY(), 51, 0.0);
    }

    @Test
    public void goDown(){

        GameWorld.playerBacteria.setPos(60, 50);

        Agar agarDown = new Agar(null, null);
        agarDown.setPos(50, 0);

        GameWorld.agars.add(agarDown);
        GameWorld.units.add(agarDown);

        ModuleGame bot = new ModuleGame();
        bot.load(null);
        bot.run();

        Assert.assertEquals(GameWorld.playerBacteria.getX(), 60, 0.0);
        Assert.assertEquals(GameWorld.playerBacteria.getY(), 49, 0.0);
    }

    @Test
    public void goLeft(){

        GameWorld.playerBacteria.setPos(50, 50);

        Agar agarLeft = new Agar(null, null);
        agarLeft.setPos(0, 50);

        GameWorld.agars.add(agarLeft);
        GameWorld.units.add(agarLeft);

        ModuleGame bot = new ModuleGame();
        bot.load(null);
        bot.run();

        Assert.assertEquals(GameWorld.playerBacteria.getX(), 49, 0.0);
        Assert.assertEquals(GameWorld.playerBacteria.getY(), 50, 0.0);
    }

    @Test
    public void goRight(){

        GameWorld.playerBacteria.setPos(10, 50);

        Agar agarUp = new Agar(null, null);
        agarUp.setPos(100, 50);

        GameWorld.agars.add(agarUp);
        GameWorld.units.add(agarUp);

        ModuleGame bot = new ModuleGame();
        bot.load(null);
        bot.run();

        Assert.assertEquals(GameWorld.playerBacteria.getX(), 11, 0.0);
        Assert.assertEquals(GameWorld.playerBacteria.getY(), 50, 0.0);
    }
}