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
import com.example.designsupportlibraryuseing.activity.CoorAndAppbarActivity;
import com.example.designsupportlibraryuseing.activity.CoorAndFABActivity;
import com.example.designsupportlibraryuseing.activity.SettingThemeActivity;

public class CoordinatorLayoutFragment extends Fragment implements View.OnClickListener {

    private Button btn1, btn2, btn3, btn4, btn5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coordinator_layout, container, false);
        btn1 = (Button) view.findViewById(R.id.button1);
        btn2 = (Button) view.findViewById(R.id.button2);
        btn3 = (Button) view.findViewById(R.id.button3);
        btn4 = (Button) view.findViewById(R.id.button4);
        btn5 = (Button) view.findViewById(R.id.button5);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                startActivity(new Intent(getActivity(), CoorAndFABActivity.class));
                break;
            case R.id.button2:
                startActivity(new Intent(getActivity(), CoorAndAppbarActivity.class));
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

            default:
        }
    }
}
