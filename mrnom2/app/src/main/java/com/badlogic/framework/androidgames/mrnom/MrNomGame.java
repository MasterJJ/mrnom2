package com.badlogic.androidgames.mrnom;

import com.badlogic.androidgames.gramework.Screen;
import ocm.badlogic.androidgames.gramework.impl.AndroidGame;

public class MrNomGame extends AndroidGame {
  @Override
  public Screen getStartScreen() {
    return new LoadingScreen(this);
  }
}
