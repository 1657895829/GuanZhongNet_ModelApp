package com.app.homework.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.app.homework.R;
import com.app.homework.adapter.FaBuAdapter;
import com.app.homework.bean.FaBuBean;
import com.app.homework.presenter.FabuPresenter;
import com.app.homework.view.FaBuView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 我发布的fragment
 */

public class FragmentRight extends Fragment implements FaBuView {
    @BindView(R.id.right_Xrecy)
    XRecyclerView rightXrecy;
    Unbinder unbinder;
    private FabuPresenter presenter;
    private FaBuAdapter adapter;
    private Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_right, null);
        unbinder = ButterKnife.bind(this, view);

        //创建Presenter层实例,与view层交互
        presenter = new FabuPresenter(this);

        //get请求方式
        presenter.get();

        //设置布局管理器以及布局适配器
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        adapter = new FaBuAdapter(getActivity());
        rightXrecy.setLayoutManager(manager);
        rightXrecy.setAdapter(adapter);

        //XRecyclerview的上拉下拉方法
        rightXrecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //在子线程内完成下拉加载数据
                        presenter.get();
                        adapter.notifyDataSetChanged();
                        rightXrecy.refreshComplete();
                    }
                }, 888);
            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //在子线程内完成下拉加载数据
                        presenter.get();
                        adapter.notifyDataSetChanged();
                        rightXrecy.loadMoreComplete();
                    }
                }, 888);
            }
        });

        return view;
    }

    @Override
    public void onSuccess(FaBuBean bean) {
        //请求成功时添加数据
        adapter.addData(bean);
    }

    @Override
    public void onFailure(Exception e) {
        System.out.println("数据出错："+e);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
