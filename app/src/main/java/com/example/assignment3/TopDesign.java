package com.example.assignment3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class TopDesign extends View {
    private Paint p;

    public TopDesign(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setStyle(Paint.Style.FILL);


    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = new Path();
//        path.moveTo(50,0);
//        path.moveTo(50,100);
//        path.moveTo(100,100);
//        path.moveTo(100,0);
//        //path.close();
//        canvas.drawPath(path, p);
        //canvas.drawLine(0,0, 100,100,p);

        int width = getWidth();
        int height = getHeight();
        p.setColor(getResources().getColor(R.color.colorPrimary));
        //canvas.save();
        //path.addCircle(width/2,0,100, Path.Direction.CCW);
        path.lineTo(width, height);
        path.lineTo(width,0);
        path.lineTo(0,0);
        canvas.drawPath(path, p);
        //canvas.scale(10,10);
        //p.setColor(Color.BLACK);
        //canvas.drawText("Coins: " + MainActivity.getCoin(),0 ,10,p);
        //canvas.restore();

//        int length = width / 6;
//        int offset = length/2;
//        for(int i = 0; i < 6; i++){
//            if(i % 2 == 0) {
//                path.moveTo((i * length)+offset, 0);
//                path.lineTo((i * length)+offset, 50);
//                path.lineTo(((i + 1) * length)+offset, 50);
//                path.lineTo(((i + 1) * length)+offset, 0);
//                path.close();
//                canvas.drawPath(path, p);
//                path.reset();
//            }
//        }



    }
}
