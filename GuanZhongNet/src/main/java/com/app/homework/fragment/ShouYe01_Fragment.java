package com.app.homework.fragment;

import android.content.Intent;
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
import com.zaaach.citypicker.CityPickerActivity;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import static android.app.Activity.RESULT_OK;

/**
 * 首页fragment
 */
public class ShouYe01_Fragment extends Fragment {
    private static final int REQUEST_CODE_PICK_CITY = 0;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    Unbinder unbinder;
    @BindView(R.id.country)
    TextView country;
    private List<String> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //设置布局
        View view = inflater.inflate(R.layout.shouye_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //设置标题
        list = new ArrayList<>();

        //判断网络状态，加载数据
        if (NetConnectionUtil.isNetConnectioned(getActivity())) {
            list.add("头条");
            list.add("社会");
            list.add("国内");
            list.add("国际");
            list.add("娱乐");
            list.add("军事");
            list.add("财经");
            list.add("时尚");
            list.add("科技");
            list.add("体育");
            viewPager.setOffscreenPageLimit(list.size());
        } else {
            NetConnectionUtil.setNetConnectionWork(getActivity());
        }

        //设置使用ViewPager+Fragment显示新闻列表数据布局的适配器
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            //得到当前页的标题,,,也就是设置当前页面显示的标题是tabLayout对应的标题
            @Override
            public CharSequence getPageTitle(int position) {
                return list.get(position);
            }

            @Override
            public Fragment getItem(int position) {

                //判断所选的标题，进行传值显示,然后返回对应的fragment
                if (position == 0) {

                    LunBoTu_Fragment lunBoTuFragment = new LunBoTu_Fragment();

                    Bundle bundle = new Bundle();
                    bundle.putString("name", list.get(position));

                    if (list.get(position).equals("头条")) {
                        bundle.putString("name", "top");
                    } else if (list.get(position).equals("社会")) {
                        bundle.putString("name", "shehui");
                    } else if (list.get(position).equals("国内")) {
                        bundle.putString("name", "guonei");
                    } else if (list.get(position).equals("国际")) {
                        bundle.putString("name", "guoji");
                    } else if (list.get(position).equals("娱乐")) {
                        bundle.putString("name", "yule");
                    } else if (list.get(position).equals("军事")) {
                        bundle.putString("name", "junshi");
                    } else if (list.get(position).equals("科技")) {
                        bundle.putString("name", "keji");
                    } else if (list.get(position).equals("财经")) {
                        bundle.putString("name", "caijing");
                    } else if (list.get(position).equals("时尚")) {
                        bundle.putString("name", "shishang");
                    } else if (list.get(position).equals("体育")) {
                        bundle.putString("name", "tiyu");
                    }
                    lunBoTuFragment.setArguments(bundle);
                    return lunBoTuFragment;
                } else {
                    //在这个位置对比一下标题是什么,,,然后返回对应的fragment
                    Shou_Child_Fragment child_fragment = new Shou_Child_Fragment();

                    //判断所选的标题，进行传值显示
                    Bundle bundle = new Bundle();
                    bundle.putString("name", list.get(position));

                    if (list.get(position).equals("头条")) {
                        bundle.putString("name", "top");
                    } else if (list.get(position).equals("社会")) {
                        bundle.putString("name", "shehui");
                    } else if (list.get(position).equals("国内")) {
                        bundle.putString("name", "guonei");
                    } else if (list.get(position).equals("国际")) {
                        bundle.putString("name", "guoji");
                    } else if (list.get(position).equals("娱乐")) {
                        bundle.putString("name", "yule");
                    } else if (list.get(position).equals("军事")) {
                        bundle.putString("name", "junshi");
                    } else if (list.get(position).equals("科技")) {
                        bundle.putString("name", "keji");
                    } else if (list.get(position).equals("财经")) {
                        bundle.putString("name", "caijing");
                    } else if (list.get(position).equals("时尚")) {
                        bundle.putString("name", "shishang");
                    } else if (list.get(position).equals("体育")) {
                        bundle.putString("name", "tiyu");
                    }
                    child_fragment.setArguments(bundle);
                    return child_fragment;
                }

            }

            @Override
            public int getCount() {
                return list == null ? 0 : list.size();
            }
        });

        //TabLyout要与ViewPager关联显示
        tabLayout.setupWithViewPager(viewPager);
    }

    //点击全国按钮实现地域选择
    @OnClick(R.id.country)
    public void onViewClicked() {
        //启动
        startActivityForResult(new Intent(getActivity(), CityPickerActivity.class),
                REQUEST_CODE_PICK_CITY);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //重写onActivityResult方法
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode == RESULT_OK) {
            if (data != null) {
                String city = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                country.setText("当前选择：" + city);
            }
        }
    }

}
