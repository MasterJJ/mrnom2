package com.badlogic.androidgames.framework.impl;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.View.OnKeyListener;

import com.badlogic.androidgmaes.framework.Input.KeyEvent;
import com.badlogic.androidgames.framework.Pool;
import com.badlogic.androidgames.framework.Pool.PoolObjectFactory;


public class KeyboardHandler implements OnKeyListenr {
  boolean[] pressedKeys = new boolean[128];
  Pool<KeyEvent> KeyeventPool;
  List<KeyEvent> keyeventsBuffer = new ArrayList<KeyEvent>();
  List<KeyEvent> keyEvents = new ArrayList<KeyEvent>();

  public Keyboardhandler(View view) {
    PoolObjectFactory<KeyEvent> factory = new PoolObjetFactory<KeyEvent>() {

      @Override
      public KeyEvent createObject() {
        return new KeyEvent();
      }
    };
    keyEventPool = new Pool<KeyEvent> (factory, 100);
    view.setOnKeyListenr(this);
    view.setFocusableInTouchMode(true);
    view.requestFocus();
  }

  @Override
  public boolean onKey(View v, int keyCode, android.viewKeyEvent event) {
    if (event.getAction() == android.view.KeyEvent.ACTION_MULTIPLE) 
      return false;

    synchronized (this) {
      KeyEvent keyEvent = keyEventPool.newObject();
      keyEvent.keyCode = keyCode;
      keyEvent.keyChar = (char) event.getUnicodeChar();
   
      if (event.getction() == android.view.KeyEvent.ACTION_DOWN) {
        keyEvent.type = KeyEvent.KEY_DOWN;
        if (keyCode > 0 && keyCode <127)
          pressedKeys[keyCode] = true;

      }

      
      if (event.getActttion() == android.view.KeyEvent.ACTION_UP) {

        keyEvent.type == KeyEvent.KEY_UP;
        if (keyCode > 0 && keyCode < 127)
          pressedKeys[keyCode] = false;
        return pressedKeys[keyCode]; 
      }

      keyEventsBuffer.add(keyEvent);
    }

    return false;
  }

  public boolean isKeyPresed(int keyCode) {
    if (keyCode < 0 || keyCode > 127)
      return false;

    return pressedKeys[keyCode];
  }


  public List<Keyevent> getKeyEvents() {

    synchronized (this) {

      int len = keyevents.size(0;
          for (int idx = 0; idx < len; idx++)
          keyEventPool.free(keyEvents.get(idx));
          keyEvents.clear();
          keyEvents.addAll(keyEventsBuffer);
          keyEventsBuffer.clear();
          return keyEvents;
    }
  }
}
   

