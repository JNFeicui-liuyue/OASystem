package com.example.hasee.oasystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.navigationView) NavigationView mNavigationView;//侧滑菜单视图
    @Bind(R.id.recyclerview)RecyclerView mRecyclerView;

    //让每一个item显示一个String
    private List<ItemBean> mDatas;
    private SimpleAdapter mAdapter;

    private int[] ivPic = {R.drawable.lx_08,R.drawable.lx_010,
                            R.drawable.lx_012, R.drawable.lx_013,
                            R.drawable.lx_020};

    private String[] tvTitle = {"请假审批","加班审批","公车审批","申请管理",
            "通知公告"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyApplication.getInstance().addActivity(this);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();

        ButterKnife.bind(this);

        //ActionBar处理
//        setSupportActionBar(mToolbar);
        //设置ToolBar左上角切换侧滑菜单的按钮
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                        mDrawerLayout,mToolbar,R.string.navigation_drawer_open,
                        R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        //同步
        toggle.syncState();

        //设置mNavigationView的监听事件
        mNavigationView.setNavigationItemSelectedListener(this);

        initRecyclerView();
        initDatas();
        mAdapter = new SimpleAdapter(this,mDatas);
        mRecyclerView.setAdapter(mAdapter);

        //item点击事件
        mAdapter.setOnItemClickListener(new SimpleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this,mDatas.get(position).title, Toast.LENGTH_SHORT).show();

                switch (position){
                    case 0 :
                        Intent intent1 = new Intent(MainActivity.this,QjshpActivity.class);
                        startActivity(intent1);
                        break;
                    case 1 :
                        Intent intent2 = new Intent(MainActivity.this,JbshpActivity.class);
                        startActivity(intent2);
                        break;
                    case 2 :
                        Intent intent3 = new Intent(MainActivity.this,YchshpActivity.class);
                        startActivity(intent3);
                        break;
                    case 3 :
                        Intent intent4 = new Intent(MainActivity.this,ShqglActivity.class);
                        startActivity(intent4);
                        break;
                    case 4 :
                        Intent intent5 = new Intent(MainActivity.this,TzhggActivity.class);
                        startActivity(intent5);
                        break;
                }
            }
        });
    }

    private void initRecyclerView() {

        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


    }

    //为 mDatas 赋值
    private void initDatas() {

        mDatas = new ArrayList<ItemBean>();
        for (int i = 0; i < 5; i++) {
            mDatas.add(new ItemBean(ivPic[i],tvTitle[i]));
        }


    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.zhuxiao :
                Intent intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.tuichu :
                MyApplication.getInstance().exit();

        }
        return false;
    }

    @Override
    public void onBackPressed() {

            exitTwice();
    }

    //两次退出
    private boolean isFirstExit=true;
    private void exitTwice(){
        if(isFirstExit){
            Toast.makeText(this, "再按一次退出！", Toast.LENGTH_SHORT).show();
            isFirstExit=false;
            new Thread(){
                public void run() {
                    try {
                        Thread.sleep(3000);
                        isFirstExit=true;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                };
            }.start();
        }else{
            finish();
        }
    }
}
