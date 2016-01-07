package com.chenenyu.freetimeline;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * <a href="https://github.com/chenenyu">Source code</a>
 * </p>
 * Created by Cheney on 16/1/6.
 */
public class FreeTimeLineAdapter extends BaseAdapter {

    private boolean[] opened = new boolean[0];
    private List<FreeTimeLineElement> elements = Collections.emptyList();

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
