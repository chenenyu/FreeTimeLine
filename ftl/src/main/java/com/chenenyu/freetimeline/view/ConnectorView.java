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
    private Paint iconPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint rootPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint bottomPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint clearPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private ConnectorView.Type type;
    private Bitmap cache;


    public enum Type {
        SUCKER_TOP, // 顶部吸盘样式
        NORMAL_TOP, // 顶部普通样式
        NODE,  // 中间普通结点
        BOTTOM; // 底部样式

        Type() {
        }
    }

    public void setType(ConnectorView.Type type) {
        if (type != this.type) {
            this.type = type;
            if (this.cache != null) {
                this.cache.recycle();
                this.cache = null;
            }
            this.invalidate();
        }
    }

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
        iconPaint.setColor(FreeTimeLineUI.DEFAULT_LINE_COLOR);
        rootPaint.setColor(FreeTimeLineUI.DEFAULT_ROOT_COLOR);
        bottomPaint.setColor(FreeTimeLineUI.DEFAULT_END_COLOR);
        clearPaint.setColor(0);
        clearPaint.setXfermode(FreeTimeLineUI.CLEAR_XFER_MODE);
        this.type = Type.NORMAL_TOP;
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
            float strokeSize = FreeTimeLineUI.dpToPixel(4.0F, this.getResources());
            iconPaint.setStrokeWidth(strokeSize);
            rootPaint.setStrokeWidth(strokeSize);
            switch (this.type.ordinal()) {
                case 0: // SUCKER_TOP
                    float radiusClear = halfWidth - strokeSize / 2.0F;
                    cacheCanvas.drawRect(0.0F, 0.0F, (float) width, radiusClear, rootPaint);
                    cacheCanvas.drawCircle(0.0F, radiusClear, radiusClear, clearPaint);
                    cacheCanvas.drawCircle((float) width, radiusClear, radiusClear, clearPaint);
                    cacheCanvas.drawLine(halfWidth, 0.0F, halfWidth, halfHeight, rootPaint);
                    cacheCanvas.drawLine(halfWidth, halfHeight, halfWidth, (float) height, iconPaint);
                    cacheCanvas.drawCircle(halfWidth, halfHeight, halfWidth, iconPaint);
                    cacheCanvas.drawCircle(halfWidth, halfHeight, thirdWidth, clearPaint);
                    break;
                case 1: // NORMAL_TOP
                    cacheCanvas.drawLine(halfWidth, halfHeight, halfWidth, height, iconPaint);
                    cacheCanvas.drawCircle(halfWidth, halfHeight, thirdWidth, bottomPaint);
                    break;
                case 2: // NODE
                    cacheCanvas.drawLine(halfWidth, 0.0F, halfWidth, (float) height, iconPaint);
                    cacheCanvas.drawCircle(halfWidth, halfHeight, halfWidth, iconPaint);
                    cacheCanvas.drawCircle(halfWidth, halfHeight, thirdWidth, clearPaint);
                    break;
                default: // BOTTOM
                    cacheCanvas.drawLine(halfWidth, 0.0F, halfWidth, halfHeight, iconPaint);
                    cacheCanvas.drawCircle(halfWidth, halfHeight, thirdWidth, bottomPaint);
            }
        }

        canvas.drawBitmap(this.cache, 0.0F, 0.0F, null);
    }

}
