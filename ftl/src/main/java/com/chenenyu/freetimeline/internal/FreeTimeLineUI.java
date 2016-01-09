package com.chenenyu.freetimeline.internal;

import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.DisplayMetrics;

public class FreeTimeLineUI {
    public static final int DEFAULT_LINE_COLOR = 0xFFD3D3D3;
    public static final int DEFAULT_SOLID_COLOR = 0xFF7B593B;
    public static final int DEFAULT_HOLLOW_COLOR = 0xFFD3D3D3;
    public static final int DEFAULT_TOGGLE_COLOR = 0xFF4EAAB2;
    static final PorterDuffXfermode CLEAR_XFER_MODE;

    // Top type
    public static final int TOP_SUCKER = 0;
    public static final int TOP_SOLID = 1;
    public static final int TOP_HOLLOW = 2;

    // Node type
    public static final int NODE_SOLID = 10;
    public static final int NODE_HOLLOW = 11;

    // Bottom type
    public static final int BOTTOM_SOLID = 20;
    public static final int BOTTOM_HOLLOW = 21;


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
