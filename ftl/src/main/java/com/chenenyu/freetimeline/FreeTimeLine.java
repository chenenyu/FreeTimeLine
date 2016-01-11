package com.chenenyu.freetimeline;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
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
    private FreeTimeLineConfig mConfig;

    private int TOP_TYPE; // 顶部结点的样式
    private int NODE_TYPE; // 中间节点的样式
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

    private boolean SHOW_TOGGLE; // 是否显示更多按钮

    public FreeTimeLine(Context context) {
        this(context, null);
    }

    public FreeTimeLine(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FreeTimeLine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FreeTimeLine, defStyleAttr, 0);
        TOP_TYPE = a.getInt(R.styleable.FreeTimeLine_top_type, FreeTimeLineUI.TOP_HOLLOW);
        NODE_TYPE = a.getInt(R.styleable.FreeTimeLine_node_type, FreeTimeLineUI.NODE_HOLLOW);
        BOTTOM_TYPE = a.getInt(R.styleable.FreeTimeLine_bottom_type, FreeTimeLineUI.BOTTOM_HOLLOW);

        LINE_COLOR = a.getColor(R.styleable.FreeTimeLine_line_color, FreeTimeLineUI.DEFAULT_LINE_COLOR);
        SOLID_COLOR = a.getColor(R.styleable.FreeTimeLine_solid_color, FreeTimeLineUI.DEFAULT_SOLID_COLOR);
        HOLLOW_COLOR = a.getColor(R.styleable.FreeTimeLine_hollow_color, FreeTimeLineUI.DEFAULT_HOLLOW_COLOR);
        SUCKER_COLOR = a.getColor(R.styleable.FreeTimeLine_sucker_color, FreeTimeLineUI.DEFAULT_SUCKER_COLOR);
        TOGGLE_COLOR = a.getColor(R.styleable.FreeTimeLine_toggle_color, FreeTimeLineUI.DEFAULT_TOGGLE_COLOR);

        LEFT_COLOR = a.getColor(R.styleable.FreeTimeLine_left_color, FreeTimeLineUI.DEFAULT_LEFT_COLOR);
        LEFT_SIZE = a.getDimension(R.styleable.FreeTimeLine_left_size, TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, FreeTimeLineUI.DEFAULT_LEFT_SIZE, getResources().getDisplayMetrics()));
        LEFT_SIZE = LEFT_SIZE / getResources().getDisplayMetrics().scaledDensity;

        PARENT_COLOR = a.getColor(R.styleable.FreeTimeLine_parent_color, FreeTimeLineUI.DEFAULT_PARENT_COLOR);
        PARENT_SIZE = a.getDimension(R.styleable.FreeTimeLine_parent_size, TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, FreeTimeLineUI.DEFAULT_PARENT_SIZE, getResources().getDisplayMetrics()));
        PARENT_SIZE = PARENT_SIZE / getResources().getDisplayMetrics().scaledDensity;

        CHILD_COLOR = a.getColor(R.styleable.FreeTimeLine_child_color, FreeTimeLineUI.DEFAULT_CHILD_COLOR);
        CHILD_SIZE = a.getDimension(R.styleable.FreeTimeLine_child_size, TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, FreeTimeLineUI.DEFAULT_CHILD_SIZE, getResources().getDisplayMetrics()));
        CHILD_SIZE = CHILD_SIZE / getResources().getDisplayMetrics().scaledDensity;

        SHOW_TOGGLE = a.getBoolean(R.styleable.FreeTimeLine_show_toggle, FreeTimeLineUI.DEFAULT_SHOW_TOGGLE);
        a.recycle();

        mConfig = new FreeTimeLineConfig()
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
                .setChildSize(CHILD_SIZE)
                .setShowToggle(SHOW_TOGGLE);

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
            throw new IllegalStateException("FreeTimeLine can't have any child added manually.");
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

    public FreeTimeLineConfig getConfig() {
        return mConfig;
    }

    public void setConfig(FreeTimeLineConfig config) {
        mConfig = config;
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }

    public void setTopType(int type) {
        if (mAdapter != null && TOP_TYPE != type) {
            mConfig.setTopType(type);
            mAdapter.notifyDataSetChanged();
        }
    }

    public void setNodeType(int type) {
        if (mAdapter != null && NODE_TYPE != type) {
            mConfig.setNodeType(type);
            mAdapter.notifyDataSetChanged();
        }
    }

    public void setBottomType(int type) {
        if (mAdapter != null && BOTTOM_TYPE != type) {
            mConfig.setBottomType(type);
            mAdapter.notifyDataSetChanged();
        }
    }

    public void setLineColor(int color) {
        if (mAdapter != null && LINE_COLOR != color) {
            mConfig.setLineColor(color);
            mAdapter.notifyDataSetChanged();
        }
    }

    public void setSolidColor(int color) {
        if (mAdapter != null && SOLID_COLOR != color) {
            mConfig.setSolidColor(color);
            mAdapter.notifyDataSetChanged();
        }
    }

    public void setHollowColor(int color) {
        if (mAdapter != null && HOLLOW_COLOR != color) {
            mConfig.setHollowColor(color);
            mAdapter.notifyDataSetChanged();
        }
    }

    public void setSuckerColor(int color) {
        if (mAdapter != null && SUCKER_COLOR != color) {
            mConfig.setSuckerColor(color);
            mAdapter.notifyDataSetChanged();
        }
    }

    public void setToggleColor(int color) {
        if (mAdapter != null && TOGGLE_COLOR != color) {
            mConfig.setToggleColor(color);
            mAdapter.notifyDataSetChanged();
        }
    }


    public void setLeftColor(int color) {
        if (mAdapter != null && LEFT_COLOR != color) {
            mConfig.setLeftColor(color);
            mAdapter.notifyDataSetChanged();
        }
    }

    public void setLeftSize(float size) {
        if (mAdapter != null && LEFT_SIZE != size) {
            mConfig.setLeftSize(size);
            mAdapter.notifyDataSetChanged();
        }
    }

    public void setParentColor(int color) {
        if (mAdapter != null && PARENT_COLOR != color) {
            mConfig.setParentColor(color);
            mAdapter.notifyDataSetChanged();
        }
    }

    public void setParentSize(float size) {
        if (mAdapter != null && PARENT_SIZE != size) {
            mConfig.setParentSize(size);
            mAdapter.notifyDataSetChanged();
        }
    }

    public void setChildColor(int color) {
        if (mAdapter != null && CHILD_COLOR != color) {
            mConfig.setChildColor(color);
            mAdapter.notifyDataSetChanged();
        }
    }

    public void setChildSize(float size) {
        if (mAdapter != null && CHILD_SIZE != size) {
            mConfig.setChildSize(size);
            mAdapter.notifyDataSetChanged();
        }
    }

    public void setShowToggle(boolean show) {
        if (mAdapter != null && SHOW_TOGGLE != show) {
            mConfig.setShowToggle(show);
            mAdapter.notifyDataSetChanged();
        }
    }

}
