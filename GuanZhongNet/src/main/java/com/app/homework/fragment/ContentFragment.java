package com.app.homework.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.app.homework.R;
import com.app.homework.adapter.BaoMingAdapter;
import com.app.homework.bean.BaoMingBean;
import com.app.homework.util.NetConnectionUtil;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 选择内容的fragment
 * 注释部分为固定数据
 */
public class ContentFragment extends Fragment {
    //    private static final String Key = "title";
//    @BindView(R.id.tv_content)
    TextView tvContent;
    Unbinder unbinder;
    @BindView(R.id.left_Xrecy)
    XRecyclerView leftXrecy;
    private List<BaoMingBean.ResultsBean> list = new ArrayList<>();
    private Handler handler = new Handler();
    private String encode;
    private BaoMingAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.content_fragment,container, false);
        unbinder = ButterKnife.bind(this, view);

        //传递内容，设置标题
//        String title = getArguments().getString(Key);
//        tvContent.setText(title);
//        tvContent.setTextColor(Color.RED);
//        tvContent.setTextSize(28);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //获取传递的标题数据，把获取的标题转码实现
        Bundle bundle = getArguments();
        String data = bundle.getString("name", "福利");
        encode = URLEncoder.encode(data);

        //判断网络状态，异步加载数据
        if (NetConnectionUtil.isNetConnectioned(getActivity())) {
            getData(data);
        } else {
            NetConnectionUtil.setNetConnectionWork(getActivity());
        }

        //XRecyclerview的上拉下拉方法
        leftXrecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //在子线程内完成下拉加载数据
                        getData(encode);
                        adapter.notifyDataSetChanged();
                        leftXrecy.refreshComplete();
                    }
                }, 888);
            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //在子线程内完成下拉加载数据
                        getData(encode);
                        adapter.notifyDataSetChanged();
                        leftXrecy.loadMoreComplete();
                    }
                }, 888);
            }
        });
    }

    //请求网络数据的方法
    public void getData(final String encode) {
        //使用OKhttp请求网络数据
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                // http://gank.io/api/data/福利/10/1
                .url("http://gank.io/api/data/" + encode + "/10/1")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("数据出错：" + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                System.out.println("返回：" + result);

                handler.post(new Runnable() {

                    @Override
                    public void run() {

                        //子线程内成功的回调
                        BaoMingBean bean = new Gson().fromJson(result, BaoMingBean.class);
                        list = bean.getResults();

                        //设置布局管理器以及布局适配器
                        if (adapter == null) {
                            LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                            leftXrecy.setLayoutManager(manager);
                            adapter = new BaoMingAdapter(getActivity(), list);
                            leftXrecy.setAdapter(adapter);
                        } else {
                            adapter.notifyDataSetChanged();
                        }
                    }
                });

            }
        });
    }

//    /**
//     * fragment之间静态传值
//     */
//    public static ContentFragment newInstance(String content){
//        ContentFragment fragment = new ContentFragment();
//
//        Bundle bundle = new Bundle();
//        bundle.putString(Key,content);
//        fragment.setArguments(bundle);
//
//        return fragment;
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
