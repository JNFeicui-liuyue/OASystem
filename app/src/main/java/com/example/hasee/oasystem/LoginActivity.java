package com.example.hasee.oasystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.login_edt_username)
    EditText mName;
    @Bind(R.id.login_edt_pwd)
    EditText mPwd;
    @Bind(R.id.login_rememberPassword)
    CheckBox mJizhumima;
    @Bind(R.id.login_AutoLogin)
    CheckBox mZidongdenglu;
//    @Bind(R.id.submit) Button mBtnLogin;

    private String userName; // 用来存储用户名
    private String passWord; // 用来存储密码

    private SharedPreferences sp;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        MyApplication.getInstance().addActivity(this);

        ButterKnife.bind(this);

        sp = getSharedPreferences("userInfo",MODE_PRIVATE);

        //判断记住密码多选框的状态
        if (sp.getBoolean("ISCHECK",false)){
            //设置默认是记住密码状态
            mJizhumima.setChecked(true);
            mName.setText(sp.getString("USER_NAME",""));
            mPwd.setText(sp.getString("PASSWORD",""));
            //判断自动登录多选框状态
            if (sp.getBoolean("AUTO_ISCHECK",false)){
                //设置默认是自动登录状态
                mZidongdenglu.setChecked(true);
                //跳转界面
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }

            //监听记住密码多选框按钮事件
//            mJizhumima.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    if (mJizhumima.isChecked()){
//                        Log.d(TAG, "记住密码已选中");
//                        sp.edit().putBoolean("ISCHECK",true).commit();
//
//                    }else {
//                        Log.d(TAG, "记住密码没有选中");
//                        sp.edit().putBoolean("ISCHECK",false).commit();
//                    }
//                }
//            });

            //监听自动登录多选框事件
//            mZidongdenglu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    if (mZidongdenglu.isChecked()){
//                        Log.d(TAG, "自动登录已选中");
//                        sp.edit().putBoolean("AUTO_ISCHECK",true).commit();
//                    }else {
//                        Log.d(TAG, "自动登录没有选中");
//                        sp.edit().putBoolean("AUTO_ISCHECK",false).commit();
//                    }
//                }
//            });
        }


//        SharedPreferences sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
//
//        if (sharedPreferences != null && ! "".equals(sharedPreferences)){
//            String zh = sharedPreferences.getString("account","");
//            mName.setText(zh);
//            boolean jizhu = sharedPreferences.getBoolean("jizhu",false);
//            if (jizhu){
//                String  mima = sharedPreferences.getString()
//            }
//        }
//        if (sp != null && ! "".equals(sp)){
//            String zh = sp.getString("account","");
//            boolean isChecked = sp.getBoolean("jizhu",false);
//            mName.setText(zh);
//            if (isChecked){
//                String mm = sp.getString("password","");
//                mPwd.setText(mm);
//                mJizhumima.setChecked(true);
//                if (sp.getBoolean("zidong",false)){
//                    mZidongdenglu.setChecked(sp.getBoolean("zidong",false));
//                }
//            }
//        }
    }

    @OnClick(R.id.login_img_logbtn)
    public void login(){
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        userName = mName.getText().toString();
        passWord = mPwd.getText().toString();
        if (userName.trim() == null || "".equals(userName.trim())){
            Toast.makeText(LoginActivity.this, "账号不能为空", Toast.LENGTH_SHORT).show();
        }else if (passWord.trim() == null || "".equals(passWord.trim())){
            Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
        }else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("USER_NAME", userName);
            editor.putBoolean("ISCHECK",mJizhumima.isChecked());
            editor.putBoolean("AUTO_CHECK",mZidongdenglu.isChecked());
            if (mJizhumima.isChecked()){
                //记住用户名、密码
                editor.putString("USER_NAME", userName);
                editor.putString("PASSWORD", passWord);
            }
            editor.commit();
            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            String ming = sharedPreferences.getString("NAME","");
            Log.d(TAG, "login: " + ming);
        }
    }

    //监听记住密码多选框按钮事件
    @OnCheckedChanged(R.id.login_rememberPassword)
    public void jizhumima(){
        if (mJizhumima.isChecked()){
            Log.d(TAG, "记住密码已选中");
            sp.edit().putBoolean("ISCHECK",true).commit();

        }else {
            Log.d(TAG, "记住密码没有选中");
            sp.edit().putBoolean("ISCHECK",false).commit();
        }
    }

    //监听自动登录多选框事件
    @OnCheckedChanged(R.id.login_AutoLogin)
    public void zidongdenglu(){
        if (mZidongdenglu.isChecked()){
            Log.d(TAG, "自动登录已选中");
            sp.edit().putBoolean("AUTO_ISCHECK",true).commit();
        }else {
            Log.d(TAG, "自动登录没有选中");
            sp.edit().putBoolean("AUTO_ISCHECK",false).commit();
        }
    }
}
