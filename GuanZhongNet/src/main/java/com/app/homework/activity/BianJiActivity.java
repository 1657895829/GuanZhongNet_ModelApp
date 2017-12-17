package com.app.homework.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import com.app.homework.R;
import butterknife.ButterKnife;
import butterknife.OnClick;

//预览 和 发布 页面
public class BianJiActivity extends AppCompatActivity {
    private SharedPreferences.Editor edit;
    private SharedPreferences config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bian_ji);
        ButterKnife.bind(this);

        //sharedpreferences存取数据
        config = this.getSharedPreferences("config", 0);
        edit = config.edit();

        Toast.makeText(BianJiActivity.this,"欢迎进入 预览 页面",Toast.LENGTH_SHORT).show();

    }

    @OnClick(R.id.cha)
    public void onViewClicked() {
        //跳转发布页
        Toast.makeText(BianJiActivity.this,"退出",Toast.LENGTH_SHORT).show();

        startActivity(new Intent(BianJiActivity.this, FaHuoDongActivity.class));
        finish();
    }

    @OnClick({R.id.huo_yl, R.id.huo_fb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.huo_fb:
                //判断用户uid是否存在
                String uid = config.getString("uid", null);

                if (uid == null) {  //不存在就登录
                    startActivity(new Intent(BianJiActivity.this, LoginActivity.class));
                    finish();
                }else {             //存在就提示
                    Toast.makeText(BianJiActivity.this,"欢迎进入 发布 页面",Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.huo_yl:

                Toast.makeText(BianJiActivity.this,"欢迎进入 预览 页面",Toast.LENGTH_SHORT).show();

                break;
        }
    }

}
