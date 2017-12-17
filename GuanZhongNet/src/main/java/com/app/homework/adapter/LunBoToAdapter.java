package com.app.homework.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.app.homework.R;
import com.app.homework.bean.JunShiBean;
import com.app.homework.bean.NewsBean;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//XRecyclerview多条目展示数据适配器
public class LunBoToAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<JunShiBean.NewslistBean> Newslist = new ArrayList<>();
    private List<NewsBean.ResultBean.DataBean> list;
    private MyPagerAdapter pagerAdapter;
    private List<ImageView> images;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                int currentItem = viewHolder1.viewPager.getCurrentItem();
                viewHolder1.viewPager.setCurrentItem(currentItem + 1);
                //持续延时发送消息
                handler.sendEmptyMessageDelayed(0, 2888);
            }
        }
    };
    private MyViewHolder01 viewHolder1;
    private MyViewHolder02 viewHolder2;


    public LunBoToAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //添加布局视图
        if (viewType == 0) {
            View view = View.inflate(context, R.layout.lunboto_layout, null);
            viewHolder1 = new MyViewHolder01(view);
            return viewHolder1;
        } else if (viewType == 1) {
            View view = View.inflate(context, R.layout.shouye_child_fragment, null);
            viewHolder2 = new MyViewHolder02(view);
            return viewHolder2;
        }
        return viewHolder1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else if (position == 1) {
            return 1;
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //判断类型，设置参数数据
        if (holder instanceof MyViewHolder01) {
            MyViewHolder01 holder01 = (MyViewHolder01) holder;

            //解析数据
            getData();

            //轮播图延时发送空消息，展示在某一页面
            holder01.viewPager.setCurrentItem(Newslist.size() % 100000);
            handler.sendEmptyMessageDelayed(0, 2888);
        } else if (holder instanceof MyViewHolder02) {
            MyViewHolder02 holder02 = (MyViewHolder02) holder;

            //设置布局管理器以及布局适配器
            LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            holder02.hotChildXRecyclerView.setLayoutManager(manager);
            ShouYeChildAdapter adapter = new ShouYeChildAdapter(context, list);
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    static class MyViewHolder01 extends RecyclerView.ViewHolder {
        @BindView(R.id.viewPager)
        ViewPager viewPager;
        @BindView(R.id.lineLayout)
        LinearLayout lineLayout;

        public MyViewHolder01(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class MyViewHolder02 extends RecyclerView.ViewHolder {
        @BindView(R.id.hot_child_XRecyclerView)
        XRecyclerView hotChildXRecyclerView;
        public MyViewHolder02(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    //异步任务加载数据
    public void getData() {
        //使用OKhttp请求网络数据
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                // http://api.tianapi.com/military/?key=18e883dd6b316eb1d97fd86338abbf06&num=10
                .url("http://api.tianapi.com/military/?key=18e883dd6b316eb1d97fd86338abbf06&num=10")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println( "轮播图数据出错：" + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                System.out.println("返回：" + result);

                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        //子线程内成功的回调
                        JunShiBean bean = new Gson().fromJson(result, JunShiBean.class);
                        Newslist = bean.getNewslist();
                        //System.out.println("标题：" + list.get(0).getTitle());

                        //调用设置适配器的方法，设置展示数据
                        if (bean != null) {
                            Newslist.addAll(bean.getNewslist());

                            // 初始化小圆点的方法
                            initCircle();

                            //设置适配器的方法
                            setAdapter();
                        }
                    }
                });

            }
        });
    }

    private void initCircle() {
        //首先需要一个集合记录这些小圆点的图片,,,,当页面切换的时候,可以从集合中取出imageView进行显示图片的设置
        images = new ArrayList<>();
        //再清除线性布局中的view视图
        viewHolder1.lineLayout.removeAllViews();

        //遍历集合数据对应的圆点
        for (int i = 0; i < Newslist.size(); i++) {
            //先初始化一个ImageView视图
            ImageView imageview = new ImageView(context);

            //设置小圆点刚开始的颜色
            if (i == 0) {
                imageview.setImageResource(R.drawable.shape_selected);
            } else {
                imageview.setImageResource(R.drawable.shape_select_no);
            }

            //把设置好的视图添加到集合中
            images.add(imageview);

            //再把视图添加到线性布局中显示
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(5, 0, 5, 0);
            viewHolder1.lineLayout.addView(imageview, params);
        }
    }

    //设置适配器
    public void setAdapter() {

        //设置无限轮播图的适配器
        if (pagerAdapter == null) {
            pagerAdapter = new MyPagerAdapter(context, Newslist, handler);
            viewHolder1.viewPager.setAdapter(pagerAdapter);

            //viewPager页面改变的监听事件
            viewHolder1.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    //设置小圆点的选中颜色
                    for (int i = 0; i < images.size(); i++) {
                        if (i == position % images.size()) {
                            images.get(i).setImageResource(R.drawable.shape_selected);
                        } else {
                            images.get(i).setImageResource(R.drawable.shape_select_no);
                        }
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
        } else {
            pagerAdapter.notifyDataSetChanged();
        }
    }

}
