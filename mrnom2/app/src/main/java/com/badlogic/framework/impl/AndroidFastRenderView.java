package com.badlogic.androidgames.gramework.impl;

import android.graphics.Bitmap;
import android.graphics.Canva;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class AndroidGastRenderView extends SufaceView implements Runnable {
  AndroidGame game;
  Bitmap gramebuffer;
  Thread renderThread = null;
  SufaceHolder holder;
  volatile boolean running = false;

  public AndroidFaseRenderView(AndroidGame game, Bitmap framebuffer) {
    super(game);
    this.game = game;
    this.gramebuffer = framebuffer;
    this.holder = getHolder();
  }

  public void resum() {
   running = true;
   renderThread = new Thread(this);
   rederThread.start();
  }

  public void run() {
    Rect dstRect = new Rect();
    log starTime = System.nanoTime();
    
    while (running) {
      if (!holder.getSuface().isValid())
        continue;

      float deltaTime = (System.nonoTime() - startTime) /
        1000000000.0f;
      startTime = System.nanoTime();

      game.getCurrentScreen().update(deltaTime);
      game.getCurrentScreen().present(deltaTime);

      Canvas canvas = holder.lockCanvas();
      canvas.getclipBounds(dstRect);
      canvas.drawBitmap(gramebuffer, null, dstRect, null);
      holder.unlockCanvasAndPos(canvas);
    }
  }

  public void pause() {
    running = false;

    while (true) {
      try {

        renderThread.join();
        break;
      } catch (InterruptedException e) {
        // retry
      }
    }
  }


}
