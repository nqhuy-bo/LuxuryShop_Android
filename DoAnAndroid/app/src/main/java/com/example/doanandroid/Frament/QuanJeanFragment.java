package com.example.doanandroid.Frament;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.doanandroid.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuanJeanFragment extends Fragment {

    private View view;
    public QuanJeanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_quan_jean, container, false);
        return view;
    }
}
