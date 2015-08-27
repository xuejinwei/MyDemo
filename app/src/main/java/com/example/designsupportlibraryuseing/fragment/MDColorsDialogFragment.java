package com.example.designsupportlibraryuseing.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.afollestad.materialdialogs.AlertDialogWrapper;
import com.example.designsupportlibraryuseing.R;
import com.example.designsupportlibraryuseing.common.utils.AppSetting;
import com.example.designsupportlibraryuseing.common.base.BaseActivity;
import com.example.designsupportlibraryuseing.common.base.MyApplication;
import com.example.designsupportlibraryuseing.common.utils.ThemeUtils;
import com.example.designsupportlibraryuseing.widget.CircleImageView;

import java.util.HashMap;
import java.util.Map;

/**
 * 界面配色设置
 *
 * @author wangdan
 */
public class MDColorsDialogFragment extends DialogFragment
        implements OnItemClickListener, AdapterView.OnItemLongClickListener {

    public static void launch(Activity context) {
        Fragment fragment = context.getFragmentManager().findFragmentByTag("DMColorsDialogFragment");
        if (fragment != null) {
            context.getFragmentManager().beginTransaction().remove(fragment).commit();
        }

        MDColorsDialogFragment dialogFragment = new MDColorsDialogFragment();
        dialogFragment.show(context.getFragmentManager(), "DMColorsDialogFragment");
    }

    private Map<String, ColorDrawable> colorMap = new HashMap<String, ColorDrawable>();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        setCancelable(true);

        View view = View.inflate(getActivity(), R.layout.as_ui_mdcolors_dialog, null);

        GridView gridView = (GridView) view.findViewById(R.id.grid);
        gridView.setAdapter(new MDColorsAdapter());
        gridView.setOnItemClickListener(this);
        gridView.setOnItemLongClickListener(this);

        return new AlertDialogWrapper.Builder(getActivity())
                .setView(view)
                .setPositiveButton("取消", null)
                .create();
    }

    class MDColorsAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return ThemeUtils.themeColorArr.length;
        }

        @Override
        public Object getItem(int position) {
            return ThemeUtils.themeColorArr[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null)
                convertView = View.inflate(getActivity(), R.layout.as_item_mdcolors, null);

            if (!colorMap.containsKey(String.valueOf(position)))
                colorMap.put(String.valueOf(position), new ColorDrawable(getResources().getColor(ThemeUtils.themeColorArr[position][0])));

            CircleImageView imgColor = (CircleImageView) convertView.findViewById(R.id.imgColor);
            ColorDrawable colorDrawable = colorMap.get(String.valueOf(position));
            imgColor.setImageDrawable(colorDrawable);

            View imgSelected = convertView.findViewById(R.id.imgSelected);
            imgSelected.setVisibility(AppSetting.getThemeColor() == position ? View.VISIBLE : View.GONE);

            return convertView;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == AppSetting.getThemeColor()) {
            dismiss();

            return; //如果点击的主题就是当下选择的主题，直接return 不执行下面设置选择主题和重启activity的操作
        }

        AppSetting.setThemeColor(position);

        dismiss();

        if (getActivity() instanceof BaseActivity)
            ((BaseActivity) getActivity()).reload();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(MyApplication.getInstance(), getResources().getStringArray(R.array.mdColorNames)[position], Toast.LENGTH_SHORT).show();

        return true;
    }
}
