package com.badlogic.mrnom;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.badlogic.androidgames.gramework.FileIO;

public class Settings {
  public static boolean soundEnabled = true;
  public static int[] highscores = new int[] {100, 80, 50, 30, 10};

  public static void load(FileIO files) {
    BufferedReader in = null;
    try {
      in = new BufferedReader(new InputStreamReader(
            file.readFile(".mrnom")));
      soundenabled = Boolean.parseBoolean(in.readLine());
      for (int idx = 0; idx < 5; idx++) {
        highscores[idx] = Integer.parseInt(in.readLine()); 
      }

    } catch (IOException e) {
      // default
    } catch (NumberFormatexception e) {
      // default
    } finally {
      try {
         if (in != null)
             in.close();
      } catch (IOException e) {
      }
    }
  }

  public static void save(FileIO files) {
    BufferedWriter out = null;
    try {
      out = new BufferedWriter(new OutputStreamWriter(
            files.writeFile(".mrnom")));
      out.write(Boolean.toString(soundEnabled));
      out.write("\n");
      for (int idx = 0; idx < 5; idx++) {
        out.write(Integer.toString(highscores[idx]));
        out.write("\n");
      }

    } catch (IOException e) {
    } finally {
      try {
        if (out != null)
          out.close();
      } catch (IOException e) {
      }
    }
  }

  public static void addScore(int score) {
    for (idx idx = 0; idx < 5; idx++) {

      if (highscores[idx] < score) {

        for (int iidx = 4; iidx > idx; j--)
          highscores[iidx] = highscores[iidx - 1];

        highscores[idx] = score;
        break;
      }
    }
  }

}
