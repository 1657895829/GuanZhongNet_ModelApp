package com.app.homework.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.app.homework.R;
import com.app.homework.activity.LoginActivity;
import com.app.homework.activity.PersonActivity;
import com.app.homework.activity.SettingActivity;
import com.app.homework.bean.DengluBean;
import com.facebook.drawee.view.SimpleDraweeView;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
/**
 * 我的fragment
 */
public class Mine04_Fragment extends Fragment {
    @BindView(R.id.settings)
    ImageView settings;
    @BindView(R.id.wode_touxiang)
    SimpleDraweeView wodeTouxiang;
    @BindView(R.id.denglu)
    TextView denglu;
    Unbinder unbinder;
    private SharedPreferences config;
    private SharedPreferences.Editor edit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //设置布局
        View view = inflater.inflate(R.layout.mine_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);

        //注册eventbus
        EventBus.getDefault().register(this);

        //获取保存的用户id
        config = getActivity().getSharedPreferences("config", 0);
        edit = config.edit();

        //进入页面判断是否上次是否登录
        String uid = config.getString("uid", null);
        if(uid == null){
            wodeTouxiang.setImageResource(R.drawable.default_head);
            denglu.setText("未登录");
        }else{
            wodeTouxiang.setImageResource(R.drawable.ath);//头像改变
            denglu.setText("18631090582");               //头像下面的文字改变
        }

        return view;
    }

    //接收eventbus传递的数据的事件
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onM(DengluBean dengluBean) {
        //走到这里 说明成功登录了,改变头像的设置,改变点击头像和点击设置跳转的页面
        if (dengluBean != null) {
            //设置个人信息
            wodeTouxiang.setImageResource(R.drawable.ath);
            denglu.setText(dengluBean.getData().getUsername());      //登录文字被用户名替换

        }
    }

    //每次进入页面都判断是否登录
    @Override
    public void onResume() {
        super.onResume();
        String uid = config.getString("uid", null);
        if(uid == null){
            //没登录
            wodeTouxiang.setImageResource(R.drawable.default_head);
            denglu.setText("未登录");
        }else{
            //已登录
            wodeTouxiang.setImageResource(R.drawable.ath);//头像改变
            denglu.setText("18631090582");               //头像下面的文字改变
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //销毁EventBus，取消注册
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.settings, R.id.wode_touxiang})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.settings:      //点击设置按钮
                //1.如果已经登录,就跳到设置页面,2.如果没登录,就跳到登录页面
                String uid = config.getString("uid", null);

                if (uid == null) {   //没登录

                    //跳到登录页面
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                } else {             //已经登录

                    wodeTouxiang.setImageResource(R.drawable.ath);//头像改变
                    denglu.setText("18631090582");                         //头像下面的文字改变

                    //跳到设置页面
                    Intent intent = new Intent(getActivity(), SettingActivity.class);
                    intent.putExtra("mobile", denglu.getText().toString());
                    startActivity(intent);
                }

                break;

            case R.id.wode_touxiang://点击 我的头像
                //1.如果已经登录,就跳到 个人资料页面.2.如果没登录,就跳到登录页面
                String uid1 = config.getString("uid", null);

                if (uid1 == null) {  //没登录

                    //跳到登录页面
                    Intent intent1 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent1);
                } else {
                    wodeTouxiang.setImageResource(R.drawable.ath);  //头像改变
                    denglu.setText("18631090582");                  //头像下面的文字改变

                    //跳转到个人资料页面
                    Intent intent = new Intent(getActivity(), PersonActivity.class);
                    intent.putExtra("username", denglu.getText().toString());
                    startActivity(intent);
                    break;
                }
        }
    }
}
