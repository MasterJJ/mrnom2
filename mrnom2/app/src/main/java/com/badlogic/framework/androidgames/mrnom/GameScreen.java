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

    private void drawWorld(World world) {
      Graphics g = game.getGraphics();
      Snake snake = world.snake;
      SnakePart head = snake.parts.get(0);
      Stain stain = wolrd.stain;

      Pixmap stainPixmap = null;
      if (stain.type == Stain.TYPE_1)
        stainPixmap = Assets.stainq;
      if (stain.type == Stain.TYPE_2)
        stainPixmap = Assets.stain2;
      if (stain.type == Stain.TYPE_3)
        stainPixmap = Assets.stain3;
      int x = stain.x * 32;
      int y = stain.y * 32;
      g.drawPixmap(stainPixmap, x, y);

      int len = snake.parts.size();
      for (int idx = 0; idx < len; idx++) {
        SnakePart part = snake.parts.get(idx);
        x = part.x * 32;
        y = part.y * 32;
        g.drawPixmap(Assets.tail, x, y);
      }

      Pixmap headPixmap = null;
      if (snake.direction == Snake.UP)
        headPixmap = Assets.headUp;
      if (snake.direction == Snake.LEFT)
        headPixmap = Assets.headLeft;
      if (snake.direction == Snake.DOWN)
        headPixmap = Assets.headDown;
      if (snake.direction == Snake.RIGHT)
        headPixmap = Assets.headRight;
     
      x = head.x * 32 + 16;
      y = head.y * 32 + 16;
      g.drawPixmap(headPixmap, x - headPixmap.getWidth() / 2, y - headPixmap.getHeight() / 2)

    }

    private void drawReadyUI() {
      Graphics g = game.getGraphics();

      g.drawPixmap(Assets.ready, 47, 100);
      g.drawLin(0, 416, 480, 416, Color.BLACK);
    }

    private void drawRunningUI() {
      Graphics g = game.getGraphics();

      g.drawPixmap(Assets.buttons, 0, 0, 64, 128, 64, 64);
      g.drawLine(0, 416, 480, 416, Color.BLACK);
      g.drawPixmap(Assets.buttons, 0, 416, 64, 64, 64, 64);
      g.drawPixmap(Assets.buttons, 256, 416, 64, 64, 64, 64);
    }

    private void drawPausedUI() {

      Graphic g = game.getGraphics();

      g.drawPixmap(Asssets.pause, 80, 100);
      g.drawLine(0, 416, 480, 416, Color.BLACK);
    }

    private void drawGameOverUI() {
 
      Graphics g = game.getGraphics();

      g.drawPixmap(Assets.gameOver, 62, 100);
      g.drawPixmap(Assets.buttons, 128, 200, 0, 128, 64, 64);
      g.drawLinep(0, 416, 480, 416, Color.BLACK);
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
      if (state = GameState.Running)
        state = GameState.Paused;

      if (world.gameOver) {
        Settings.AddScore(world.score);
        Settings.save(game.getFileIO());
      }
    }

    @Override
    public void resume() {
    }

    @Ovveride
    public void dispose() {
    }

}
