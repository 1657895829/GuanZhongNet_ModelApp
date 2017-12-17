package com.app.homework.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.app.homework.R;
import com.app.homework.activity.WebUrlActivity;
import com.app.homework.bean.FaBuBean;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

//RecyclerView展示 我发布的 数据适配器
public class FaBuAdapter extends RecyclerView.Adapter<FaBuAdapter.MyViewHolder> {
    private Context context;
    private List<FaBuBean.ResultBean.DataBean> list;

    public FaBuAdapter(Context context) {
        this.context = context;
    }

    //添加数据
    public void addData(FaBuBean bean) {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.addAll(bean.getResult().getData());
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //添加布局视图
        View view = View.inflate(context, R.layout.fabu_adapter, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.draweeView.setImageURI(list.get(position).getThumbnail_pic_s());
        holder.title.setText(list.get(position).getTitle());

        //条目的点击事件
        holder.fabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebUrlActivity.class);
                intent.putExtra("url",list.get(position).getUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.draweeView)
        SimpleDraweeView draweeView;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.fabu)
        LinearLayout fabu;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
