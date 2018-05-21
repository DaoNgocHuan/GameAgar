package com.agar.game.modules;

import com.agar.game.GameRenderer;
import com.agar.game.GameWorld;

public interface Module {

    public static final int EXIT_SUCCESS = 0;
    public static final int EXIT_FAILURE = 1;

//    public void load(GameRenderer gr, GameWorld gw, Module batch);
    public void load(Module batch);
    public int run();
    public void unload();
}