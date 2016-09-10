package com.apogiatzis.cygazetteapp.misc;

import android.graphics.Color;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by andre on 19/08/2016.
 */
public class Design {

    public static final int cRed = Color.rgb(219,51,64);
    public static final int cYellow = Color.rgb(241,192,86);
    public static final int cTurquise = Color.rgb(54,163,126);
    public static final int cOrange = Color.rgb(255,136,77);

    private static int previousColor;

    private static Random random = new Random(System.currentTimeMillis());

    public static List<Integer> articleColors = new ArrayList<Integer>(
            Arrays.asList(cRed, cYellow, cTurquise, cOrange)
    );

    public static int pickColor(){
        int index = random.nextInt(articleColors.size());
        int color;
        do{
            color = articleColors.get(index);
            index = random.nextInt(articleColors.size());
            Log.i("DEBUG", String.valueOf(color));
        } while(color == previousColor);
        previousColor = color;
        return color;
    }

}
