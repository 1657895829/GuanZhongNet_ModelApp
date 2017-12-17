package com.app.homework.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.app.homework.R;
import com.app.homework.util.NetConnectionUtil;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 我报名的fragment
 * 注释部分为固定数据
 */

public class FragmentLeft extends Fragment {
    @BindView(R.id.left_tabLayout)
    TabLayout leftTabLayout;
    @BindView(R.id.viewPager01)
    ViewPager viewPager01;
    @BindView(R.id.xiaoren_weidenglu)
    ViewPager xiaorenWeidenglu;
    Unbinder unbinder;
    private List<String> listTitles;
    private List<Fragment> fragments;
    private List<TextView> listTextViews;
    private SharedPreferences config;
    private SharedPreferences.Editor edit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_left, container, false);
        unbinder = ButterKnife.bind(this, view);

        //tabLayout标题
        final List<String> listTitles = new ArrayList<>();

        //判断网络状态，加载数据
        if (NetConnectionUtil.isNetConnectioned(getActivity())) {

            //设置标题
            listTitles.add("福利");
            listTitles.add("前端");
            listTitles.add("all");
            listTitles.add("iOS");
            viewPager01.setOffscreenPageLimit(listTitles.size());
        } else {
            NetConnectionUtil.setNetConnectionWork(getActivity());
        }

        //设置使用ViewPager+Fragment显示新闻列表数据布局的适配器
        viewPager01.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            //得到当前页的标题,,,也就是设置当前页面显示的标题是tabLayout对应的标题
            @Override
            public CharSequence getPageTitle(int position) {
                return listTitles.get(position);
            }

            @Override
            public Fragment getItem(int position) {

                //判断所选的标题，进行传值显示,然后返回对应的fragment
                ContentFragment contentFragment = new ContentFragment();

                Bundle bundle = new Bundle();
                bundle.putString("name", listTitles.get(position));
                contentFragment.setArguments(bundle);
                return contentFragment;
            }

            @Override
            public int getCount() {
                return listTitles.size();
            }
        });

        //TabLyout要与ViewPager关联显示
        leftTabLayout.setupWithViewPager(viewPager01);


        //      ---------------------  哭的小人viewpager
        //设置viewpager可以预加载全部的页数,,不会销毁其他的页面
        xiaorenWeidenglu.setOffscreenPageLimit(listTitles.size());
        xiaorenWeidenglu.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {

            //去登陆fragment
            private GoToLogin_Fragment loginFragment;

            @Override
            public CharSequence getPageTitle(int position) {
                return listTitles.get(position);
            }

            @Override
            public Fragment getItem(int position) {

                loginFragment = new GoToLogin_Fragment();
                return loginFragment;
            }

            @Override
            public int getCount() {
                return listTitles.size();
            }
        });

        //设置tablayout和viewpager联动
        leftTabLayout.setupWithViewPager(xiaorenWeidenglu);





        //sharedpreferences存取数据
        config = getActivity().getSharedPreferences("config", 0);
        edit = config.edit();
        String uid = config.getString("uid", null);
        if (uid == null) {
            //如果没登录,就显示 哭的小人 ViewPager
            xiaorenWeidenglu.setVisibility(View.VISIBLE);//设置显示哭的小人布局
            viewPager01.setVisibility(View.GONE);
            //设置tablayout和viewpager联动
            leftTabLayout.setupWithViewPager(xiaorenWeidenglu);
        } else {
            //如果已经登录,就显示数据
            viewPager01.setVisibility(View.VISIBLE);
            xiaorenWeidenglu.setVisibility(View.GONE);
            //设置tablayout和viewpager联动
            leftTabLayout.setupWithViewPager(viewPager01);
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //每次进入页面,都判断是否登录
        String uid = config.getString("uid", null);

        if (uid == null) {
            //如果没登录,就显示 哭的小人
            xiaorenWeidenglu.setVisibility(View.VISIBLE);//设置显示哭的小人布局
            viewPager01.setVisibility(View.GONE);
            //设置tablayout和viewpager联动
            leftTabLayout.setupWithViewPager(xiaorenWeidenglu);
        } else {
            //如果已经登录,就显示数据
            viewPager01.setVisibility(View.VISIBLE);
            xiaorenWeidenglu.setVisibility(View.GONE);
            //设置tablayout和viewpager联动
            leftTabLayout.setupWithViewPager(viewPager01);
        }

    }

    //初始化数据
    private void initData() {
//       listTitles = new ArrayList<>();         //tabLayout标题
//        fragments = new ArrayList<>();          //对应的fragment
//        listTextViews = new ArrayList<>();     //数据集合

//        listTitles.add("待审核");
//        listTitles.add("待支付");
//        listTitles.add("待参加");
//        listTitles.add("已完成");

//        //遍历标题集合，选择对应的fragment内容
//        for (int i = 0;i<listTitles.size();i++){
//            ContentFragment fragment = ContentFragment.newInstance(listTitles.get(i));
//            fragments.add(fragment);
//        }
//
//        /**
//         * 设置tab模式，当前为系统默认模式
//         * tabLayout.setTabMode(TabLayout.SCROLL_AXIS_HORIZONTAL);
//         */
//        //添加tab选项,设置tab选项卡内容
//        for (int i = 0;i<listTitles.size();i++){
//            tabLayout.addTab(tabLayout.newTab().setTag(listTitles.get(i)));
//        }
//
//
//        //设置fragment页面切换事件
//        FragmentPagerAdapter fragAdapter = new FragmentPagerAdapter(getFragmentManager()) {
//            @Override
//            public int getCount() {
//                return fragments.size();
//            }
//
//            @Override
//            public Fragment getItem(int position) {
//                return fragments.get(position);
//            }
//
//            //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
//            @Override
//            public CharSequence getPageTitle(int position) {
//                return listTitles.get(position);
//            }
//        };
//
//        //设置ViewPager页面fragment的适配器
//        viewPager01.setAdapter(fragAdapter);

//        //将TabLayout和ViewPager关联
//        tabLayout.setupWithViewPager(viewPager01);

//        //给TabLayout设置适配器
//        tabLayout.setTabsFromPagerAdapter(fragAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
