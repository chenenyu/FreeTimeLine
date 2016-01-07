package com.chenenyu.freetimeline.view;

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
    private Paint circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint togglePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint bottomPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint clearPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap cache;

    private int type;
    private float strokeSize;

    public static final int TOP_SUCKER = 0;
    public static final int TOP_SOLID = 1;
    public static final int TOP_HOLLOW = 2;

    public static final int NODE_HOLLOW = 10;

    public static final int BOTTOM_SOLID = 20;


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
        linePaint.setColor(FreeTimeLineUI.DEFAULT_LINE_COLOR);
        circlePaint.setColor(FreeTimeLineUI.DEFAULT_CIRCLE_COLOR);
        togglePaint.setColor(FreeTimeLineUI.DEFAULT_TOGGLE_COLOR);
        bottomPaint.setColor(FreeTimeLineUI.DEFAULT_END_COLOR);
        clearPaint.setColor(0);
        clearPaint.setXfermode(FreeTimeLineUI.CLEAR_XFER_MODE);

        strokeSize = FreeTimeLineUI.dpToPixel(4.0F, this.getResources());
        linePaint.setStrokeWidth(strokeSize);
        circlePaint.setStrokeWidth(strokeSize);
        togglePaint.setStrokeWidth(strokeSize);

        this.type = NODE_HOLLOW;
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
                case TOP_SUCKER:
                    float radiusClear = halfWidth - strokeSize / 2.0F;
                    cacheCanvas.drawRect(0.0F, 0.0F, (float) width, radiusClear, togglePaint);
                    cacheCanvas.drawCircle(0.0F, radiusClear, radiusClear, clearPaint);
                    cacheCanvas.drawCircle((float) width, radiusClear, radiusClear, clearPaint);
                    cacheCanvas.drawLine(halfWidth, 0.0F, halfWidth, halfHeight, togglePaint);
                    cacheCanvas.drawLine(halfWidth, halfHeight, halfWidth, (float) height, linePaint);
                    cacheCanvas.drawCircle(halfWidth, halfHeight, halfWidth, circlePaint);
                    cacheCanvas.drawCircle(halfWidth, halfHeight, thirdWidth, clearPaint);
                    break;
                case TOP_SOLID:
                    cacheCanvas.drawLine(halfWidth, halfHeight, halfWidth, height, linePaint);
                    cacheCanvas.drawCircle(halfWidth, halfHeight, thirdWidth, bottomPaint);
                    break;
                case TOP_HOLLOW:
                    cacheCanvas.drawLine(halfWidth, halfHeight, halfWidth, height, linePaint);
                    cacheCanvas.drawCircle(halfWidth, halfHeight, halfWidth, circlePaint);
                    cacheCanvas.drawCircle(halfWidth, halfHeight, thirdWidth, clearPaint);
                    break;
                case NODE_HOLLOW:
                    cacheCanvas.drawLine(halfWidth, 0.0F, halfWidth, (float) height, linePaint);
                    cacheCanvas.drawCircle(halfWidth, halfHeight, halfWidth, circlePaint);
                    cacheCanvas.drawCircle(halfWidth, halfHeight, thirdWidth, clearPaint);
                    break;
                case BOTTOM_SOLID:
                    cacheCanvas.drawLine(halfWidth, 0.0F, halfWidth, halfHeight, linePaint);
                    cacheCanvas.drawCircle(halfWidth, halfHeight, thirdWidth, bottomPaint);
                    break;
            }
        }

        canvas.drawBitmap(this.cache, 0.0F, 0.0F, null);
    }

    public void setType(int type) {
        if (type != this.type) {
            this.type = type;
            if (this.cache != null) {
                this.cache.recycle();
                this.cache = null;
            }
            this.invalidate();
        }
    }

}
