package com.example.sampleproject.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sampleproject.Adapters.TopSachApdapter;
import com.example.sampleproject.DAO.ThongKeDAO;
import com.example.sampleproject.Model.Sach;
import com.example.sampleproject.R;

import java.util.ArrayList;

public class TopSachFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view= inflater.inflate(R.layout.fragment_top_sach, container, false);
        RecyclerView rcvTopSach=view.findViewById(R.id.rcvTopSach);
        ThongKeDAO thongKeDAO=new ThongKeDAO(getContext());

        ArrayList<Sach>list=new ArrayList<>();
        list=thongKeDAO.getTopSach();
        TopSachApdapter topSachApdapter=new TopSachApdapter(getContext(),list);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        rcvTopSach.setLayoutManager(layoutManager);
        rcvTopSach.setAdapter(topSachApdapter);
         return view;
    }
}