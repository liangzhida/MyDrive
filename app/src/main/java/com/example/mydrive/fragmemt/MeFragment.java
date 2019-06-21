package com.example.mydrive.fragmemt;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mydrive.R;
import com.example.mydrive.activity.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment {


    private ImageView img_touxiang;
    private TextView tv_denlu;
    private FragmentActivity activity;

    public MeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        activity = getActivity();
        initView(view);
        tv_denlu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, LoginActivity.class);
               
                startActivity(intent);
            }
        });

        return view;
    }

    private void initView(View view) {
        img_touxiang = (ImageView) view.findViewById(R.id.img_touxiang);
        tv_denlu = (TextView) view.findViewById(R.id.tv_denlu);
    }
}
