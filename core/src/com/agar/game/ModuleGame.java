package com.agar.game;

import java.util.Random;

import com.agar.game.models.Unit;
import com.agar.game.modules.Module;


public class ModuleGame implements Module {
    Random rnd;

    @Override
    public void load(Module batch) {
        GameRenderer.module = batch;
        this.rnd = new Random(System.currentTimeMillis());
    }

    @Override
    public int run() {
        Unit victim = GameWorld.nearestVictimFor(GameWorld.playerBacteria);
        if (victim != null) {
            GameWorld.playerBacteria.moveTo(victim.getX(), victim.getY());
        }
        return 0;
    }


    @Override
    public void unload() {
        System.out.println("unload");
    }

}