package com.example.designsupportlibraryuseing.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.designsupportlibraryuseing.R;

public class SnackBarFragment extends Fragment implements View.OnClickListener {

    private Button btn1, btn2, btn3, btn4, btn5;
    Intent intent;

    private PopupWindow popupWindow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_snack_bar, container, false);
        btn1 = (Button) view.findViewById(R.id.button1);
        btn2 = (Button) view.findViewById(R.id.button2);
        btn3 = (Button) view.findViewById(R.id.button3);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        return view;
    }

    private void showPopUp(View v) {
        LinearLayout layout = new LinearLayout(getActivity());
        layout.setBackgroundColor(Color.GRAY);
        TextView tv = new TextView(getActivity());
        tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        tv.setText("I'm a pop -----------------------------!");
        tv.setTextColor(Color.WHITE);
        layout.addView(tv);

        popupWindow = new PopupWindow(layout, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setAnimationStyle(R.style.popwin_anim_style);

        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        int[] location = new int[2];
        v.getLocationOnScreen(location);
        popupWindow.showAsDropDown(v);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                Snackbar.make(getView(), "Snackbar默认样式", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.button2:
                Snackbar.make(getView(), "Snackbar添加ACTION事件", Snackbar.LENGTH_SHORT).setAction("ACTION", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "Snackbar上ACTION点击事件", Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;
            case R.id.button3:
                Snackbar snackBar = Snackbar.make(getView(), "修改Snackbar的颜色", Snackbar.LENGTH_SHORT);
                snackBar.setActionTextColor(Color.RED);//ACTION文字颜色

                View view = snackBar.getView();
                view.setBackgroundColor(Color.LTGRAY);//Snackbar背景颜色

                TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                tv.setTextColor(Color.BLACK);//Snackbar文字颜色
                snackBar.setAction("Delete", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "Snackbar上ACTION点击事件", Toast.LENGTH_SHORT).show();
                    }
                }).show();

                break;

            default:
        }
    }
}
