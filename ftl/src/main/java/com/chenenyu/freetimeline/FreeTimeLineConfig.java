package com.chenenyu.freetimeline;

/**
 * <p>
 * All configurations for <a href="https://github.com/chenenyu/FreeTimeLine">FreeTimeLine</a>.
 * </p>
 * Created by Cheney on 16/1/7.
 */
public class FreeTimeLineConfig {
    public final int TOP_TYPE;
    public final int LINE_COLOR;
    public final int SOLID_COLOR;
    public final int HOLLOW_COLOR;
    public final int TOGGLE_COLOR;

    private FreeTimeLineConfig(Builder builder) {
        this.TOP_TYPE = builder.top_type;
        this.LINE_COLOR = builder.line_color;
        this.SOLID_COLOR = builder.solid_color;
        this.HOLLOW_COLOR = builder.hollow_color;
        this.TOGGLE_COLOR = builder.toggle_color;
    }

    public static class Builder {
        private int top_type;
        private int line_color;
        private int solid_color;
        private int hollow_color;
        private int toggle_color;

        public Builder() {

        }

        public Builder setTopType(int type) {
            top_type = type;
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

        public Builder setToggleColor(int color) {
            toggle_color = color;
            return this;
        }

        public FreeTimeLineConfig build() {
            return new FreeTimeLineConfig(this);
        }
    }

}
