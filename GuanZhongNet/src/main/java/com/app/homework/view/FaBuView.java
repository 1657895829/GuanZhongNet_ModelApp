package com.app.homework.view;


import com.app.homework.bean.FaBuBean;

/**
 * view层接口类，请求成功与失败的方法
 */
public interface FaBuView {
    public void onSuccess(FaBuBean bean);
    public void onFailure(Exception e);
}
