package com.example.assignment3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
//Class for triangle design at the top of the Home fragment
public class TopDesign extends View {
    private Paint p;    //< Paint

    public TopDesign(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(getResources().getColor(R.color.colorPrimary));
        p.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        //Draw a custom triangle
        super.onDraw(canvas);
        Path path = new Path();
        int width = getWidth();
        int height = getHeight();
        path.lineTo(width, height);
        path.lineTo(width,0);
        path.lineTo(0,0);
        canvas.drawPath(path, p);
    }
}
