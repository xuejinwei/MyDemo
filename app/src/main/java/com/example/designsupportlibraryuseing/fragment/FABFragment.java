package com.example.designsupportlibraryuseing.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.designsupportlibraryuseing.R;

public class FABFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fab, container, false);
        FloatingActionButton fab=(FloatingActionButton) view.findViewById(R.id.fab);
        FloatingActionButton fabMini=(FloatingActionButton) view.findViewById(R.id.fab_mini);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "这个是FloatingActionButton默认大小", Toast.LENGTH_SHORT).show();
            }
        });
        fabMini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "这个是FloatingActionButton迷你大小", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
