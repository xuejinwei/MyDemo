package com.example.designsupportlibraryuseing;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.designsupportlibraryuseing.common.base.BaseActivity;
import com.example.designsupportlibraryuseing.fragment.CoordinatorLayoutFragment;
import com.example.designsupportlibraryuseing.fragment.FABFragment;
import com.example.designsupportlibraryuseing.fragment.SnackBarFragment;
import com.example.designsupportlibraryuseing.fragment.TextInputLayoutFragment;
import com.example.designsupportlibraryuseing.otto.BusProvider;
import com.example.designsupportlibraryuseing.otto.ToolbarChangeEvent;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;
    private String TAG = "Screen_size";

    @Subscribe
    public void onToolbarChange(ToolbarChangeEvent event) {
        toolbar.setTitle(event.toString());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setHomeAsUpIndicator(R.drawable.ic_menu);
            ab.setDisplayHomeAsUpEnabled(true);
        }

        // 获取屏幕密度(在Context环境下，比如在Activity中执行这些代码或者传入Context对象去获得DisplayMetrics）
        DisplayMetrics dm = getResources().getDisplayMetrics();
        //getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;        // 屏幕密度（像素比例：0.75/1.0/1.5/2.0,3.0,4等）
        int densityDPI = dm.densityDpi;        // 屏幕密度(这里是系统密度）（每寸像素：120/160/240/320,480,640等）
        float xdpi = dm.xdpi; //这两个是水平和垂直方向上的dpi，如果要计算水平和垂直方向上的实际dp值，可以通过后面`屏幕的宽高差异`中的内容计算。不过你倒是可以通过这两个值大致计算屏幕的宽和高的英寸
        float ydpi = dm.ydpi;
        Log.i(TAG, "  DisplayMetrics: " + "xdpi=" + xdpi + "; ydpi=" + ydpi);
        Log.i(TAG, "  DisplayMetrics: " + "density=" + density + "; densityDPI=" + densityDPI);
        int screenWidth = dm.widthPixels;        // 屏幕像素宽（像素，如：480px）
        int screenHeight = dm.heightPixels;        // 屏幕像素高（像素，如：800px）
        Log.i(TAG, "  DisplayMetrics" + "screenWidth=" + screenWidth + "; screenHeight=" + screenHeight);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setTargetElevation(6);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }
        TabLayout tableLayout = (TabLayout) findViewById(R.id.tabs);
//        tableLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
//        tableLayout.setTabTextColors(Color.LTGRAY, Color.BLACK);
        assert viewPager != null;
        tableLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new CoordinatorLayoutFragment(), "CoordinatorLayout");
        adapter.addFragment(new TextInputLayoutFragment(), "EditText悬浮提示");
        adapter.addFragment(new SnackBarFragment(), "Snackbar");
        adapter.addFragment(new FABFragment(), "FAB");

        viewPager.setAdapter(adapter);

    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });


    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);

        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Register ourselves so that we can provide the initial value.
        BusProvider.getInstance().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Always unregister when an object no longer should be on the bus.
        BusProvider.getInstance().unregister(this);
    }
}
