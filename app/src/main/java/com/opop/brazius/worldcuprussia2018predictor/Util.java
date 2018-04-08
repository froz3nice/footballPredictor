package com.opop.brazius.worldcuprussia2018predictor;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v4.util.LruCache;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

/**
 * Created by Juozas on 2018.03.30.
 */

public class Util {
    public static String getTeamByButton(Button btn, ListAdapter.ViewHolder holder) {
        if (btn == holder.btn1) {
            return holder.name1.getText().toString();
        }
        if (btn == holder.btn2) {
            return holder.name2.getText().toString();
        }
        if (btn == holder.btn3) {
            return holder.name3.getText().toString();
        }
        if (btn == holder.btn4) {
            return holder.name4.getText().toString();
        }

        return "";
    }

    public static int getFlagByButton(Button btn, ListAdapter.ViewHolder holder) {
        if (btn == holder.btn1) return (int) holder.img1.getTag();
        if (btn == holder.btn2) return (int) holder.img2.getTag();
        if (btn == holder.btn3) return (int) holder.img3.getTag();
        if (btn == holder.btn4) return (int) holder.img4.getTag();

        return 0;
    }


    public static boolean isHeader(int pos){
        return pos == 0 || pos == 9 || pos == 14 || pos == 17 || pos == 19;
    }

    public static boolean isGroup(int pos){
        return pos > 0 && pos < 9;
    }
    public static boolean isRound16(int pos){
        return pos > 9 && pos < 14;
    }
    public static boolean isRound8(int pos){
        return pos > 14 && pos < 17 ;
    }
    public static boolean isSemiFinal(int pos){
        return pos == 18;
    }
    public static boolean isFinal(int pos){
        return pos == 20;
    }
    public static boolean isWinnerView(int pos){
        return pos == 21;
    }

}
