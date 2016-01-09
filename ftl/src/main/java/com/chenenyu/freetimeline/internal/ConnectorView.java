package com.chenenyu.freetimeline.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Connector view.
 * Created by Cheney on 16/1/6.
 */
public class ConnectorView extends View {

    private Paint linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint solidPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint hollowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint suckerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint clearPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Bitmap cache;
    private float strokeSize;

    private int type;
    private int lineColor = FreeTimeLineUI.DEFAULT_LINE_COLOR;
    private int solidColor = FreeTimeLineUI.DEFAULT_SOLID_COLOR;
    private int hollowColor = FreeTimeLineUI.DEFAULT_HOLLOW_COLOR;
    private int suckerColor = FreeTimeLineUI.DEFAULT_SUCKER_COLOR;


    public ConnectorView(Context context) {
        this(context, null);
    }

    public ConnectorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ConnectorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * Default config.
     */
    private void init() {
        linePaint.setColor(lineColor);
        solidPaint.setColor(solidColor);
        hollowPaint.setColor(hollowColor);
        suckerPaint.setColor(suckerColor);
        clearPaint.setColor(0);
        clearPaint.setXfermode(FreeTimeLineUI.CLEAR_XFER_MODE);

        strokeSize = FreeTimeLineUI.dpToPixel(4.0F, this.getResources());
        linePaint.setStrokeWidth(strokeSize);
        hollowPaint.setStrokeWidth(strokeSize);
        suckerPaint.setStrokeWidth(strokeSize);

        this.type = FreeTimeLineUI.NODE_HOLLOW;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = this.getWidth();
        int height = this.getHeight();
        if (this.cache != null && (this.cache.getWidth() != width || this.cache.getHeight() != height)) {
            this.cache.recycle();
            this.cache = null;
        }

        if (this.cache == null) {
            this.cache = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas cacheCanvas = new Canvas(this.cache);
            float halfWidth = (float) width / 2.0F;
            float halfHeight = (float) height / 2.0F;
            float thirdWidth = (float) width / 3.0F;
            switch (this.type) {
                case FreeTimeLineUI.TOP_SUCKER:
                    float radiusClear = halfWidth - strokeSize / 2.0F;
                    cacheCanvas.drawRect(0.0F, 0.0F, (float) width, radiusClear, suckerPaint);
                    cacheCanvas.drawCircle(0.0F, radiusClear, radiusClear, clearPaint);
                    cacheCanvas.drawCircle((float) width, radiusClear, radiusClear, clearPaint);
                    cacheCanvas.drawLine(halfWidth, 0.0F, halfWidth, halfHeight, suckerPaint);
                    cacheCanvas.drawLine(halfWidth, halfHeight, halfWidth, (float) height, linePaint);
                    cacheCanvas.drawCircle(halfWidth, halfHeight, halfWidth, hollowPaint);
                    cacheCanvas.drawCircle(halfWidth, halfHeight, thirdWidth, clearPaint);
                    break;
                case FreeTimeLineUI.TOP_SOLID:
                    cacheCanvas.drawLine(halfWidth, halfHeight, halfWidth, height, linePaint);
                    cacheCanvas.drawCircle(halfWidth, halfHeight, thirdWidth, solidPaint);
                    break;
                case FreeTimeLineUI.TOP_HOLLOW:
                    cacheCanvas.drawLine(halfWidth, halfHeight, halfWidth, height, linePaint);
                    cacheCanvas.drawCircle(halfWidth, halfHeight, halfWidth, hollowPaint);
                    cacheCanvas.drawCircle(halfWidth, halfHeight, thirdWidth, clearPaint);
                    break;
                case FreeTimeLineUI.NODE_SOLID:
                    cacheCanvas.drawLine(halfWidth, 0.0F, halfWidth, (float) height, linePaint);
                    cacheCanvas.drawCircle(halfWidth, halfHeight, thirdWidth, solidPaint);
                    break;
                case FreeTimeLineUI.NODE_HOLLOW:
                    cacheCanvas.drawLine(halfWidth, 0.0F, halfWidth, (float) height, linePaint);
                    cacheCanvas.drawCircle(halfWidth, halfHeight, halfWidth, hollowPaint);
                    cacheCanvas.drawCircle(halfWidth, halfHeight, thirdWidth, clearPaint);
                    break;
                case FreeTimeLineUI.BOTTOM_SOLID:
                    cacheCanvas.drawLine(halfWidth, 0.0F, halfWidth, halfHeight, linePaint);
                    cacheCanvas.drawCircle(halfWidth, halfHeight, thirdWidth, solidPaint);
                    break;
                case FreeTimeLineUI.BOTTOM_HOLLOW:
                    cacheCanvas.drawLine(halfWidth, 0.0F, halfWidth, halfHeight, linePaint);
                    cacheCanvas.drawCircle(halfWidth, halfHeight, halfWidth, hollowPaint);
                    cacheCanvas.drawCircle(halfWidth, halfHeight, thirdWidth, clearPaint);
                    break;
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

    public void setType(int type) {
        if (type != this.type) {
            this.type = type;
            recycleCache();
            this.invalidate();
        }
    }

    public void setLineColor(int color) {
        if (color != this.lineColor) {
            lineColor = color;
            linePaint.setColor(lineColor);
            recycleCache();
            this.invalidate();
        }
    }

    public void setSolidColor(int color) {
        if (color != this.solidColor) {
            solidColor = color;
            solidPaint.setColor(solidColor);
            recycleCache();
            this.invalidate();
        }
    }

    public void setHollowColor(int color) {
        if (color != this.hollowColor) {
            hollowColor = color;
            hollowPaint.setColor(hollowColor);
            recycleCache();
            this.invalidate();
        }
    }

    public void setSuckerColor(int color) {
        if (color != this.suckerColor) {
            suckerColor = color;
            suckerPaint.setColor(suckerColor);
            recycleCache();
            this.invalidate();
        }
    }

}
