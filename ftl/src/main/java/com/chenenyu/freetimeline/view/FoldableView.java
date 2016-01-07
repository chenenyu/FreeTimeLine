package com.chenenyu.freetimeline.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Foldable View(i.e. plus sign and minus sign).
 * Created by Cheney on 16/1/6.
 */
public class FoldableView extends View {
    private static final Paint iconPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private boolean opened;

    public FoldableView(Context context) {
        this(context, null);
    }

    public FoldableView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public FoldableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        float strokeSize = FreeTimeLineUI.dpToPixel(2.0F, this.getResources());
        iconPaint.setStrokeWidth(strokeSize);
        iconPaint.setColor(FreeTimeLineUI.DEFAULT_FOLDABLE_COLOR);
    }

    protected void onDraw(Canvas canvas) {
        int width = this.getWidth();
        int height = this.getHeight();
        int halfHeight = height / 2;
        int halfWidth = width / 2;
        if (this.opened) {
            canvas.drawLine(0.0F, (float) halfHeight, (float) width, (float) halfHeight, iconPaint);
        } else {
            canvas.drawLine(0.0F, (float) halfHeight, (float) width, (float) halfHeight, iconPaint);
            canvas.drawLine((float) halfWidth, 0.0F, (float) halfWidth, (float) height, iconPaint);
        }
    }

    public void setOpened(boolean opened) {
        if (opened != this.opened) {
            this.opened = opened;
            this.invalidate();
        }
    }

}
