package com.example.mydrive.fragmemt;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mydrive.R;
import com.example.mydrive.activity.MoniActivity;
import com.example.mydrive.activity.QuestionsActivity;
import com.example.mydrive.activity.XunxuActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class KaoshiFragment extends Fragment {
    private ImageView img_xunxu;
    private ImageView img_moni;
    private FragmentActivity activity;
    private ImageView img_error;
    private ImageView img_suiji;

    public KaoshiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kaoshi, container, false);
        activity = getActivity();
        initView(view);
        return view;
    }

    private void initView(View view) {
        img_xunxu = (ImageView) view.findViewById(R.id.img_xunxu);
        img_moni = (ImageView) view.findViewById(R.id.img_moni);
        img_xunxu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, XunxuActivity.class);
                startActivity(intent);
            }
        });
        img_moni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, MoniActivity.class);
                startActivity(intent);
            }
        });

        img_error = (ImageView) view.findViewById(R.id.img_error);
        img_suiji = (ImageView) view.findViewById(R.id.img_suiji);
        img_suiji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, MoniActivity.class);
                startActivity(intent);
            }
        });
        img_error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, QuestionsActivity.class);
                startActivity(intent);
            }
        });
    }
}
