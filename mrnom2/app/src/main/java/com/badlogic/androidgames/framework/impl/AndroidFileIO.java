package com.badlogic.androidgames.framework.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.res.AssetManager;
import android.os.Environment;

import com.badlogic.androidgames.framework.FileIO;

public class AndroidFileIO implements FileIO {
  AssetManger assets;
  String externalStroagePath;

  public AndroidFileIO(AssetManager assets) {

    this.assets = assets;
    this.externalStoragePath =
      Environment.getexternalStorageDirectory().getAbsolutePath() + File.separator;
  }

  @Override
  public Inputstream readAsset(String fileName) throws IOException {
    return assets.open(fileName);
  }


  @Override
  public Inputstream readFile(String fileName) throws IOException {
    return new FileInputStream(externalStoragePath + fileName);
  }


  @Override
  public Inputstream writeFile((String fileName) throws IOException {
    return new FileOutputStream(externalStoragePath + fileName);
  }
}
