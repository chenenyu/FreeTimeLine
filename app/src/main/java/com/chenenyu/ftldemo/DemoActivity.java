package com.chenenyu.ftldemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.chenenyu.freetimeline.FreeTimeLine;

public class DemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FreeTimeLine ftl = (FreeTimeLine) findViewById(R.id.ftl);
        ftl.setElements(MockData.getCustomData());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
