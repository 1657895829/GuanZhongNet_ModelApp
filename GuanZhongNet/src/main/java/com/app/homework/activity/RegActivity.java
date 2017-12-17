package com.app.homework.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.homework.R;
import com.app.homework.bean.ZhuceBean;
import com.app.homework.presenter.RegPresenter;
import com.app.homework.view.RegViewListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//注册页面
public class RegActivity extends AppCompatActivity implements RegViewListener {
    @BindView(R.id.zhuce_cha)
    TextView zhuceCha;
    @BindView(R.id.zhuce_zh)
    EditText zhuceZh;
    @BindView(R.id.zhuce_pwd)
    EditText zhucePwd;
    @BindView(R.id.zhuce_btn)
    Button zhuceBtn;
    private RegPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        ButterKnife.bind(this);

        //调用p层
        presenter = new RegPresenter(this);
    }

    @OnClick({R.id.zhuce_cha, R.id.zhuce_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zhuce_cha:
                finish();
                break;
            case R.id.zhuce_btn:
                //https://www.zhaoapi.cn/user/reg?mobile=15810680959&password=123456
                if (!TextUtils.isEmpty(zhuceZh.getText().toString()) && !TextUtils.isEmpty(zhucePwd.getText().toString())) {

                    zhuceBtn.setBackgroundColor(Color.parseColor("#ff6102"));          //信息都不为空时登录按钮为橙色

                    // mvp请求注册的接口
                    presenter.getData(zhuceZh.getText().toString(), zhucePwd.getText().toString());

                } else {

                    Toast.makeText(this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void success(ZhuceBean zhuceBean) {
        Toast.makeText(this, "注册结果：      " + zhuceBean.getMsg(), Toast.LENGTH_SHORT).show();

        if (zhuceBean.getMsg().equals("注册成功")) {
            try {
                Thread.sleep(2000);
                Toast.makeText(this, "注册成功,即将跳转到登录页面", Toast.LENGTH_SHORT).show();
                finish();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void failure(Exception e) {
        Toast.makeText(this, "注册失败！请检查网络" + e, Toast.LENGTH_SHORT).show();
    }
}
