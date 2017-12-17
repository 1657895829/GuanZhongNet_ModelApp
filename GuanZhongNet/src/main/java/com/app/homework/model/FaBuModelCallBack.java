package com.app.homework.model;

import com.app.homework.bean.FaBuBean;

/**
 * model类接口，成功和失败的方法
 */
public interface FaBuModelCallBack {
    public void onSuccess(FaBuBean faBuBean);
    public void onFailure(Exception e);
}
