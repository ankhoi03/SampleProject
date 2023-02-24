package com.example.sampleproject.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sampleproject.Activities.LoginActivity;
import com.example.sampleproject.Activities.MainActivity;
import com.example.sampleproject.DAO.ThuThuDAO;
import com.example.sampleproject.R;

public class DoiMatKhauFragment extends Fragment {
    ThuThuDAO thuThuDAO;


    public DoiMatKhauFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_doi_mat_khau, container, false);
        Button btnDoiMK=view.findViewById(R.id.btnDoiMK);
        EditText edtOldPass=view.findViewById(R.id.edtMKCu);
        EditText edtNewPass=view.findViewById(R.id.edtMKMoi);
        EditText edtXNNewPass=view.findViewById(R.id.edtMKMoiXN);
        btnDoiMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPass=edtOldPass.getText().toString();
                String newPass=edtNewPass.getText().toString();
                String reNewPass=edtXNNewPass.getText().toString();
                if(oldPass.length()==0||newPass.length()==0||reNewPass.length()==0){
                    Toast.makeText(getContext(),"Nhập đầy đủ thông tin!!",Toast.LENGTH_SHORT).show();
                }else {
                    if(newPass.equals(reNewPass)){
                        thuThuDAO=new ThuThuDAO(getContext());
                        SharedPreferences sharedPreferences = getContext().getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
                        String matt = sharedPreferences.getString("mathuthu", "");
                        boolean check =thuThuDAO.doiMatKhau(matt,oldPass,newPass);
                        if(check){
                            Toast.makeText(getContext(),"Cập nhật nhật khẩu thành công!!",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getContext(), LoginActivity.class));
                            SharedPreferences preferences=getContext().getSharedPreferences("THONGTIN",Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor=preferences.edit();
                            editor.remove("isLoggedIn");
                            editor.remove("mathuthu");
                            editor.remove("pass");
                            editor.apply();
                            getActivity().finish();
                        }else {
                            Toast.makeText(getContext(),"Cập nhật nhật khẩu thất bại!!",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getContext(),"Nhập lại mật khẩu không trùng khớp!!!",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        return view;
    }

}