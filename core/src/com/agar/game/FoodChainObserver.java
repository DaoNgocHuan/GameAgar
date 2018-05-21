package com.agar.game;

import com.agar.game.models.Unit;
import com.agar.game.models.bacterias.Bacteria;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Класс для обработки правил поедания бактериями друг друга
 */
public class FoodChainObserver {

  public static void init () {
    if (instance == null) {
      instance = new FoodChainObserver();
    }
  }

  private static HashMap<String, ArrayList<String>> chain;
  private static FoodChainObserver instance;

  private FoodChainObserver() {
    chain = new HashMap<String, ArrayList<String>>();

    chain.put("Bacteria", new ArrayList<String>());
    chain.get("Bacteria").add("StrongBacteria");
    chain.get("Bacteria").add("MediumBacteria");
    chain.get("Bacteria").add("WeakBacteria");

    chain.put("StrongBacteria", new ArrayList<String>());
    chain.get("StrongBacteria").add("StrongBacteria");
    chain.get("StrongBacteria").add("MediumBacteria");
    chain.get("StrongBacteria").add("WeakBacteria");

    chain.put("MediumBacteria", new ArrayList<String>());
    chain.get("MediumBacteria").add("MediumBacteria");
    chain.get("MediumBacteria").add("WeakBacteria");

    chain.put("WeakBacteria", new ArrayList<String>());
    chain.get("WeakBacteria").add("WeakBacteria");

    for (String type: chain.keySet()) {
      chain.get(type).add("Agar");
    }
  }

  public static boolean canEat(Bacteria bacteria, Unit u) {
    return chain.get(bacteria.getClass().getSimpleName()).contains(u.getClass().getSimpleName());
  }
}
