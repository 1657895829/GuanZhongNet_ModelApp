package com.app.homework.retrofit;

import com.app.homework.bean.DengluBean;
import com.app.homework.bean.FaBuBean;
import com.app.homework.bean.ZhuceBean;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
/**
 * 网络接口数据的请求类
 */
public interface GetDataInterface {

    //注册的接口
    //https://www.zhaoapi.cn/user/reg?mobile=15810680959&password=123456
    @FormUrlEncoded
    @POST("/user/reg")
    Call<ZhuceBean> zhuce(@FieldMap Map<String, String> map);

    //登录的接口
    //https://www.zhaoapi.cn/user/login?mobile=15810680959&password=123456
    @FormUrlEncoded
    @POST("/user/login")
    Call<DengluBean> denglu(@FieldMap Map<String, String> map);

    //我发布的fragment http://v.juhe.cn/toutiao/index?type=toutiao&key=2df01835231e41bfe1d652cf2d2c7f07
    @GET("/toutiao/index")
    Observable<FaBuBean> get(@Query("key") String key);

}
