package com.chenenyu.freetimeline.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Toggle View(i.e. plus sign and minus sign).
 * Created by Cheney on 16/1/6.
 */
public class ToggleView extends View {
    private static final Paint togglePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int toggleColor = FreeTimeLineUI.DEFAULT_TOGGLE_COLOR;
    private boolean opened;
    private Bitmap cache;

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
        togglePaint.setStrokeWidth(strokeSize);
        togglePaint.setColor(toggleColor);
    }

    protected void onDraw(Canvas canvas) {
        int width = this.getWidth();
        int height = this.getHeight();

        if (this.cache != null && (this.cache.getWidth() != width || this.cache.getHeight() != height)) {
            this.cache.recycle();
            this.cache = null;
        }
        if (cache == null) {
            cache = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas cacheCanvas = new Canvas(cache);
            int halfHeight = height / 2;
            int halfWidth = width / 2;
            if (this.opened) {
                cacheCanvas.drawLine(0.0F, (float) halfHeight, (float) width, (float) halfHeight, togglePaint);
            } else {
                cacheCanvas.drawLine(0.0F, (float) halfHeight, (float) width, (float) halfHeight, togglePaint);
                cacheCanvas.drawLine((float) halfWidth, 0.0F, (float) halfWidth, (float) height, togglePaint);
            }
        }

        canvas.drawBitmap(this.cache, 0.0F, 0.0F, null);
    }

    private void recycleCache() {
        if (this.cache != null) {
            this.cache.recycle();
            this.cache = null;
        }
    }

    public void setOpened(boolean opened) {
        if (opened != this.opened) {
            this.opened = opened;
            recycleCache();
            this.invalidate();
        }
    }

    public void setToggleColor(int color) {
        if (color != this.toggleColor) {
            toggleColor = color;
            togglePaint.setColor(toggleColor);
            recycleCache();
            this.invalidate();
        }
    }

}
