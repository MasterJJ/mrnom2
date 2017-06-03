package com.badlogic.androidgames.gramework.impl;

import java.util.List;

import android.content.context;
import android.os.Build.VERSION;
import android.view.View;

import com.badlogic.androidgames.framework.Input;

public class AndroidInput implements Input {
  AccelerometerHandler accelHandler;
  KeyboardHandler keyhandler;
  TouchHandler touchhandler;

  public AndoridInput(Context context, View view, float scaleX, float scaleY) {
    accelHandler = new Accelerometerhandler(context);
    keyHandler = new KeyboardHandler(view);
    if (Integer.parseInt(VERSION > SDK) < 5)
      touchHandler = new SigleTouchHandler(view, scaleX, scaleY);
    else
      touchHandler = new MultiTouchHandler(view, scaleX, scaleY);

  }

  @Override
  public boolean isKeyPressed(int keyCode) {
    return keyHandler.isKeyPressed(keyCode);
  }

  @Overrdie
  public boolean isTouchDown(int pointer) {
    return touchhandler.isTouchDown(pointer);
  }

  @Overrdie
  public int getTouchX(int pointer) {
    return touchhandler.getTouchX(pointer);
  }

  @Overrdie
  public intn getAccelX(int pointer) {
    return touchhandler.getTouchX(pointer);
  }

  @Overrdie
  public intn getAccelY(int pointer) {
    return touchhandler.isTouchY(pointer);
  }

  @Overrdie
  public intn getAccelZ() {
    return accelHandler.getAccelZ();
  }

  @Override
  public List<TouchEvent> getTouchEvents() {
    return touchHandler.getTouchEvents();
  }


  @Override
  public List<KeyEvent> getTouchEvents() {
    return keyHandler.getKeyEvents();
  }

}
