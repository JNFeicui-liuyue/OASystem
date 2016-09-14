package com.example.hasee.oasystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class ShqglActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shqgl);

        ButterKnife.bind(this);
        MyApplication.getInstance().addActivity(this);
    }

    @OnClick({R.id.sqgl_hysq_imgbtn,R.id.sqgl_bxsq_imgbtn,
            R.id.sqgl_ycsq_imgbtn,R.id.sqgl_jbsq_imgbtn,
            R.id.sqgl_jksq_imgbtn,R.id.sqgl_qjsq_imgbtn})
    public void bangongshenqing(View view){
        switch (view.getId()){
            case R.id.sqgl_hysq_imgbtn :
                Intent intent1 = new Intent(this,HyshqActivity.class);
                startActivity(intent1);
                break;
            case R.id.sqgl_bxsq_imgbtn :
                Intent intent2 = new Intent(this,BxshqActivity.class);
                startActivity(intent2);
                break;
            case R.id.sqgl_ycsq_imgbtn :
                Intent intent3 = new Intent(this,YchshqActivity.class);
                startActivity(intent3);
                break;
            case R.id.sqgl_jbsq_imgbtn :
                Intent intent4 = new Intent(this,JbshqActivity.class);
                startActivity(intent4);
                break;
            case R.id.sqgl_jksq_imgbtn :
                Intent intent5 = new Intent(this,JkshqActivity.class);
                startActivity(intent5);
                break;
            case R.id.sqgl_qjsq_imgbtn :
                Intent intent6 = new Intent(this,QjshqActivity.class);
                startActivity(intent6);
                break;
        }

    }

    @OnClick(R.id.back)
    public void back(){
        finish();
    }
}
