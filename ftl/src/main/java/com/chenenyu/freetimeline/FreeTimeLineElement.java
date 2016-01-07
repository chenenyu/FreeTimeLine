package com.chenenyu.freetimeline;

/**
 * FTL element.
 * Created by Cheney on 16/1/6.
 */
public class FreeTimeLineElement {

    private String parent;
    private String child;
    private String left;

    public FreeTimeLineElement(String parent, String child, String left) {
        this.parent = parent;
        this.child = child;
        this.left = left;
    }

}
