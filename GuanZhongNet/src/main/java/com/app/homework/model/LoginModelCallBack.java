package com.app.homework.model;

import com.app.homework.bean.DengluBean;

/**
 * 登录model层接口
 */

public interface LoginModelCallBack {
    public void success(DengluBean dengluBean);
    public void failure(Exception e);
}
