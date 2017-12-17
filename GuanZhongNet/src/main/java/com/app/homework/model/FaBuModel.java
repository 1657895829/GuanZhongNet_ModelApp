package com.app.homework.model;

import com.app.homework.bean.FaBuBean;
import com.app.homework.retrofit.GetDataInterface;
import com.app.homework.retrofit_rxjava_okhttp.LoggingInterceptor;
import com.app.homework.retrofit_rxjava_okhttp.RetrofitUnitl;
import java.util.concurrent.TimeUnit;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;

/**
 * model接口实现类
 */
public class FaBuModel {
    /**
     * get请求
     * @param callBack
     */
    public void getData(final FaBuModelCallBack callBack){
        //使用okhttp请求,添加拦截器时把下面代码解释
        OkHttpClient ok = new OkHttpClient.Builder()
                .connectTimeout(20000, TimeUnit.SECONDS)
                .writeTimeout(20000,TimeUnit.SECONDS)
                .readTimeout(20000,TimeUnit.SECONDS)
                .addInterceptor(new LoggingInterceptor())
                .build();

        //使用Retrofit结合RxJava，okhttp封装类的单例模式
        RetrofitUnitl.getInstance("http://v.juhe.cn",ok)
                .setCreate(GetDataInterface.class)
                .get("2df01835231e41bfe1d652cf2d2c7f07")
                .subscribeOn(Schedulers.io())               //请求完成后在io线程中执行
                .observeOn(AndroidSchedulers.mainThread())  //最后在主线程中执行

                //进行事件的订阅，使用Consumer实现
                .subscribe(new Consumer<FaBuBean>() {
                    @Override
                    public void accept(FaBuBean faBuBean) throws Exception {
                        //请求成功时返回数据
                        callBack.onSuccess(faBuBean);
                        System.out.println("m个人信息：" + faBuBean.toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callBack.onFailure((Exception) throwable);
                    }
                });

    }

}
