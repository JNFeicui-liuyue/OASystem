package com.example.hasee.oasystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class QjshqActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qjshq);

        ButterKnife.bind(this);
        MyApplication.getInstance().addActivity(this);
    }

    @OnClick(R.id.back)
    public void back(){
        finish();
    }
}
