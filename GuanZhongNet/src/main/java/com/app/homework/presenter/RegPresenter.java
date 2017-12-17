package com.app.homework.presenter;


import com.app.homework.bean.ZhuceBean;
import com.app.homework.model.RegModel;
import com.app.homework.model.RegModelCallBack;
import com.app.homework.view.RegViewListener;

/**
 * 注册Presenter 层
 */

public class RegPresenter {

    RegModel regModel = new RegModel();
    RegViewListener regViewListener;
    public RegPresenter(RegViewListener regViewListener) {
        this.regViewListener = regViewListener;
    }

    public void getData(String zhuce_zh, String zhuce_pwd) {
        regModel.getData(zhuce_zh,zhuce_pwd, new RegModelCallBack() {

            @Override
            public void success(ZhuceBean zhuceBean) {
                regViewListener.success(zhuceBean);
            }

            @Override
            public void failure(Exception e) {

            }
        });
    }
}
