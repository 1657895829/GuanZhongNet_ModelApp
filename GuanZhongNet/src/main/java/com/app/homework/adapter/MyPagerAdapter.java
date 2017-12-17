package com.app.homework.adapter;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.app.homework.activity.WebUrlActivity;
import com.app.homework.bean.JunShiBean;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.List;

//轮播图适配器
public class MyPagerAdapter extends PagerAdapter{
    private Context context;
    private Handler handler;
    private List<JunShiBean.NewslistBean> list;

    public MyPagerAdapter(Context context, List<JunShiBean.NewslistBean> list, Handler handler) {
        this.context = context;
        this.handler = handler;
        this.list = list;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        //设置视图
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageLoader.getInstance().displayImage(list.get(position%list.size()).getPicUrl(),imageView);

        //imageView触摸的监听事件
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                boolean isTiaoZhuan = true;   //设定标志为true

                switch (motionEvent.getAction()){           //获取触摸的动作
                    case MotionEvent.ACTION_DOWN:
                        isTiaoZhuan = false;
                        handler.removeCallbacksAndMessages(null);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        isTiaoZhuan = false;
                        handler.removeCallbacksAndMessages(null);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        isTiaoZhuan = false;
                        handler.sendEmptyMessageDelayed(0,2888);
                        break;

                    //抬起后的动作时，获取图片路径，进行跳转页面
                    case MotionEvent.ACTION_UP :
                        if (isTiaoZhuan){
                            String web_url = list.get(position%list.size()).getUrl();
                            Intent intent = new Intent(context, WebUrlActivity.class);
                            intent.putExtra("url",web_url);
                            context.startActivity(intent);
                        }
                        handler.sendEmptyMessageDelayed(0,2888);
                        break;
                }

                //自己处理触摸事件....如果当前位置返回了true,,,点击事件将不会执行,,,表示动作自己处理,不会传递
                return true;
            }
        });

        //添加视图到容器
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
