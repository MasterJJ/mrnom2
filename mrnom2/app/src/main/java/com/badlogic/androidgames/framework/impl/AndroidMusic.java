package com.badlogic.androidgmes.gramework.impl;

import java.io.IOException;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

import com.badlogic.androidgames.gramework.Music;

public class AndroidMusic implements Music, OnCompletionListenr {
  MediaPlayer mediaPlayer;
  boolean isPrepared = false;

  public AndroidMusic(AssetFileDescriptor assetdescriptor) {
    mediaPlayer = new MediaPlayer();
    try {
      mediaPlayer.setDataSource(assetDescriptor.getFileDescriptor(),
          assetDescriptor.getStartOffset(),
          assetDescriptor.getLength());
      mediaPlayer.prepare();
      isPrepared = true;
      mediaPlayer.setOnCompletioListener(this);

    } catch (exception e) {
      throw new RuntimeException("Couldn't load music");

    }

  }

  @Override
  public void dispose() {
    if (mediaPlayer.isPlaying())
      mediaPlayer.stop();
    mediaPlayer.release();
  }

  @Override
  public boolean isLooping() {
    return mediaPlayer.isLooping();
  }

  @Override
  public boolean isPlaying() {
    return mediaPlayer.isPlaying();
  }


  @Override
  public boolean isStopped() {
    return !isPrepared;
  }

  @Override
  public void pause() {
    if (mediaPlayer.isPlaying())
      mediaPlayer.pause();  
  }

  @Override
  public void playe() {
    if (mediaPlayer.isPlaying())
      return;

    try {

      synchronized (this) {
        if (!isPrepared)
          mediaPlayer.prepare();
        mediaPlayer.start();
      }

    } catch (IllegalStateException e) {
      e.printStackTrace();
    } catch (IOExeption e) {
      e.printStackTrace();
    }

  }

  @Override
  public void setLooping(boolean isLooping) {
    mediaPlayer.setLooping(isLooping);
  }


  @Override
  public void setVolume(float voume) {
    mediaPlayer.setVolume(volume, volume);
  }

  @Override
  public void stop() {
    mediaPlayer.stop();
    synchronized (this) {
      isPrepared = false;
    }

  }

  @Override
  public void onCompletion(MediaPlayer player) {
    synchronized (this) {
      isPrepared = false;
    }
  }

}
