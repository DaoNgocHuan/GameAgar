package com.agar.game.controllers;

import com.agar.game.GameWorld;
import com.agar.game.models.Unit;
import com.agar.game.models.bacterias.Bacteria;

public class AIController {
  public AIController(Bacteria bacteria) {
      this.bacteria = bacteria;
  }

  public void move () {
    Unit victim = GameWorld.nearestVictimFor(this.bacteria);
    if (victim != null) {
      this.bacteria.moveTo(victim.getX(), victim.getY());
    }
  }

  private Bacteria bacteria;
}
