package com.app.homework.view;


import com.app.homework.bean.DengluBean;

/**
 * 登录view层
 */

public interface LoginViewListener {
    public void success(DengluBean dengluBean);
    public void failure(Exception e);
}
