package com.chenenyu.ftldemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chenenyu.freetimeline.FreeTimeLine;
import com.chenenyu.freetimeline.FreeTimeLineElement;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<FreeTimeLineElement> elements = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            FreeTimeLineElement element = new FreeTimeLineElement();
            element.setLeft("2015年6月23日");
            element.setParent("parent" + i);
            element.setChild("child" + i);
            elements.add(element);
        }
        FreeTimeLine ftl = (FreeTimeLine) findViewById(R.id.ftl);
        ftl.setElements(elements);
    }
}
