package com.chenenyu.freetimeline;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.chenenyu.freetimeline.internal.FreeTimeLineAdapter;
import com.chenenyu.freetimeline.internal.FreeTimeLineUI;

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

    private FreeTimeLineConfig mConfig;
    private int TOP_TYPE; // 顶部结点的样式
    private int LINE_COLOR; // 竖线的颜色
    private int SOLID_COLOR; // 实心圆的颜色
    private int HOLLOW_COLOR;
    private int TOGGLE_COLOR;

    public FreeTimeLine(Context context) {
        this(context, null);
    }

    public FreeTimeLine(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FreeTimeLine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FreeTimeLine, defStyleAttr, 0);
        TOP_TYPE = a.getInt(R.styleable.FreeTimeLine_top_type, FreeTimeLineUI.TOP_SOLID);
        LINE_COLOR = a.getColor(R.styleable.FreeTimeLine_line_color, FreeTimeLineUI.DEFAULT_LINE_COLOR);
        SOLID_COLOR = a.getColor(R.styleable.FreeTimeLine_solid_color, FreeTimeLineUI.DEFAULT_SOLID_COLOR);
        HOLLOW_COLOR = a.getColor(R.styleable.FreeTimeLine_hollow_color, FreeTimeLineUI.DEFAULT_HOLLOW_COLOR);
        TOGGLE_COLOR = a.getColor(R.styleable.FreeTimeLine_toggle_color, FreeTimeLineUI.DEFAULT_TOGGLE_COLOR);
        a.recycle();

        mConfig = new FreeTimeLineConfig.Builder()
                .setTopType(TOP_TYPE)
                .setLineColor(LINE_COLOR)
                .setSolidColor(SOLID_COLOR)
                .setHollowColor(HOLLOW_COLOR)
                .setToggleColor(TOGGLE_COLOR)
                .build();

        mContent = new ListView(context);
        mContent.setDivider(null);
        mContent.setCacheColorHint(0);
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
                mAdapter = new FreeTimeLineAdapter(mElements, mConfig);
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

    public void setConfig(FreeTimeLineConfig config) {
        mConfig = config;
        if (mAdapter != null)
            mAdapter.notifyDataSetChanged();
    }
}
