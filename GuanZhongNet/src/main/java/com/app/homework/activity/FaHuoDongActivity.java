package com.app.homework.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.app.homework.R;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FaHuoDongActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fa_huo_dong);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.faHuoDong, R.id.cha})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.faHuoDong:
                //跳转编辑页
                startActivity(new Intent(FaHuoDongActivity.this,BianJiActivity.class));
                break;

            case R.id.cha:

                //销毁当前页面
                startActivity(new Intent(FaHuoDongActivity.this,HomeActivity.class));
                finish();
                break;
        }
    }
}
