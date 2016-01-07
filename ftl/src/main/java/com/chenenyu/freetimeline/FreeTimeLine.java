package com.chenenyu.freetimeline;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.chenenyu.freetimeline.view.ConnectorView;
import com.chenenyu.freetimeline.view.FreeTimeLineUI;

import java.util.List;

/**
 * <p>
 * Custom layout that contains <a href="https://github.com/chenenyu/FreeTimeLine">FreeTimeLine</a>.
 * </p>
 * Created by Cheney on 16/1/6.
 */
public class FreeTimeLine extends FrameLayout {

    private List<FreeTimeLineElement> mElements;
    private FreeTimeLineAdapter mAdapter;
    private int LINE_COLOR;
    private int CIRCLE_COLOR;
    private int END_COLOR;
    private int FOLDABLE_COLOR;
    private int TOP_TYPE;

    public FreeTimeLine(Context context) {
        this(context, null);
    }

    public FreeTimeLine(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FreeTimeLine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FreeTimeLine, defStyleAttr, 0);
        LINE_COLOR = a.getColor(R.styleable.FreeTimeLine_line_color, FreeTimeLineUI.DEFAULT_LINE_COLOR);
        CIRCLE_COLOR = a.getColor(R.styleable.FreeTimeLine_circle_color, FreeTimeLineUI.DEFAULT_CIRCLE_COLOR);
        END_COLOR = a.getColor(R.styleable.FreeTimeLine_end_color, FreeTimeLineUI.DEFAULT_END_COLOR);
        FOLDABLE_COLOR = a.getColor(R.styleable.FreeTimeLine_foldable_color, FreeTimeLineUI.DEFAULT_FOLDABLE_COLOR);
        TOP_TYPE = a.getInt(R.styleable.FreeTimeLine_top_type, ConnectorView.Type.NORMAL_TOP.ordinal());
        a.recycle();
        // TODO: 16/1/6  add view
    }

}
