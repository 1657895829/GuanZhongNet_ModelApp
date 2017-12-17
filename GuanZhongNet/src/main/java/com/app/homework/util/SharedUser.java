package com.app.homework.util;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.app.homework.activity.LoginActivity;
import com.app.homework.bean.DengluBean;

/**
 * 登录功能的操作逻辑步骤如下：可以分为五个步骤来实现。
 */

public class SharedUser {
    private Context context;

    //1、进入页面先判断用户是否已经登录
    private void isLogin() {
        //查看本地是否有用户的登录信息
        SharedPreferences sp = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        String name = sp.getString("name", "");
        if (TextUtils.isEmpty(name)) {
            //本地没有保存过用户信息，给出提示：登录
            doLogin();
        } else {
            //已经登录过，直接加载用户的信息并显示
            //doUser();
        }
    }

    //   2、 没有登录过，则提示用户登录，点击后跳转到登录页面
    private void doLogin() {
        new AlertDialog.Builder(context)
                .setTitle("提示")
                .setMessage("您还没有登录哦！")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @SuppressLint("NewApi")
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                UIUtils.toast("进入登录界面",false);
                        Intent intent = new Intent(context, LoginActivity.class);
                        context.startActivity(intent);

                    }
                })
                .setCancelable(false)
                .show();
    }

    //3. 保存用户信息：在登录页面中做登录操作，登录成功后保存相应的用户信息到本地
    //保存用户信息
    public void saveUser(DengluBean dengluBean){
        SharedPreferences sp = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name",dengluBean.getData().getUsername());    //用户名
        editor.putInt("id",dengluBean.getData().getUid());                 //用户id
        editor.commit();//必须提交，否则保存不成功
    }

}
