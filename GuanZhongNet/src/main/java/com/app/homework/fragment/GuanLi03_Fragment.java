package com.app.homework.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.app.homework.R;
import com.xys.libzxing.zxing.activity.CaptureActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 管理fragment
 */
public class GuanLi03_Fragment extends Fragment {
    @BindView(R.id.fb_bm)
    RadioButton fbBm;
    @BindView(R.id.fb_fb)
    RadioButton fbFb;
    @BindView(R.id.rg_top)
    RadioGroup rgTop;
    @BindView(R.id.gl_topvp)
    ViewPager viewPager;
    Unbinder unbinder;
    @BindView(R.id.xiaoren_viewpager_weidenglu)
    ViewPager xiaorenViewpagerWeidenglu;
    private SharedPreferences config;
    private SharedPreferences.Editor edit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //设置布局
        View view = inflater.inflate(R.layout.guanli_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);

        //sharedpreferences
        config = getActivity().getSharedPreferences("config", 0);
        edit = config.edit();
        String uid = config.getString("uid", null);
        if (uid == null) {
            //如果没登录,就显示 哭的小人
            xiaorenViewpagerWeidenglu.setVisibility(View.VISIBLE);//设置显示哭的小人布局
            viewPager.setVisibility(View.GONE);
        } else {
            //如果已经登录,就显示数据
            viewPager.setVisibility(View.VISIBLE);
            xiaorenViewpagerWeidenglu.setVisibility(View.GONE);
        }

        //RadioGroup 组合按钮的点击事件
        rgTop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {

                    //点击第一个radiobutton,显示viewpager的第一页:报名
                    case R.id.fb_bm:
                        viewPager.setCurrentItem(0, false);
                        xiaorenViewpagerWeidenglu.setCurrentItem(0, false);
                        break;

                    //点击第二个radiobutton,显示viewpager的第二页：发布
                    case R.id.fb_fb:
                        viewPager.setCurrentItem(1, false);
                        xiaorenViewpagerWeidenglu.setCurrentItem(1, false);
                        break;
                }
            }
        });

        //设置不显示小人的viewPager页面适配器
        viewPager.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                switch (position) {
                    case 0:
                        //当滑动到第一页时候,展示这个fragment
                        fragment = new FragmentLeft();
                        break;
                    case 1:
                        //当滑动到第二页时候,展示这个fragment
                        fragment = new FragmentRight();
                        break;
                }
                return fragment;
            }

            @Override
            public int getCount() {
                //返回viewpager的数量
                return 2;
            }
        });

        //不显示小人的viewpager的页面切换事件,切换为对应的RadioGroup 按钮
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                //radiogroup选中对应的radiobutton
                rgTop.check(rgTop.getChildAt(position).getId());

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


        //----------------------未登录页面 显示小人的设置------------------------
        //viewpager设置适配器
        xiaorenViewpagerWeidenglu.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                switch (position) {
                    case 0:
                        //当滑动到第一页时候,展示这个fragment
                        fragment = new FragmentLeft();
                        break;
                    case 1:
                        //当滑动到第二页时候,展示这个fragment
                        fragment = new GoToLogin_Fragment();
                        break;
                }
                return fragment;
            }

            @Override
            public int getCount() {
                //返回viewpager的数量
                return 2;
            }
        });

        //viewpager滑动监听
        xiaorenViewpagerWeidenglu.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //radiogroup选中对应的radiobutton
                rgTop.check(rgTop.getChildAt(position).getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //每次进入页面,都判断是否登录
        String uid = config.getString("uid", null);

        if (uid == null) {
            //如果没登录,就显示 哭的小人
            xiaorenViewpagerWeidenglu.setVisibility(View.VISIBLE);//设置显示哭的小人布局
            viewPager.setVisibility(View.GONE);
        } else {
            //如果已经登录,就显示数据
            viewPager.setVisibility(View.VISIBLE);
            xiaorenViewpagerWeidenglu.setVisibility(View.GONE);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //二维码扫描
    @OnClick(R.id.sao)
    public void onViewClicked() {
        Toast.makeText(getActivity(),"开始扫描",Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getActivity(), CaptureActivity.class);
        startActivity(intent);
    }
}
