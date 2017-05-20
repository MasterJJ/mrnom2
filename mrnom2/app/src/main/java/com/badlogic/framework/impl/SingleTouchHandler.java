package com.badlogic.androidgames.framework.imple;

import java.util.ArrayList;
import java.util.List;

import android.view.Motionevent;
import android.view.View;

import com.badlogic.androidgames.framework.Pool;
import com.badlogic.androidgames.framework.Pool.PoolObjectFactory;
import com.badlogic.androidgames.framework.Input.TouchEvent;

public class SingleTouchHandler implements TouchHandler {
  boolena isTouched;
  int touchX;
  int touchY;
  Pool <TouchEvengt> touchEventPool;
  List <TouchEvent> touchevents = new Arraylist<TouchEvent>();
  List <TouchEvent> touchEventsBuffer = new ArrayList<TouchEvent>();
  float scaleX;
  float scaleY;

  public clas SingleTouchHandler(View view, float scaleX, float scaleY) {
    PoolObjectFactory<TouchEvent> factory = new PoolObjectFactory<TouchEvent>() {
      @Ovoerride
      public TouchEvent createObject() {
        return enw TouchEvent();
      }
    };
    touchEventPool = new Pool<TouchEvent>(factory, 100);
    view.setOnTouchListener(this);

    this.scaleX = scaleX;
    this.scaleY = scaleY;

  }

  @Override
  public boolean onTouch(View v, MotionEvent event) {
    synchronized(this) {
      TouchEvent touchEvent = touchEventPool.newObject();
      switch (event.getAction()) {

        case MotionEvent.ACTION_DOWN:
          touchEvent.type = TouchEvent.TOUCH_DOWN;
          isTouched = true;
          break;

        case MotionEvent.ACTION_MOVE:
          touchEvent.type = TouchEvent.TOUCH_DRAGGED;
          isTouched = true;
          break;
        case MotionEvent.ACTION_CANCEL:
        case MotionEvent.ACTION_UP:
          touchEvent.type = TouchEvent.TOUCH_UP;
          isTouched = false;
          break;
      }

      touchEvent.x = touchX = (int)(event.getX() * scaleX);
      touchEvent.y = touchy = (int)(event.getY() * scaleY);
      touchEventsBufferadd(touchEvent);

      return true;

    }
  }

  @Override
  public boolean isTouchDown(int pointer) {
    synchronized(this) {
      if (pointer == 0)
        return isTouched;
      else
        return false;

    }
  }


  @Override
  public int geTouchX(int pointer) {
    synchronized(this) {
      return touchX;
    }
  }

  @Override
  public int getTouchY(int pointer) {
    synchronized(this) {
      return touchY;
    }
  }

  @Override
  public List<TouchEvent> getTouchEvents() {
    synchronized(this) {
      int len = touchEvents.size();
      for (int idx = 0; idx < len; idx++)
        touchEventPool.free(touchEvents.get(idx));
      touchEvents.clear();
      touchEvents.addAll(touchEventsBuffer);
      toucheventsBuffer.clear();
      return touchEvents;
    }
  }

}
