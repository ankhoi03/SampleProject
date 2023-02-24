package com.example.sampleproject.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sampleproject.Adapters.LoaiSachAdapter;
import com.example.sampleproject.Adapters.PhieuMuonAdapter;
import com.example.sampleproject.DAO.LoaiSachDAO;
import com.example.sampleproject.DAO.PhieuMuonDAO;
import com.example.sampleproject.Model.LoaiSach;
import com.example.sampleproject.Model.PhieuMuon;
import com.example.sampleproject.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class QLLoaiSachFragment extends Fragment {
    LoaiSachDAO loaiSachDAO;
    ArrayList<LoaiSach> list;
    RecyclerView rcvLoaiSach;


    public QLLoaiSachFragment() {
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View   view= inflater.inflate(R.layout.fragment_ql_loai_sach, container, false);
         FloatingActionButton fabAddLoai=view.findViewById(R.id.fabAddLoaiSach);
         fabAddLoai.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                showDialogAdd();
             }
         });
         rcvLoaiSach=view.findViewById(R.id.rcvLoaiSach);
         loadData();
         return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
    private void loadData(){
        loaiSachDAO=new LoaiSachDAO(getContext());
        list=loaiSachDAO.getDSLoaiSach();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        rcvLoaiSach.setLayoutManager(layoutManager);
        LoaiSachAdapter loaiSachAdapter=new LoaiSachAdapter(list,getContext());
        rcvLoaiSach.setAdapter(loaiSachAdapter);

    }

    private void  showDialogAdd(){
        loaiSachDAO=new LoaiSachDAO(getContext());
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=this.getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog_them_loai_sach,null);
        builder.setView(view);
        EditText edtTen=view.findViewById(R.id.edtLoaiSachThem);
        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               LoaiSach loaiSach=new LoaiSach(edtTen.getText().toString());
                boolean check = loaiSachDAO.themLoaiSach(loaiSach);
                if (check) {
                    Toast.makeText(getContext(), "Thêm Loại Sách Thành Công!", Toast.LENGTH_SHORT).show();
                    loadData();
                } else {
                    Toast.makeText(getContext(), "Thêm Loại Sách Thất Bại!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        Dialog dialog= builder.create();
        dialog.show();

    }

}