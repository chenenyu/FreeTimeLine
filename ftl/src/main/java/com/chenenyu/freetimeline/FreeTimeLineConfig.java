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
    public final int TOP_TYPE;
    public final int NODE_TYPE;
    public final int BOTTOM_TYPE;

    public final int LINE_COLOR;
    public final int SOLID_COLOR;
    public final int HOLLOW_COLOR;
    public final int SUCKER_COLOR;
    public final int TOGGLE_COLOR;

    public final int LEFT_COLOR;
    public final float LEFT_SIZE;

    public final int PARENT_COLOR;
    public final float PARENT_SIZE;

    public final int CHILD_COLOR;
    public final float CHILD_SIZE;

    public final boolean SHOW_TOGGLE;

    private FreeTimeLineConfig(Builder builder) {
        this.TOP_TYPE = builder.top_type;
        this.NODE_TYPE = builder.node_type;
        this.BOTTOM_TYPE = builder.bottom_type;

        this.LINE_COLOR = builder.line_color;
        this.SOLID_COLOR = builder.solid_color;
        this.HOLLOW_COLOR = builder.hollow_color;
        this.SUCKER_COLOR = builder.sucker_color;
        this.TOGGLE_COLOR = builder.toggle_color;

        this.LEFT_COLOR = builder.left_color;
        this.LEFT_SIZE = builder.left_size;

        this.PARENT_COLOR = builder.parent_color;
        this.PARENT_SIZE = builder.parent_size;

        this.CHILD_COLOR = builder.child_color;
        this.CHILD_SIZE = builder.child_size;

        this.SHOW_TOGGLE = builder.show_toggle;
    }

    public static class Builder {
        private int top_type = FreeTimeLineUI.TOP_HOLLOW;
        private int node_type = FreeTimeLineUI.NODE_HOLLOW;
        private int bottom_type = FreeTimeLineUI.BOTTOM_SOLID;

        private int line_color = FreeTimeLineUI.DEFAULT_LINE_COLOR;
        private int solid_color = FreeTimeLineUI.DEFAULT_SOLID_COLOR;
        private int hollow_color = FreeTimeLineUI.DEFAULT_HOLLOW_COLOR;
        private int sucker_color = FreeTimeLineUI.DEFAULT_SUCKER_COLOR;
        private int toggle_color = FreeTimeLineUI.DEFAULT_TOGGLE_COLOR;

        private int left_color;
        private float left_size;

        private int parent_color;
        private float parent_size;

        private int child_color;
        private float child_size;

        private boolean show_toggle;

        public Builder() {

        }

        public Builder setTopType(int type) {
            top_type = type;
            return this;
        }

        public Builder setNodeType(int type) {
            node_type = type;
            return this;
        }

        public Builder setBottomType(int type) {
            bottom_type = type;
            return this;
        }

        public Builder setLineColor(int color) {
            line_color = color;
            return this;
        }

        public Builder setSolidColor(int color) {
            solid_color = color;
            return this;
        }

        public Builder setHollowColor(int color) {
            hollow_color = color;
            return this;
        }

        public Builder setSuckerColor(int color) {
            sucker_color = color;
            return this;
        }

        public Builder setToggleColor(int color) {
            toggle_color = color;
            return this;
        }


        public Builder setLeftColor(int color) {
            this.left_color = color;
            return this;
        }

        public Builder setLeftSize(float size) {
            this.left_size = size;
            return this;
        }

        public Builder setParentColor(int color) {
            this.parent_color = color;
            return this;
        }

        public Builder setParentSize(float size) {
            this.parent_size = size;
            return this;
        }

        public Builder setChildColor(int color) {
            this.child_color = color;
            return this;
        }

        public Builder setChildSize(float size) {
            this.child_size = size;
            return this;
        }

        public Builder setShowToggle(boolean show) {
            this.show_toggle = show;
            return this;
        }

        public FreeTimeLineConfig build() {
            return new FreeTimeLineConfig(this);
        }
    }

}
