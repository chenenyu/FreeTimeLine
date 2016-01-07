package com.chenenyu.freetimeline;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chenenyu.freetimeline.view.ConnectorView;
import com.chenenyu.freetimeline.view.ToggleView;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * <a href="https://github.com/chenenyu/FreeTimeLine">Source code</a>
 * </p>
 * Created by Cheney on 16/1/6.
 */
public class FreeTimeLineAdapter extends BaseAdapter {

    private boolean[] opened;
    private final boolean SHOW_LEFT;
    private final boolean SHOW_TOGGLE;
    private List<FreeTimeLineElement> mElements = Collections.emptyList();

    public FreeTimeLineAdapter(Context context, List<FreeTimeLineElement> elements) {
        mElements = elements;
        opened = new boolean[mElements.size()];
        SHOW_LEFT = showLeft();
        // TODO: 16/1/7 ?
        SHOW_TOGGLE = true;
    }

    @Override
    public int getCount() {
        return mElements.size();
    }

    @Override
    public Object getItem(int position) {
        return mElements.size() == 0 ? null : mElements.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.__ftl_node_row, parent, false);
        }
        TextView left = (TextView) convertView.findViewById(R.id.__ftl_row_left_text);
        ConnectorView connectorView = (ConnectorView) convertView.findViewById(R.id.__ftl_row_connector);
        TextView middle = (TextView) convertView.findViewById(R.id.__ftl_row_middle_text);
        ToggleView toggleView = (ToggleView) convertView.findViewById(R.id.__ftl_row_toggle);

        if (SHOW_LEFT) {
            left.setVisibility(View.VISIBLE);
            left.setText(mElements.get(position).getLeft());
        }

        if (position == 0) {
            // TODO: 16/1/7 判断是普通样式还是吸盘样式
            connectorView.setType(ConnectorView.Type.NORMAL_TOP);
        } else if (position == mElements.size() - 1) {
            connectorView.setType(ConnectorView.Type.BOTTOM);
        } else {
            connectorView.setType(ConnectorView.Type.NODE);
        }

        if (SHOW_TOGGLE) {
            toggleView.setVisibility(View.VISIBLE);
            toggleView.setOpened(opened[position]);
            middle.setText(Html.fromHtml(elementToHtmlString(mElements.get(position), opened[position])));
        } else {
            toggleView.setVisibility(View.GONE);
            middle.setText(mElements.get(position).getParent());
        }

        return convertView;
    }

    private boolean showLeft() {
        for (FreeTimeLineElement element : mElements) {
            if (!TextUtils.isEmpty(element.getLeft()))
                return true;
        }
        return false;
    }

    public void toggleRow(int position) {
        this.opened[position] = !this.opened[position];
        notifyDataSetChanged();
    }

    private String elementToHtmlString(FreeTimeLineElement element, boolean opened) {
        if (TextUtils.isEmpty(element.getParent())) {
            return "";
        }
        if (opened && !TextUtils.isEmpty(element.getChild())) {
            return "<font color=\'#80000000\'>" + element.getParent() + "</font><br><font color=\'#80FFFFFF\'>"
                    + element.getChild() + "</font>";
        } else {
            return element.getParent();
        }
    }

}
