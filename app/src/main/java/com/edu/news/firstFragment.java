package com.edu.news;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.edu.news.User.AllCategories;


/**
 * A simple {@link Fragment} subclass.
 */
public class firstFragment extends Fragment {
    ImageView tin1;




    public firstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);
//        tin1 = view.findViewById(R.id.tin1);
//        tin1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getContext(), detailActivity.class));
//            }
//        });

        return view;


    }

}
