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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sampleproject.Adapters.SachAdapter;
import com.example.sampleproject.Adapters.ThanhVienAdapter;
import com.example.sampleproject.DAO.LoaiSachDAO;
import com.example.sampleproject.DAO.SachDAO;
import com.example.sampleproject.DAO.ThanhVienDAO;
import com.example.sampleproject.Model.LoaiSach;
import com.example.sampleproject.Model.Sach;
import com.example.sampleproject.Model.ThanhVien;
import com.example.sampleproject.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;


public class QLSachFragment extends Fragment {
    SachDAO sachDAO;
    ArrayList<Sach> list;
    RecyclerView rcvSach;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_q_l_sach, container, false);
        rcvSach=view.findViewById(R.id.rcvSach);
        FloatingActionButton fabAdd=view.findViewById(R.id.fabAddSach);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAdd();
            }
        });
        loadData();
        return view;
    }
    private void loadData(){
        sachDAO=new SachDAO(getContext());
        list=sachDAO.getDSDauSach();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        rcvSach.setLayoutManager(layoutManager);
        SachAdapter sachAdapter=new SachAdapter(getContext(),list,getDataLoaiSach());
        rcvSach.setAdapter(sachAdapter);
    }
    private void  showDialogAdd(){
        sachDAO=new SachDAO(getContext());
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=this.getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog_sach,null);
        TextView tieude=view.findViewById(R.id.tvSachDialog);
        tieude.setText("THÊM SÁCH");
        builder.setView(view);
        EditText edtTen=view.findViewById(R.id.edtTenSCDialog);
        EditText edtTien=view.findViewById(R.id.edtTienSCDialog);
        Spinner spnLoai=view.findViewById(R.id.spnLoaiSach);
        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(),getDataLoaiSach(), android.R.layout.simple_list_item_1,new String[]{"tenLoai"},new int[]{android.R.id.text1});
        spnLoai.setAdapter(simpleAdapter);
        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String tenSach=edtTen.getText().toString();
                int tien=Integer.parseInt(edtTien.getText().toString()) ;
                HashMap<String,Object> hs= (HashMap<String, Object>) spnLoai.getSelectedItem();
                int maloai= (int) hs.get("maLoai");
                boolean check = sachDAO.themSach(tenSach,tien,maloai);
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
    private ArrayList<HashMap<String,Object>> getDataLoaiSach(){
        LoaiSachDAO loaiSachDAOsachDAO=new LoaiSachDAO(getContext());
        ArrayList<LoaiSach> list= loaiSachDAOsachDAO.getDSLoaiSach();

        ArrayList<HashMap<String,Object>> listHM=new ArrayList<>();
        for (LoaiSach loaiSach:list) {
            HashMap<String,Object> hs=new HashMap<>();
            hs.put("maLoai",loaiSach.getMaTheLoai());
            hs.put("tenLoai",loaiSach.getTenTheLoai());

            listHM.add(hs);
        }
        return listHM;
    }
}