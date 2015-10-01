package com.example.designsupportlibraryuseing.fragment;


import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.designsupportlibraryuseing.R;
import com.example.designsupportlibraryuseing.activity.CollapsingContactActivity;
import com.example.designsupportlibraryuseing.activity.CollapsingToolbarActivity;
import com.example.designsupportlibraryuseing.activity.OkHttpActivity;
import com.example.designsupportlibraryuseing.activity.SettingThemeActivity;
import com.example.designsupportlibraryuseing.otto.BusProvider;
import com.example.designsupportlibraryuseing.otto.OttoActivity;
import com.example.designsupportlibraryuseing.otto.ToolbarChangeEvent;

public class CoordinatorLayoutFragment extends Fragment implements View.OnClickListener {

    private Button btn1, btn2, btn3, btn4, btn5,btn6;
    private int i=1;

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

//    @Produce
//    public ToolbarChangeEvent produceToolbarChangedEvent() {
//        // Provide an initial value for location based on the last known position.
//        return new ToolbarChangeEvent(Integer.toString(i++));
//    }
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
//                startActivity(new Intent(getActivity(), CoorAndFABActivity.class));
                BusProvider.getInstance().post(new ToolbarChangeEvent(Integer.toString(i++)));
                break;
            case R.id.button2:
//                sendSMS("16300", "xykdmm");
//                startActivity(new Intent(getActivity(), CoorAndAppbarActivity.class));
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
    public void sendSMS(String phoneNumber, String message) {
        // ---sends an SMS message to another device---
        SmsManager sms = SmsManager.getDefault();
        String SENT_SMS_ACTION = "SENT_SMS_ACTION";
        String DELIVERED_SMS_ACTION = "DELIVERED_SMS_ACTION";

        // create the sentIntent parameter
        Intent sentIntent = new Intent(SENT_SMS_ACTION);
        PendingIntent sentPI = PendingIntent.getBroadcast(getActivity(), 0, sentIntent,
                0);

        // create the deilverIntent parameter
        Intent deliverIntent = new Intent(DELIVERED_SMS_ACTION);
        PendingIntent deliverPI = PendingIntent.getBroadcast(getActivity(), 0,
                deliverIntent, 0);

        // register the Broadcast Receivers

        getActivity().registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context _context, Intent _intent) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(getActivity().getBaseContext(), "短信发送，密码获取成功",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(getActivity().getBaseContext(), "短信发送失败",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(getActivity().getBaseContext(), "请关闭飞航模式在获取密码",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(getActivity().getBaseContext(), "由于PDU，发送失败",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(SENT_SMS_ACTION));
        getActivity().registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context _context, Intent _intent) {
                Toast.makeText(getActivity(), "SMS delivered actions",
                        Toast.LENGTH_SHORT).show();
            }
        }, new IntentFilter(DELIVERED_SMS_ACTION));

        sms.sendTextMessage(phoneNumber, null, message, sentPI, deliverPI);
    }
}
