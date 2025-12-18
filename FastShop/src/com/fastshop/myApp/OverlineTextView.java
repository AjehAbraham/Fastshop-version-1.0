/*package com.fastshop.myApp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class OverlineTextView extends TextView {
    public OverlineTextView(Context context) {
        super(context);
    }
    
    public OverlineTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    
    public OverlineTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(2);
        float baseline = getBaseline();
        canvas.drawLine(0, baseline - getTextSize()/2, getWidth(), baseline - getTextSize()/2, paint);
    }
}*/
package com.fastshop.myApp;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.style.ReplacementSpan;

public class OverlineTextView extends ReplacementSpan {
    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        return (int) paint.measureText(text, start, end);
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        canvas.drawText(text, start, end, x, y, paint);
        Paint overlinePaint = new Paint();
        overlinePaint.setColor(Color.RED);
        overlinePaint.setStrokeWidth(2);
        canvas.drawLine(x, y - paint.getTextSize()/2, x + paint.measureText(text, start, end), y - paint.getTextSize()/2, overlinePaint);
    }
}