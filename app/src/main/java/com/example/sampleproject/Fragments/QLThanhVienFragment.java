package com.example.sampleproject.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

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
import com.example.sampleproject.Adapters.ThanhVienAdapter;
import com.example.sampleproject.DAO.LoaiSachDAO;
import com.example.sampleproject.DAO.ThanhVienDAO;
import com.example.sampleproject.Model.LoaiSach;
import com.example.sampleproject.Model.ThanhVien;
import com.example.sampleproject.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class QLThanhVienFragment extends Fragment {
    ThanhVienDAO thanhVienDAO;
    ArrayList<ThanhVien> list;
    RecyclerView rcvThanhVien;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_q_l_thanh_vien, container, false);
        rcvThanhVien=view.findViewById(R.id.rcvThanhVien);
        FloatingActionButton fab=view.findViewById(R.id.fabAddThanhVien);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAdd();
            }
        });
        loadData();
        return view;
    }
    private void loadData(){
        thanhVienDAO=new ThanhVienDAO(getContext());
        list=thanhVienDAO.getDSThanhVien();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        rcvThanhVien.setLayoutManager(layoutManager);
        ThanhVienAdapter thanhVienAdapter=new ThanhVienAdapter(getContext(),list);
        rcvThanhVien.setAdapter(thanhVienAdapter);
    }
    private void  showDialogAdd(){
        thanhVienDAO=new ThanhVienDAO(getContext());
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=this.getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog_thanh_vien,null);
        builder.setView(view);
        EditText edtTen=view.findViewById(R.id.edtThanhVienThem);
        EditText edtNamSinh=view.findViewById(R.id.edtNamSinhThem);
        TextView tvTen=view.findViewById(R.id.tvTenDialogThanhVien);
        tvTen.setText("THÊM THÀNH VIÊN");
        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean check = thanhVienDAO.themThanhVien(edtTen.getText().toString(),edtNamSinh.getText().toString());
                if (check) {
                    Toast.makeText(getContext(), "Thêm Thành Viên Thành Công!", Toast.LENGTH_SHORT).show();
                    loadData();
                } else {
                    Toast.makeText(getContext(), "Thêm Thành Viên Thất Bại!", Toast.LENGTH_SHORT).show();
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