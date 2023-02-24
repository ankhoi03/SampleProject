package com.example.sampleproject.Fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.sampleproject.DAO.ThongKeDAO;
import com.example.sampleproject.R;

import java.util.Calendar;


public class ThongKeDoanhThuFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_thong_ke_doanh_thu, container, false);
        EditText edtStart=view.findViewById(R.id.edtNgayBatDau);
        EditText edtEnd=view.findViewById(R.id.edtNgayKetThuc);
        EditText edtResult=view.findViewById(R.id.edtDoanhThu);
        edtResult.setEnabled(false);
        Button btnThongKe=view.findViewById(R.id.btnThongke);
        Calendar calendar=Calendar.getInstance();
        edtStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String ngay="",thang="";
                                if(dayOfMonth<10){
                                    ngay="0"+dayOfMonth;
                                }else {
                                    ngay=""+dayOfMonth;
                                }
                                if(month+1<10){
                                    thang="0"+(month+1);
                                }else {
                                    thang=""+(month+1);
                                }
                                edtStart.setText(ngay+"/"+thang+"/"+year);
                            }
                        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        edtEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String ngay="",thang="";
                                if(dayOfMonth<10){
                                    ngay="0"+dayOfMonth;
                                }else {
                                    ngay=""+dayOfMonth;
                                }
                                if(month+1<10){
                                    thang="0"+(month+1);
                                }else {
                                    thang=""+(month+1);
                                }
                                edtEnd.setText(ngay+"/"+thang+"/"+year);
                            }
                        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThongKeDAO thongKeDAO=new ThongKeDAO(getContext());
                String ngaybatdau=edtStart.getText().toString();
                String ngayketthuc=edtEnd.getText().toString();
                int doanhthu=thongKeDAO.getDoanhThu(ngaybatdau,ngayketthuc);
                edtResult.setText(doanhthu+" VND");
            }
        });
        return view;
    }
}