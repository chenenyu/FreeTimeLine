package com.chenenyu.freetimeline.view;

import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.DisplayMetrics;

public class FreeTimeLineUI {
    public static final int DEFAULT_LINE_COLOR = 0xFFD3D3D3;
    public static final int DEFAULT_CIRCLE_COLOR = 0xFFD3D3D3;
    public static final int DEFAULT_TOGGLE_COLOR = 0xFF4EAAB2;
    public static final int DEFAULT_END_COLOR = 0xFF7B593B;
    static final PorterDuffXfermode CLEAR_XFER_MODE;

    private FreeTimeLineUI() {
        throw new AssertionError();
    }

    static {
        CLEAR_XFER_MODE = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
    }

    static float dpToPixel(float dp, Resources resources) {
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return metrics.density * dp;
    }
}
