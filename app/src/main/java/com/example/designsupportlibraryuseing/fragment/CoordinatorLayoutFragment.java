package com.example.designsupportlibraryuseing.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.designsupportlibraryuseing.R;
import com.example.designsupportlibraryuseing.activity.CollapsingContactActivity;
import com.example.designsupportlibraryuseing.activity.CollapsingToolbarActivity;
import com.example.designsupportlibraryuseing.activity.Main2Activity;
import com.example.designsupportlibraryuseing.activity.OkHttpActivity;
import com.example.designsupportlibraryuseing.activity.SettingThemeActivity;
import com.example.designsupportlibraryuseing.otto.BusProvider;
import com.example.designsupportlibraryuseing.otto.OttoActivity;
import com.example.designsupportlibraryuseing.otto.ToolbarChangeEvent;
import com.squareup.otto.Produce;

public class CoordinatorLayoutFragment extends Fragment implements View.OnClickListener {

    private Button btn1, btn2, btn3, btn4, btn5, btn6;


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

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);

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
            default:
        }
    }
}
