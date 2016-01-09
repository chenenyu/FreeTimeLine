package com.chenenyu.freetimeline;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
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
    private FreeTimeLineAdapter mAdapter;
    private FreeTimeLineConfig.Builder mBuilder;
    private FreeTimeLineConfig mConfig;

    private int TOP_TYPE; // 顶部结点的样式
    private int NODE_TYPE; // 中间结点的样式
    private int BOTTOM_TYPE; // 底部结点的样式

    private int LINE_COLOR; // 竖线的颜色
    private int SOLID_COLOR; // 实心圆的颜色
    private int HOLLOW_COLOR; // 空心圆的颜色
    private int SUCKER_COLOR; // 吸盘样式的颜色
    private int TOGGLE_COLOR; // +/-号的颜色

    private int LEFT_COLOR; // 左侧text的颜色
    private float LEFT_SIZE; // 左侧text的大小
    private int PARENT_COLOR; // 中间父文本的颜色
    private float PARENT_SIZE; // 中间富文本的大小
    private int CHILD_COLOR; // 中间子文本的颜色
    private float CHILD_SIZE; // 中间子文本的大小

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
        NODE_TYPE = a.getInt(R.styleable.FreeTimeLine_node_type, FreeTimeLineUI.NODE_HOLLOW);
        BOTTOM_TYPE = a.getInt(R.styleable.FreeTimeLine_bottom_type, FreeTimeLineUI.BOTTOM_HOLLOW);

        LINE_COLOR = a.getColor(R.styleable.FreeTimeLine_line_color, FreeTimeLineUI.DEFAULT_LINE_COLOR);
        SOLID_COLOR = a.getColor(R.styleable.FreeTimeLine_solid_color, FreeTimeLineUI.DEFAULT_SOLID_COLOR);
        HOLLOW_COLOR = a.getColor(R.styleable.FreeTimeLine_hollow_color, FreeTimeLineUI.DEFAULT_HOLLOW_COLOR);
        SUCKER_COLOR = a.getColor(R.styleable.FreeTimeLine_sucker_color, FreeTimeLineUI.DEFAULT_SUCKER_COLOR);
        TOGGLE_COLOR = a.getColor(R.styleable.FreeTimeLine_toggle_color, FreeTimeLineUI.DEFAULT_TOGGLE_COLOR);

        LEFT_COLOR = a.getColor(R.styleable.FreeTimeLine_left_color, getResources().getColor(android.R.color.black));
        LEFT_SIZE = a.getDimension(R.styleable.FreeTimeLine_left_size,
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
        PARENT_COLOR = a.getColor(R.styleable.FreeTimeLine_parent_color, getResources().getColor(android.R.color.black));
        PARENT_SIZE = a.getDimension(R.styleable.FreeTimeLine_parent_size,
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14, getResources().getDisplayMetrics()));
        Log.d("PARENT_SIZE", "" + PARENT_SIZE);
        CHILD_COLOR = a.getColor(R.styleable.FreeTimeLine_child_color, getResources().getColor(android.R.color.black));
        CHILD_SIZE = a.getDimension(R.styleable.FreeTimeLine_child_size,
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
        Log.d("CHILD_SIZE", "" + CHILD_SIZE);
        a.recycle();

        mBuilder = new FreeTimeLineConfig.Builder()
                .setTopType(TOP_TYPE)
                .setNodeType(NODE_TYPE)
                .setBottomType(BOTTOM_TYPE)
                .setLineColor(LINE_COLOR)
                .setSolidColor(SOLID_COLOR)
                .setHollowColor(HOLLOW_COLOR)
                .setSuckerColor(SUCKER_COLOR)
                .setToggleColor(TOGGLE_COLOR)
                .setLeftColor(LEFT_COLOR)
                .setLeftSize(LEFT_SIZE)
                .setParentColor(PARENT_COLOR)
                .setParentSize(PARENT_SIZE)
                .setChildColor(CHILD_COLOR)
                .setChildSize(CHILD_SIZE);
        mConfig = mBuilder.build();

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
        if (elements != null) {
            if (mAdapter == null) {
                mAdapter = new FreeTimeLineAdapter(elements, mConfig);
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
