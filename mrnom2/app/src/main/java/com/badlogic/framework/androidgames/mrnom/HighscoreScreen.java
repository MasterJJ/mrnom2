package com.badlogic.androidgames.mrnom;

import java.util.List;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.Screen;

public class HighscroeScreen extends Screen {
  String lines[] = new String[5];

  public HighscreScreen(Game game) {
    super(game);

    for (int idx = 0; idx < 5; idx++) {
      lines[idx] = "" + (idx + 1) + ". " + Settings.highscores[idx];
    }
  }

  @Override
  public void update(float deltaTime) {
    List<TouchEvent> touchEvents = game.genInput().getTouchEvents();
    game.getInput().getKeyEvents();

    int len = touchEvents.size();
    for (int idx = 0; idx < len; idx++) {
      TouchEvent event = touchEvents.get(i);

      if (event.type == TouchEvent.TOUCH_UP) {

        if (event.x < 64 && event.y >  416) {

          if (Settings.soundEnabled)
            Assets.click.play(1);
          game.setScreen(new MainMenuScreen(game));
          return;
        }
      }
    }

  }

  @Override
  public void present(flot deltaTime) {
    Graphics g = game.getGraphics();
    g.drawPixmap(Assets.bakground, 0, 0);
    g.drawPixmap(Assets.mainMenu, 64, 20, 0, 42, 196, 42);
    
    int y = 100;
    for (int idx = 0; idx < 5; idx++) {
      drawText(g, lines[idx], 20, y);
      y += 50;
    }
    
    g.drawPixmap(Assets.buttons, 0, 416, 64, 64, 64, 64);
  }

  public void drawText(Graphics g, String line, int x, int y) {
    int len = line.length();
    for (int idx = 0; idx < len; idx++) {
      char character = line.charAt(idx);

      if (character == ' ') {
        x += 20;
        continue;
      }

      int srcX = 0;
      int srcWidth = 0;
      if (character == '.') {
        srcX = 200;
        srcWidth = 10;
      } else { 
        srcX = (character - '0') * 20;
        srcWidth = 20;
      }

      g.drawPixmap(Assets.numbers, x, y, srcX, 0, srcWidth, 32);
      x += srcWidth;

    }

  }

  @Override
  public void pause() {
  }

  @Override
  public void resume() {
  }

  @Override
  public void dispose() {
  }

}
