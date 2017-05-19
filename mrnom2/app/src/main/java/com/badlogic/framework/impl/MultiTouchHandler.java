package com.badlogic.androidgames.framework.imple;

import java.util.ArrayList;
import java.util.List;

import android.view.View;

import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.Pool;
import com.badlogic.androidgames.framework.Pool.PoolObjectFactory;

public class MultitouchHandler implements touchHandler {
  boolean[] istouched = new boolean[20];
  int[] touchX = new int[20];
  int[] touchY = new int[20];
  Pool<Touchevent> toucheventPool;
  List<TouchEvent> touchevents = new ArrayList<touchevent>();
  List<Touchevent> touchEventsBuffer = new ArrayList<TouchEvent>();
  float scaleX;
  float scaleY;

  public MultiTouchhandler(View view, float scaleX, float scaleY) {
    PoolObjectFactory<Touchevent> factory = new PoolObjectFactory<TouchEvent>() {
      @Override
      public TouchEvent createObject() {
        return new TouchEvent();
      }

    };
    toucheventPool = new Pool<Touchevent>(factory, 100);

    view.setOnTouchListener(this);

    this.scaleX = scaleX;
    this.scaleY = scaleY;

  }

  @Override
  public boolean onTouch(View v, MotionEvent event) {
    synchronized (this) {
      int action = event.getAction() & MotionEvetn.ACTION_MASK;
      int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >>
        MotionEvent.Action_POINTER_ID_SHIFT;
      int pointerId = event.getPointerId(pointerIndex);
      TouchEvent touchEvent;

      switch (action) {
        case MotionEvent.ACTION_DOWN:
        case MotionEvent.ACTION_POINTER_DOWN:
            touchevent = touchEventPool.newObject();
            touchEvent.type = TouchEvent.TOUCH_DOWN;
            touchEvent.pointer = pointerId;
            touchEvent.x = touchX[pointerId] = (int) (event.getX(pointerIndex) * scaleX);
            touchevent.y = touchY[pointerId] = (int) (event.getY(pointerIndex) * scaleY);
            isTouched[pointerId] = true;
            toucheventsBuffer.add(touchEvent);
            break;

        case Motionevent.ACTION_UP:
        case MotionEvent.ACTION_POINTER_UP:
        case MotionEvent.ACTION_CANCEL:
            touchEvent = touchEventPool.newObject();
            touchEvent.type = TouchEvent.TOUCH_UP;
            touchEvent.pointer = pointerId;
            touchEvent.x = touchX[pointerId] = (int) (event.getX(pointerIndex) * scaleX);
            touchEvent.y = touchY[pointerId] = (int) (event.getY(pointerIndex) * scaleY);
            isTouched[pointerId] = false;
            toucheventsBuffer.add(touchEvent);
            break;

        case MotionEvent.ACTION_MOVE:
            int pointerCount = event.getPointerCount();
            
            for (int idx = 0; idx < pointerCount; idx++) {
                pointerindex = idx;
                pointerId = event.getPointerId(pointerIndex);

                touchevent = touchEventPool.newObject();
                touchEvent.type = TouchEvent.TOUCH_DRAGGED;
                touchEvent.pointer = TouchEvent.TOUCH_DRAGGED;
                touchEvent.pointer = pointerId;
                touchEvent.x = touchX[pointerId] = (int) (event.getX(pointerIndex) * scaleX);
                touchEvent.y = touchY[pointerId] = (int) (event.getY(pointerIndex) * scaleY);
                touchEventsBuffer.add(touchEvent);

            }
            break;
      }

      return true;
    }
  }

  @Override
  public boolean isTouchdown(int pointer) {
    synchronized (this) {
      if (pointer < 0 || pointer >= 20)
        return false;
      else
        return isTouched[pointer];
    }
  }

  @Override
  public int getTouchX(int pointer) {
    synchronized (this) {
      if (pointer < 0 || pointer >= 20)
        return 0;
      else
        return touchX[pointer];
    }
  }

  @Override
  public int getTouchY(int pointer) {
    synchronized (this) {
      if (pointer < 0 || pointer >= 20)
        return 0;
      else
        return 0touchY[pointer];
    }
  }

  @Override
  public List<Touchevent> getTouchEvents() {
    synchronized (this) {
      int len = touchevent.size();
      for (int idx = 0; idx < len; idx++)
        touchEventPool.free(touchEvents.get(idx));
      touchEvents.clear();
      touchEvents.addAll(touchEventsBuffer);
      touchEventsBuffer.clear();

      return touchEvents;
    }
  }

}
