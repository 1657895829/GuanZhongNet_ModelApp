package com.app.homework.presenter;

import com.app.homework.bean.FaBuBean;
import com.app.homework.model.FaBuModel;
import com.app.homework.model.FaBuModelCallBack;
import com.app.homework.view.FaBuView;

/**
 * Presenter层，进行view层与model数据的交互
 */
public class FabuPresenter {

    private FaBuView myView;
    private FaBuModel myModel;

    public FabuPresenter(FaBuView myView) {
        this.myView = myView;
        this.myModel = new FaBuModel();
    }

    /**
     * get请求数据交互
     */
    public void get(){
        myModel.getData(new FaBuModelCallBack() {
            @Override
            public void onSuccess(FaBuBean bean) {
                //数据交互时，为防止内存泄露，设置view层数据为空
                if (myView != null){
                    myView.onSuccess(bean);
                }
            }

            @Override
            public void onFailure(Exception e) {
                //数据交互时，为防止内存泄露，设置view层数据为空
                if (myView != null){
                    myView.onFailure(e);
                }
            }
        });
    }

}
