package com.example.designsupportlibraryuseing.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.designsupportlibraryuseing.R;
import com.example.designsupportlibraryuseing.activity.CollapsingContactActivity;
import com.example.designsupportlibraryuseing.activity.CollapsingToolbarActivity;
import com.example.designsupportlibraryuseing.activity.Main2Activity;
import com.example.designsupportlibraryuseing.activity.OkHttpActivity;
import com.example.designsupportlibraryuseing.activity.SettingThemeActivity;
import com.example.designsupportlibraryuseing.activity.TabBarActivity;
import com.example.designsupportlibraryuseing.otto.BusProvider;
import com.example.designsupportlibraryuseing.otto.OttoActivity;
import com.example.designsupportlibraryuseing.otto.ToolbarChangeEvent;
import com.squareup.otto.Produce;

public class CoordinatorLayoutFragment extends Fragment implements View.OnClickListener {

    private Button btn1, btn2, btn3, btn4, btn5, btn6,btn7;
    private TextView tv_span;


    private int i = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coordinator_layout, container, false);
        btn1 = (Button) view.findViewById(R.id.button1);
        btn2 = (Button) view.findViewById(R.id.button2);
        btn3 = (Button) view.findViewById(R.id.button3);
        btn4 = (Button) view.findViewById(R.id.button4);
        btn5 = (Button) view.findViewById(R.id.button5);
        btn6 = (Button) view.findViewById(R.id.button6);
        btn7 = (Button) view.findViewById(R.id.button7);
        tv_span = (TextView) view.findViewById(R.id.tv_span);

        String str = "这是设置TextView部分文字背景颜色和前景颜色的demo!";
        int bstart = str.indexOf("背景");
        int bend = bstart + "背景".length();


        int fstart = str.indexOf("前景");
        int fend = fstart + "前景".length();
        SpannableStringBuilder style = new SpannableStringBuilder(str);

        style.setSpan(new BackgroundColorSpan(Color.RED), bstart, bend, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        style.setSpan(new ForegroundColorSpan(Color.RED), fstart, fend, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

        tv_span.setText(style);


        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        return view;
    }

    @Produce
    public ToolbarChangeEvent produceToolbarChangedEvent() {
        // Provide an initial value for location based on the last known position.
        return new ToolbarChangeEvent(Integer.toString(i++));
    }

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                startActivity(new Intent(getActivity(), Main2Activity.class));
//                BusProvider.getInstance().post(new ToolbarChangeEvent(Integer.toString(i++)));
                BusProvider.getInstance().post(produceToolbarChangedEvent());
                break;
            case R.id.button2:
                startActivity(new Intent(getActivity(), OttoActivity.class));
                break;
            case R.id.button3:
                startActivity(new Intent(getActivity(), CollapsingToolbarActivity.class));
                break;
            case R.id.button4:
                startActivity(new Intent(getActivity(), CollapsingContactActivity.class));
                break;
            case R.id.button5:
                startActivity(new Intent(getActivity(), SettingThemeActivity.class));
                break;
            case R.id.button6:
                startActivity(new Intent(getActivity(), OkHttpActivity.class));
                break;
            case R.id.button7:
                startActivity(new Intent(getActivity(), TabBarActivity.class));
                break;
            default:
        }
    }
}
