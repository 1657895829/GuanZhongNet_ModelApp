package com.app.homework.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.app.homework.R;
import com.app.homework.fragment.FaBu02_Fragment;
import com.app.homework.fragment.GuanLi03_Fragment;
import com.app.homework.fragment.Mine04_Fragment;
import com.app.homework.fragment.ShouYe01_Fragment;
import com.hjm.bottomtabbar.BottomTabBar;
import butterknife.BindView;
import butterknife.ButterKnife;

//Fragment页面分类显示   key（ 2fcef79710677712d3d44b9fedcd0635 ）
public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.bottom_tab_bar)
    BottomTabBar bottomTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        //初始化Fragment
        bottomTabBar.init(getSupportFragmentManager())
                .setImgSize(50, 50)      //图片大小
                .setFontSize(12)                       //字体大小
                .setTabPadding(4, 6, 10)//选项卡的间距
                .setChangeColor(Color.RED, Color.BLACK)     //选项卡的选择颜色
                .addTabItem("活动", R.drawable.home_, ShouYe01_Fragment.class)
                .addTabItem("发布", R.drawable.found, FaBu02_Fragment.class)
                .addTabItem("管理", R.drawable.special, GuanLi03_Fragment.class)
                .addTabItem("我的", R.drawable.my, Mine04_Fragment.class)
                .isShowDivider(true)    //是否包含分割线
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {
                        Log.i("TGA", "位置：" + position + "      选项卡：" + name);
                    }
                });
    }

}
