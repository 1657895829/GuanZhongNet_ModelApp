package com.app.homework.model;

import com.app.homework.bean.ZhuceBean;

/**
 * 注册model层接口
 */

public interface RegModelCallBack {
    public void success(ZhuceBean dengluBean);
    public void failure(Exception e);
}
