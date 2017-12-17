package com.app.homework.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.app.homework.R;
import com.app.homework.activity.FaHuoDongActivity;

/**
 * 发布fragment
 */
public class FaBu02_Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //设置布局
        View view = inflater.inflate(R.layout.fabu_fragment, container, false);
        Intent intent = new Intent(getActivity(), FaHuoDongActivity.class);
        startActivity(intent);
        return view;

    }
}
