package com.chenenyu.freetimeline;

import com.chenenyu.freetimeline.internal.FreeTimeLineUI;

import java.io.Serializable;

/**
 * <p>
 * All configurations for <a href="https://github.com/chenenyu/FreeTimeLine">FreeTimeLine</a>.
 * </p>
 * Created by Cheney on 16/1/7.
 */
public class FreeTimeLineConfig implements Serializable {
    private int top_type = FreeTimeLineUI.TOP_HOLLOW;
    private int node_type = FreeTimeLineUI.NODE_HOLLOW;
    private int bottom_type = FreeTimeLineUI.BOTTOM_SOLID;

    private int line_color = FreeTimeLineUI.DEFAULT_LINE_COLOR;
    private int solid_color = FreeTimeLineUI.DEFAULT_SOLID_COLOR;
    private int hollow_color = FreeTimeLineUI.DEFAULT_HOLLOW_COLOR;
    private int sucker_color = FreeTimeLineUI.DEFAULT_SUCKER_COLOR;
    private int toggle_color = FreeTimeLineUI.DEFAULT_TOGGLE_COLOR;

    private int left_color = FreeTimeLineUI.DEFAULT_LEFT_COLOR;
    private float left_size = FreeTimeLineUI.DEFAULT_LEFT_SIZE;

    private int parent_color = FreeTimeLineUI.DEFAULT_PARENT_COLOR;
    private float parent_size = FreeTimeLineUI.DEFAULT_PARENT_SIZE;

    private int child_color = FreeTimeLineUI.DEFAULT_CHILD_COLOR;
    private float child_size = FreeTimeLineUI.DEFAULT_CHILD_SIZE;

    private boolean show_toggle = FreeTimeLineUI.DEFAULT_SHOW_TOGGLE;


    public FreeTimeLineConfig setTopType(int type) {
        top_type = type;
        return this;
    }

    public FreeTimeLineConfig setNodeType(int type) {
        node_type = type;
        return this;
    }

    public FreeTimeLineConfig setBottomType(int type) {
        bottom_type = type;
        return this;
    }

    public FreeTimeLineConfig setLineColor(int color) {
        line_color = color;
        return this;
    }

    public FreeTimeLineConfig setSolidColor(int color) {
        solid_color = color;
        return this;
    }

    public FreeTimeLineConfig setHollowColor(int color) {
        hollow_color = color;
        return this;
    }

    public FreeTimeLineConfig setSuckerColor(int color) {
        sucker_color = color;
        return this;
    }

    public FreeTimeLineConfig setToggleColor(int color) {
        toggle_color = color;
        return this;
    }


    public FreeTimeLineConfig setLeftColor(int color) {
        this.left_color = color;
        return this;
    }

    public FreeTimeLineConfig setLeftSize(float size) {
        this.left_size = size;
        return this;
    }

    public FreeTimeLineConfig setParentColor(int color) {
        this.parent_color = color;
        return this;
    }

    public FreeTimeLineConfig setParentSize(float size) {
        this.parent_size = size;
        return this;
    }

    public FreeTimeLineConfig setChildColor(int color) {
        this.child_color = color;
        return this;
    }

    public FreeTimeLineConfig setChildSize(float size) {
        this.child_size = size;
        return this;
    }

    public FreeTimeLineConfig setShowToggle(boolean show) {
        this.show_toggle = show;
        return this;
    }

    public int getTopType() {
        return top_type;
    }

    public int getNodeType() {
        return node_type;
    }

    public int getBottomType() {
        return bottom_type;
    }

    public int getLineColor() {
        return line_color;
    }

    public int getSolidColor() {
        return solid_color;
    }

    public int getHollowColor() {
        return hollow_color;
    }

    public int getSuckerColor() {
        return sucker_color;
    }

    public int getToggleColor() {
        return toggle_color;
    }

    public int getLeftColor() {
        return left_color;
    }

    public float getLeftSize() {
        return left_size;
    }

    public int getParentColor() {
        return parent_color;
    }

    public float getParentSize() {
        return parent_size;
    }

    public int getChildColor() {
        return child_color;
    }

    public float getChildSize() {
        return child_size;
    }

    public boolean isShowToggle() {
        return show_toggle;
    }
}
