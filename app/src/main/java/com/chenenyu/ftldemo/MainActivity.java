package com.chenenyu.ftldemo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.chenenyu.freetimeline.FreeTimeLineConfig;
import com.chenenyu.freetimeline.internal.FreeTimeLineUI;
import com.github.danielnilsson9.colorpickerview.dialog.ColorPickerDialogFragment;
import com.github.danielnilsson9.colorpickerview.dialog.ColorPickerDialogFragment.ColorPickerDialogListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ColorPickerDialogListener, View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private ColorPickerDialogFragment lineColorFragment, solidColorFragment, hollowColorFragment;
    private int lineColor = FreeTimeLineUI.DEFAULT_LINE_COLOR;
    private int solidColor = FreeTimeLineUI.DEFAULT_SOLID_COLOR;
    private int hollowColor = FreeTimeLineUI.DEFAULT_HOLLOW_COLOR;
    private int topType = FreeTimeLineUI.TOP_HOLLOW;
    private int nodeType = FreeTimeLineUI.NODE_HOLLOW;
    private int bottomType = FreeTimeLineUI.BOTTOM_HOLLOW;
    private boolean showToggle = true;

    @Bind(R.id.rg_top_type)
    RadioGroup mRgTopType;

    @Bind(R.id.rg_node_type)
    RadioGroup mRgNodeType;

    @Bind(R.id.rg_bottom_type)
    RadioGroup mRgBottomType;

    @Bind(R.id.rg_show_toggle)
    RadioGroup mRgShowToggle;

    @Bind(R.id.btn_line_color)
    Button mBtnLineColor;
    @Bind(R.id.btn_solid_color)
    Button mBtnSolidColor;
    @Bind(R.id.btn_hollow_color)
    Button mBtnHollowColor;

    @Bind(R.id.fab)
    FloatingActionButton mFab;
    @Bind(R.id.btn_ok)
    Button mBtnOk;
    @Bind(R.id.btn_demo)
    Button mBtnDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        lineColorFragment = ColorPickerDialogFragment.newInstance(0, getString(R.string.line_color), null, Color.WHITE, true);
        solidColorFragment = ColorPickerDialogFragment.newInstance(1, getString(R.string.solid_color), null, Color.WHITE, true);
        hollowColorFragment = ColorPickerDialogFragment.newInstance(2, getString(R.string.hollow_color), null, Color.WHITE, true);

        mRgTopType.setOnCheckedChangeListener(this);
        mRgNodeType.setOnCheckedChangeListener(this);
        mRgBottomType.setOnCheckedChangeListener(this);
        mRgShowToggle.setOnCheckedChangeListener(this);

        mFab.setOnClickListener(this);
        mBtnLineColor.setOnClickListener(this);
        mBtnSolidColor.setOnClickListener(this);
        mBtnHollowColor.setOnClickListener(this);
        mBtnOk.setOnClickListener(this);
        mBtnDemo.setOnClickListener(this);
    }

    @Override
    public void onColorSelected(int dialogId, int color) {
        switch (dialogId) {
            case 0:
                lineColor = color;
                mBtnLineColor.setTextColor(lineColor);
                break;
            case 1:
                solidColor = color;
                mBtnSolidColor.setTextColor(solidColor);
                break;
            case 2:
                hollowColor = color;
                mBtnHollowColor.setTextColor(hollowColor);
                break;
        }
    }

    @Override
    public void onDialogDismissed(int dialogId) {
//        Log.d("onDialogDismissed", dialogId + "");
    }

    @Override
    public void onClick(View v) {
        if (v == mFab) {
            Snackbar.make(v, getString(R.string.snack_info), Snackbar.LENGTH_LONG).show();
        } else if (v == mBtnLineColor) {
            lineColorFragment.show(getFragmentManager(), "lineColorFragment");
        } else if (v == mBtnSolidColor) {
            solidColorFragment.show(getFragmentManager(), "solidColorFragment");
        } else if (v == mBtnHollowColor) {
            hollowColorFragment.show(getFragmentManager(), "hollowColorFragment");
        } else if (v == mBtnOk) {
            FreeTimeLineConfig config = new FreeTimeLineConfig();
            config.setTopType(topType)
                    .setNodeType(nodeType)
                    .setBottomType(bottomType)
                    .setLineColor(lineColor)
                    .setHollowColor(hollowColor)
                    .setSolidColor(solidColor)
                    .setShowToggle(showToggle);
            Intent intent = new Intent(MainActivity.this, CustomActivity.class);
            intent.putExtra("config", config);
            startActivity(intent);
        } else if (v == mBtnDemo) {
            Intent intent = new Intent(MainActivity.this, DemoActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (group == mRgTopType) {
            switch (checkedId) {
                case R.id.rb_top_sucker:
                    topType = FreeTimeLineUI.TOP_SUCKER;
                    break;
                case R.id.rb_top_solid:
                    topType = FreeTimeLineUI.TOP_SOLID;
                    break;
                case R.id.rb_top_hollow:
                    topType = FreeTimeLineUI.TOP_HOLLOW;
                    break;
            }
        } else if (group == mRgNodeType) {
            switch (checkedId) {
                case R.id.rb_node_hollow:
                    nodeType = FreeTimeLineUI.NODE_HOLLOW;
                    break;
                case R.id.rb_node_solid:
                    nodeType = FreeTimeLineUI.NODE_SOLID;
                    break;
            }
        } else if (group == mRgBottomType) {
            switch (checkedId) {
                case R.id.rb_bottom_hollow:
                    bottomType = FreeTimeLineUI.BOTTOM_HOLLOW;
                    break;
                case R.id.rb_bottom_solid:
                    bottomType = FreeTimeLineUI.BOTTOM_SOLID;
                    break;
            }
        } else if (group == mRgShowToggle) {
            switch (checkedId) {
                case R.id.rb_show_toggle:
                    showToggle = true;
                    break;
                case R.id.rb_hide_toggle:
                    showToggle = false;
                    break;
            }
        }
    }
}
