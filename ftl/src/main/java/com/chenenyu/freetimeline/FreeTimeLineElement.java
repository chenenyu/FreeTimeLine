package com.chenenyu.freetimeline;

import java.io.Serializable;

/**
 * FTL element.
 * Created by Cheney on 16/1/6.
 */
public class FreeTimeLineElement implements Serializable {

    private String parent;
    private String child;
    private String left;

    public FreeTimeLineElement() {

    }

    public FreeTimeLineElement(String parent, String child, String left) {
        this.parent = parent;
        this.child = child;
        this.left = left;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }
}
