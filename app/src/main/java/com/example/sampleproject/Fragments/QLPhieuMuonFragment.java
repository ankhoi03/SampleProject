package com.example.sampleproject.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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
import android.widget.Toast;

import com.example.sampleproject.Adapters.PhieuMuonAdapter;
import com.example.sampleproject.DAO.PhieuMuonDAO;
import com.example.sampleproject.DAO.SachDAO;
import com.example.sampleproject.DAO.ThanhVienDAO;
import com.example.sampleproject.Model.PhieuMuon;
import com.example.sampleproject.Model.Sach;
import com.example.sampleproject.Model.ThanhVien;
import com.example.sampleproject.R;
import com.example.sampleproject.R.id;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;


public class QLPhieuMuonFragment extends Fragment {
    PhieuMuonDAO phieuMuonDAO;
    ArrayList<PhieuMuon> list;
    RecyclerView rcvQLPhieuMuon;
    public QLPhieuMuonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_ql_phieu_muon, container, false);

        rcvQLPhieuMuon=view.findViewById(R.id.rcvPhieuMuon);
        FloatingActionButton fabAddPhieuMuon=view.findViewById(id.fabAddPhieuMuon);
        //data

        //adpter
        loadData();
        fabAddPhieuMuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAdd();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void  showDialogAdd(){
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=this.getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog_them_phieumuon,null);
        builder.setView(view);
        Spinner spnThanhVien=view.findViewById(R.id.spnThanhVien);
        Spinner spnSach=view.findViewById(id.spnSach);
        EditText edtTien=view.findViewById(R.id.edtTien);
        EditText edtNgay=view.findViewById(R.id.edtNgay);
        EditText edtThuThu=view.findViewById(id.edtThuThu);
        edtNgay.setText(getNgay());
        edtNgay.setEnabled(false);
        edtThuThu.setText(getTHUTHU());
        edtThuThu.setEnabled(false);
        getDataThanhVien(spnThanhVien);
        getDataSach(spnSach);
        spnSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String,Object> hmSach= (HashMap<String, Object>) spnSach.getSelectedItem();
                int tienThueSach= (int) hmSach.get("tienSach");
                edtTien.setText(tienThueSach+"₫");
                edtTien.setEnabled(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //lay ma thanh vien
                HashMap<String,Object> hsTV= (HashMap<String, Object>) spnThanhVien.getSelectedItem();
                int matv= (int) hsTV.get("matv");
                //lay ma sach
                HashMap<String,Object> hsSach= (HashMap<String, Object>) spnSach.getSelectedItem();
                int masach= (int) hsSach.get("maSach");
                int tienSach= (int) hsSach.get("tienSach");
                themPhieuMuon(matv,masach,tienSach);
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

    private void getDataThanhVien(Spinner spnThanhVien){
        ThanhVienDAO thanhVienDAO=new ThanhVienDAO(getContext());
        ArrayList<ThanhVien> list=thanhVienDAO.getDSThanhVien();

        ArrayList<HashMap<String,Object>> listHM=new ArrayList<>();
        for (ThanhVien tv:list) {
            HashMap<String,Object> hs=new HashMap<>();
            hs.put("matv",tv.getMaThanhVien());
            hs.put("hoten",tv.getHoTen());
            listHM.add(hs);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(),listHM, android.R.layout.simple_list_item_1,new String[]{"hoten"},new int[]{android.R.id.text1});
        spnThanhVien.setAdapter(simpleAdapter);
    }
    private void getDataSach(Spinner spnSach){
        SachDAO sachDAO=new SachDAO(getContext());
        ArrayList<Sach> list=sachDAO.getDSDauSach();

        ArrayList<HashMap<String,Object>> listHM=new ArrayList<>();
        for (Sach sach:list) {
            HashMap<String,Object> hs=new HashMap<>();
            hs.put("maSach",sach.getMaSach());
            hs.put("tenSach",sach.getTenSach());
            hs.put("tienSach",sach.getGiaThue());
            listHM.add(hs);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(),listHM, android.R.layout.simple_list_item_1,new String[]{"tenSach"},new int[]{android.R.id.text1});
        spnSach.setAdapter(simpleAdapter);
    }
    private void themPhieuMuon(int matv, int masach, int tien) {
        //lay ma thu thu
//        SharedPreferences sharedPreferences = getContext().getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
//        String matt = sharedPreferences.getString("mathuthu", "");
//        Date currentTime = Calendar.getInstance().getTime();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
//        String ngay = simpleDateFormat.format(currentTime);
        String ngay=getNgay();
        String matt=getTHUTHU();
        PhieuMuon phieuMuon = new PhieuMuon(matv, masach, tien, 1, matt, ngay);
        boolean check = phieuMuonDAO.themPhieuMuon(phieuMuon);
        if (check) {
            Toast.makeText(getContext(), "Thêm Phiếu Mượn Thành Công!", Toast.LENGTH_SHORT).show();
            loadData();
        } else {
            Toast.makeText(getContext(), "Thêm Phiếu Mượn Thất Bại!", Toast.LENGTH_SHORT).show();
        }


    }
    private String getTHUTHU(){
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
        String matt = sharedPreferences.getString("mathuthu", "");
        return matt;
    }
    private String getNgay(){
        Date currentTime= Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String ngay=simpleDateFormat.format(currentTime);
        return ngay;
    }

    private void loadData(){
        phieuMuonDAO=new PhieuMuonDAO(getContext());
        list=phieuMuonDAO.getDSPhieuMuon();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        rcvQLPhieuMuon.setLayoutManager(layoutManager);
        PhieuMuonAdapter phieuMuonAdapter=new PhieuMuonAdapter(list,getContext());
        rcvQLPhieuMuon.setAdapter(phieuMuonAdapter);

    }
}