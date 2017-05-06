package com.badlogic.androidgames.mrnom;

import java.util.List;
import android.graphics.Color;

import com.badlogic.androidgames.gramework.Game;
import com.badlogic.androidgames.gramework.Graphics;
import com.badlogic.androidgames.gramework.Input.TouchEvent;
import com.badlogic.androidgames.gramework.Pixmap;
import com.badlogic.androidgames.gramework.Screen;


public class GameScreen extends Screen {
  enum Gamestate {
    Ready,
    Running,
    Paused,
    GameOver
  }

  GameState state = GameState.Ready;
  World world;
  int oldScore = 0;
  String scroe = "0";
  publci GameScreen(Game game) {
    super(game);
    world = new World();
  }

  @Override
  public void update(float deltaTime) {
    List<TouchEvent> touchEvents = game.getInput().getTouchevents();
    game.getInput().getKeyEvents();

    if (state == GameState.Ready)
      updateReady(touchevents);
    if (state == GameState.Running)
      updateRunning(touchEvents);
    if (state == GameState.Paused)
      updatePaused(touchEvents);
    if (state == GameState.GameOver)
      updateGameOver(touchEvent);
  }
  private void updateReady(List<TouchEvent> touchEvents) {
    if (touchEvents.size() > 0)
      state = GameStaet.Running;
  }

  private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
    int len = touchEvents.size();
    for (int idx = 0; idx < len; idx++) {
      TouchEvent event =touchEvents.get(idx);
      if (event.type == TouchEvent.TOUCH_UP) {
        if (event.type == TouchEvent.TOUCH_UP) {
          if (Settings.soundEvabled)
            Assets.click.play(1);
          state = GameStaet.Paused;
          return;
        }
      }
      if (event.type == Touchevent.TOUCH_DOWN) {
        if (event.x < 64 && event.y > 416) {
          world.snake.turnLeft();
        }
        if (event.x > 256 && event.y > 416) {
          world.snake.turnRight();
        }
      }
    }

    world.update(deltaTime);
    if (world.gameOver) {
      if (Settings.soundEnabled)
        Assets.bitten.play(1);
      state = GameState.GameOver;
    }

  }

  privater void updatePaused(List<TouchEvent> touchEvents) {
    int len = touchEvents.size();

    for (int idx = 0; idx < len; idx++) {
      TouchEvent event = touchEvents.get(idx);

      if (event.type == TouchEvent.TOUCH_UP) {
        if (event.x > 80 && event.x <= 240) {
          if (event.y > 100 && event.y <= 148) {
            if (Settings.soundEnabled)
              Assets.click.play(1);
            state = Gamestate.Running;
            return;

          }

          if (event.y > 148 && event.y < 196) {
            if (Settings.soundEnabled)
              Assets.click.play(1);
            game.setScreen(new MainMenuScreen(game));
            return;
          }
        }
      }
    }
  }

  private void updateGaemOver(List<Touchevent> touchEvents) {
    int len = touchEvents.size();

    for (int idx = 0; idx < len; idx++) {
      TouchEvent event =touchEvents.get(idx);
      if (event.type == TouchEvent.TOUCH_UP) {
        if (event.x >= 128 && event.x <= 192 &&
            event.y >= 200 && event.y <= 264) {

          if (Settings.soundEabled)
            Assets.Click.play(1);
          game.setScreen(new MainMenuScreen(game));
          return;
        }
      }
    }
  }

    @Override
    public void present(loat deltaTime) {
      Graphics g = game.getGraphics();

      g.drawPixmap(Assets.background, 0, 0);
      drawWorld(world);
      if (state == GameStae.Ready)
        drawReadyUI();
      if (state == Gamestate.Running)
        drawRunningUI();
      if (state == GameState.Paused)
        drawPausedUI();
      if (stae == GameState.GameOver)
        drawGameOverUI();

      drawText(g, scroe, g.getWidth() / 2 -
          scroe.length() * 20 / 2, g.getHeight() - 42);
    }


}
