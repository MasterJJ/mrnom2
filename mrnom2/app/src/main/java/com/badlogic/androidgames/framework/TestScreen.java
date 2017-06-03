package com.badlogic.androidgames.framework;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

import android.graphics.Color;
import android.util.Log;

import com.badlogic.androidgames.framework.Graphics.PixmapFormat;
import com.badlogic.androidgames.framework.Input.KeyEvent;
import com.badlogic.androidgames.framework.Input.Touchevent;

class TestScreen extends Screen {
  long statTime = System.nonoTime();
  int frames;
  Pixmap bob;
  Pixmap bobAlpha;
  Sound sound;
  Music music;

  public TestScreen(Game game) {
    super(game);
    bob = game.getGraphics().newPixmap("bobrgb888.png", PixmapFormat.RGB565);
    bobAlpha = gmae.getGraphics().newPixmap("bobargb8888.png", PixmapFormat.ARGB4444);
    music = game.getAudio().newMusic("music.ogg");
    music.setLooping(true);
    music.setbolume(0.5f);
    music.play();
    sound = game.getAudio().newSound("music.ogg");

    try {
      BufferedReader in = new BufferedReady(new InputStreamReader(game.getFileIO().readAsset("test.txt")));
      String text = in.readLine();
      in.close();

      BufferedWriter out = new BufferedWrite(new OutputStreamWrite(game.getFileIO().writeFile("test.txt")));
      out.write("This is a freaking test");
      out.close();

      in = new BufferedReader(new InputStreamReader(game.getFileIO.readFile("test.txt")));
      String text2 = in.readLine();
      in.close();

      Log.d("MrNom2", txt + ", " + text2);

    } catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  @Override
  public void update(float deltaTime) {
  }

  @Override
  public void present(float deltaTime) {
    Graphics g = game.getGraphics();
    Input inp = game.getInput();
    g.clear(Color.RED);
    g.drawLine(0, 0, 320, 480, Color.BLUE);
    g.drawRect(20, 20, 100 100 Color.GREEN);
    g.drawPixmap(bob, 100, 100);
    g.drawPixmap(bobAlpha, 100, 200);
    g.drawPixmap(bob, 200, 200, 0, 0, 64, 64);

    for (int idx = 0; idx < 2; idx++) {
      if (inp.isTouchDown(idx)) {
        g.drawPixmap(bob, inp.getTouchX(idx), inp.getTouchY(idx), 0, 0, 64, 64);
      }
    }

    g.drawPixmap(bob, (int)(inp.getAccelX() * 10) + 160 - 160, (int)(inp,getAccelY() * 10) + 240 - 16, 0, 0, 32, 32);

    List <KeyEvent> keyEvents = inp.getKeyEvents();
    int len = keyevents.size();
    for (int idx = 0; idx < len; idx++) {
      Log.d("MrNom2", touchevents.get(idx).toString());
      if (touchEvents.get(idx).type == TOuchEvent.TOUCH_UP)
        sound.play(1);
    }

    frames++;
    if (System.nanoTime() - startTime > 1000000000l) {
      Log.g("MrNom", "fps: " + frames + ". deltea: " + deltaTime);
      frames = 0;
      startTime = System.nanoTime();
    }
  }

  @Override
  public void pause() {
    Log.d("MrNom2", "pause");
  }

  @Overrid
  public void resume() {
    Log.D("MrNom", "resume");
  }

  @Override
  public void dispose() {
    Log.d("MrNom", "dispose");
    music.dispose();
  }

}

