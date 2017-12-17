package com.app.homework.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.app.homework.R;
import com.app.homework.activity.WebUrlActivity;
import com.app.homework.bean.NewsBean;
import com.app.homework.util.ImageLoaderUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

//XRecyclerview多条目展示数据适配器
public class ShouYeChildAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int ONLY_TITLE = 0;
    private int IMAGE_ONE = 1;
    private int IMAGE_TWO = 2;
    private int IMAGE_THREE = 3;
    private Context context;
    private List<NewsBean.ResultBean.DataBean> list;

    public ShouYeChildAdapter(Context context, List<NewsBean.ResultBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //添加布局视图
        if (viewType == IMAGE_ONE) {
            View view01 = LayoutInflater.from(context).inflate(R.layout.item_layout01, parent, false);
            return new MyViewHolder01(view01);
        } else if (viewType == IMAGE_TWO) {
            View view02 = LayoutInflater.from(context).inflate(R.layout.item_layout02, parent, false);
            return new MyViewHolder02(view02);
        } else if (viewType == IMAGE_THREE) {
            View view03 = LayoutInflater.from(context).inflate(R.layout.item_layout03, parent, false);
            return new MyViewHolder03(view03);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_layout00, parent, false);
            return new MyViewHolder04(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        /**每个fragment中展示新闻内容,要求:多条目加载和上拉加载,下拉刷新
         多条目加载:奇数条目(第1357....)展示只有标题的布局
         偶数条目(第2468.....)展示标题+图片样式的条目布局
         */
        if (position % 2 == 0) {
            if (list.get(position).getThumbnail_pic_s() != null && list.get(position).getThumbnail_pic_s02() != null && list.get(position).getThumbnail_pic_s03() != null) {
                return IMAGE_THREE;
            } else if (list.get(position).getThumbnail_pic_s() != null && list.get(position).getThumbnail_pic_s02() != null) {
                return IMAGE_TWO;
            }
            return IMAGE_ONE;
        }
        return ONLY_TITLE;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //判断类型，设置参数数据
        if (holder instanceof MyViewHolder01) {
            MyViewHolder01 holder01 = (MyViewHolder01) holder;
            holder01.title.setText(list.get(position).getTitle());
            holder01.authorName.setText(list.get(position).getAuthor_name());
            ImageLoader.getInstance().displayImage(list.get(position).getThumbnail_pic_s(), holder01.image, ImageLoaderUtil.getDefaultOption());

            //条目点击事件
            holder01.line1.setOnClickListener(new View.OnClickListener() {
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
            holder02.title.setText(list.get(position).getTitle());
            ImageLoader.getInstance().displayImage(list.get(position).getThumbnail_pic_s(), holder02.image001, ImageLoaderUtil.getDefaultOption());
            ImageLoader.getInstance().displayImage(list.get(position).getThumbnail_pic_s02(), holder02.image002, ImageLoaderUtil.getDefaultOption());

            //条目点击事件
            holder02.line2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳转至webview页面显示详情
                    Intent intent = new Intent(context, WebUrlActivity.class);
                    intent.putExtra("url", list.get(position).getUrl());
                    context.startActivity(intent);
                }
            });

        } else if (holder instanceof MyViewHolder03) {
            MyViewHolder03 holder03 = (MyViewHolder03) holder;
            holder03.title.setText(list.get(position).getTitle());
            ImageLoader.getInstance().displayImage(list.get(position).getThumbnail_pic_s(), holder03.image01, ImageLoaderUtil.getDefaultOption());
            ImageLoader.getInstance().displayImage(list.get(position).getThumbnail_pic_s02(), holder03.image02, ImageLoaderUtil.getDefaultOption());

            //条目点击事件
            holder03.line3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳转至webview页面显示详情
                    Intent intent = new Intent(context, WebUrlActivity.class);
                    intent.putExtra("url", list.get(position).getUrl());
                    context.startActivity(intent);
                }
            });

        } else if (holder instanceof MyViewHolder04) {
            MyViewHolder04 holder04 = (MyViewHolder04) holder;
            holder04.title.setText(list.get(position).getTitle());

            //条目点击事件
            holder04.line4.setOnClickListener(new View.OnClickListener() {
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
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class MyViewHolder01 extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.author_name)
        TextView authorName;
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.line1)
        LinearLayout line1;

        public MyViewHolder01(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class MyViewHolder02 extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.image001)
        ImageView image001;
        @BindView(R.id.image002)
        ImageView image002;
        @BindView(R.id.line2)
        LinearLayout line2;
        public MyViewHolder02(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class MyViewHolder03 extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.image01)
        ImageView image01;
        @BindView(R.id.image02)
        ImageView image02;
        @BindView(R.id.image03)
        ImageView image03;
        @BindView(R.id.line3)
        LinearLayout line3;
        public MyViewHolder03(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class MyViewHolder04 extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.line4)
        LinearLayout line4;
        public MyViewHolder04(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
