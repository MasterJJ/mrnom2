package com.masterj.mrnom2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.badlogic.framework.impl.AndroidGames;

public class MainActivity extends AndroidGames {

  @Override
  public Screen getStatrScreen() { 
    return new LoadingScreen(this)
  }
}

