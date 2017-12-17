package com.app.homework.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.app.homework.R;
import com.app.homework.activity.WebUrlActivity;
import com.app.homework.bean.BaoMingBean;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

//RecyclerView展示 我报名的 数据适配器
public class BaoMingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<BaoMingBean.ResultsBean> list;

    public BaoMingAdapter(Context context, List<BaoMingBean.ResultsBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //添加布局视图
        if (viewType == 0) {
            View view01 = LayoutInflater.from(context).inflate(R.layout.baoming01_adapter, parent, false);
            return new MyViewHolder01(view01);
        } else {
            View view02 = LayoutInflater.from(context).inflate(R.layout.baoming02_adapter, parent, false);
            return new MyViewHolder02(view02);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolder01) {
            MyViewHolder01 holder01 = (MyViewHolder01) holder;
            holder01.title.setText(list.get(position).getDesc());
            if (list.size() > 0){
                if (list.get(position).getUrl() != null){
                    holder01.draweeView.setImageURI(list.get(position).getUrl());
                }
            }

            //条目点击事件
            holder01.fabu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳转至webview页面显示详情
                    Intent intent = new Intent(context, WebUrlActivity.class);
                    intent.putExtra("url", list.get(position).getUrl());
                    context.startActivity(intent);
                }
            });

        } else if (holder instanceof MyViewHolder02) {
            MyViewHolder02 holder02 = (MyViewHolder02) holder;
            holder02.title.setText(list.get(position).getDesc());

            //条目点击事件
            holder02.fabu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳转至webview页面显示详情
                    Intent intent = new Intent(context, WebUrlActivity.class);
                    intent.putExtra("url", list.get(position).getUrl());
                    context.startActivity(intent);
                }
            });
        }

    }

    @Override
    public int getItemViewType(int position) {
        /**每个fragment中展示新闻内容,要求:多条目加载和上拉加载,下拉刷新
         多条目加载:奇数条目(第1357....)展示只有标题的布局
         偶数条目(第2468.....)展示标题+图片样式的条目布局
         */
        if (position % 2 == 0) {
            return 0;
        }
        return 1;
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class MyViewHolder01 extends RecyclerView.ViewHolder {
        @BindView(R.id.draweeView)
        SimpleDraweeView draweeView;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.fabu)
        LinearLayout fabu;
        public MyViewHolder01(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class MyViewHolder02 extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.fabu)
        LinearLayout fabu;
        public MyViewHolder02(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
