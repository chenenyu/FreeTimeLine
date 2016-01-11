package com.chenenyu.ftldemo;

import com.chenenyu.freetimeline.FreeTimeLineElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock
 * Created by Cheney on 16/1/11.
 */
public class MockData {
    public static List<FreeTimeLineElement> getCustomData() {
        List<FreeTimeLineElement> elements = new ArrayList<>();
        FreeTimeLineElement element;
        element = new FreeTimeLineElement("Init FreeTimeLine", "init", "2016/01/06");
        elements.add(element);
        element = new FreeTimeLineElement("v1.0", "1.top_type \n2.line_color \n3.solid_color \n4.hollow_color \n5.toggle_color", "2016/01/08");
        elements.add(element);
        element = new FreeTimeLineElement("add type", "1:node type\n2:bottom type", "2016/01/09");
        elements.add(element);
        element = new FreeTimeLineElement("v2.0-alpha大量更新", "吸盘样式及字体大小和颜色的自定义", "2016/01/10");
        elements.add(element);
        element = new FreeTimeLineElement("v2.1", "大量重构及自定义显示隐藏折叠按钮", "2016/01/11");
        elements.add(element);
        return elements;
    }
}
