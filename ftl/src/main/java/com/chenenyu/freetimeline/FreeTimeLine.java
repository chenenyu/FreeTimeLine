package com.chenenyu.freetimeline;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

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

    private ListView mContent;
    private List<FreeTimeLineElement> mElements;
    private FreeTimeLineAdapter mAdapter;
    private int LINE_COLOR;
    private int CIRCLE_COLOR;
    private int END_COLOR;
    private int TOGGLE_COLOR;
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
        END_COLOR = a.getColor(R.styleable.FreeTimeLine_bottom_color, FreeTimeLineUI.DEFAULT_END_COLOR);
        TOGGLE_COLOR = a.getColor(R.styleable.FreeTimeLine_toggle_color, FreeTimeLineUI.DEFAULT_TOGGLE_COLOR);
        TOP_TYPE = a.getInt(R.styleable.FreeTimeLine_top_type, ConnectorView.Type.NORMAL_TOP.ordinal());
        a.recycle();

        mContent = new ListView(context);
        mContent.setDivider(null);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        this.addView(mContent, layoutParams);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 1) {
            throw new IllegalStateException("FreeTimeLine can't have any child in xml.");
        }
    }

    public void setElements(List<FreeTimeLineElement> elements) {
        this.mElements = elements;
        if (mElements != null) {
            if (mAdapter == null) {
                mAdapter = new FreeTimeLineAdapter(getContext(), mElements);
                // custom attrs
            }
        }
        mContent.setAdapter(mAdapter);
        mContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mAdapter.toggleRow(position);
            }
        });
    }
}
