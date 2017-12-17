package com.app.homework.presenter;

import com.app.homework.bean.DengluBean;
import com.app.homework.model.LoginModel;
import com.app.homework.model.LoginModelCallBack;
import com.app.homework.view.LoginViewListener;

/**
 * 登录Presenter 层
 */

public class LoginPresenter {
    LoginModel loginModel = new LoginModel();
    LoginViewListener loginViewListener;
    public LoginPresenter(LoginViewListener loginViewListener) {
        this.loginViewListener = loginViewListener;
    }

    public void getData(String denglu_zh, String denglu_pwd) {
        loginModel.getData(denglu_zh,denglu_pwd, new LoginModelCallBack() {
            @Override
            public void success(DengluBean dengluBean) {
                loginViewListener.success(dengluBean);
                System.out.println("登录p数据："+dengluBean.toString());
            }

            @Override
            public void failure(Exception e) {

            }
        });
    }
}
