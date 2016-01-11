package com.chenenyu.ftldemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.chenenyu.freetimeline.FreeTimeLine;
import com.chenenyu.freetimeline.FreeTimeLineConfig;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CustomActivity extends AppCompatActivity {

    @Bind(R.id.ftl)
    FreeTimeLine mFtl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        FreeTimeLineConfig config = (FreeTimeLineConfig) intent.getSerializableExtra("config");
        mFtl.setConfig(config);
        mFtl.setElements(MockData.getCustomData());
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
