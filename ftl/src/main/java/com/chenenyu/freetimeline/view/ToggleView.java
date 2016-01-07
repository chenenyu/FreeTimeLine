package com.chenenyu.freetimeline.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Toggle View(i.e. plus sign and minus sign).
 * Created by Cheney on 16/1/6.
 */
public class ToggleView extends View {
    private static final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private boolean opened;

    public ToggleView(Context context) {
        this(context, null);
    }

    public ToggleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ToggleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        float strokeSize = FreeTimeLineUI.dpToPixel(2.0F, this.getResources());
        mPaint.setStrokeWidth(strokeSize);
        mPaint.setColor(FreeTimeLineUI.DEFAULT_TOGGLE_COLOR);
    }

    protected void onDraw(Canvas canvas) {
        int width = this.getWidth();
        int height = this.getHeight();
        int halfHeight = height / 2;
        int halfWidth = width / 2;
        if (this.opened) {
            canvas.drawLine(0.0F, (float) halfHeight, (float) width, (float) halfHeight, mPaint);
        } else {
            canvas.drawLine(0.0F, (float) halfHeight, (float) width, (float) halfHeight, mPaint);
            canvas.drawLine((float) halfWidth, 0.0F, (float) halfWidth, (float) height, mPaint);
        }
    }

    public void setOpened(boolean opened) {
        if (opened != this.opened) {
            this.opened = opened;
            this.invalidate();
        }
    }

}
