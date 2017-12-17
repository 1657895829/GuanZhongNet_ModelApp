package com.app.homework.view;


import com.app.homework.bean.ZhuceBean;

/**
 * 注册view层
 */

public interface RegViewListener {
    public void success(ZhuceBean zhuceBean);
    public void failure(Exception e);
}
