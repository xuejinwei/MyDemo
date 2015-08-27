package com.example.designsupportlibraryuseing.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.designsupportlibraryuseing.R;
import com.example.designsupportlibraryuseing.common.base.BaseActivity;
import com.example.designsupportlibraryuseing.systemCodeEdit.ScrollableListView;

import java.util.ArrayList;

public class CollapsingContactActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_contact);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Ali Connors");
        ImageView imageView = (ImageView) findViewById(R.id.imagehead);
        imageView.setImageResource(R.drawable.img_head);

        ArrayList<String> values = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            values.add(i + "  Item");
        }
        ScrollableListView listView = (ScrollableListView) findViewById(R.id.listview);
        listView.setAdapter(new ArrayAdapter<>(this, R.layout.list, values));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(CollapsingContactActivity.this, "aaa" + position, Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_collapsing_contact, menu);
        return true;
    }
}