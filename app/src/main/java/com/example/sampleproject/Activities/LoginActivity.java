package com.example.sampleproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sampleproject.DAO.ThuThuDAO;
import com.example.sampleproject.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText edtUser=findViewById(R.id.edtUser);
        EditText edtPassword=findViewById(R.id.edtMK);
        Button btnLogin=findViewById(R.id.btnLogin);
        SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN", MODE_PRIVATE);
        Boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn",false);
        if(isLoggedIn){
            edtUser.setText(sharedPreferences.getString("mathuthu",""));
            edtPassword.setText(sharedPreferences.getString("pass",""));
        }
        ThuThuDAO thuThuDAO=new ThuThuDAO(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=edtUser.getText().toString();
                String pass=edtPassword.getText().toString();
                if(user.length()!=0||pass.length()!=0) {
                    if (thuThuDAO.ckeckLogin(user, pass)) {
                        SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("isLoggedIn",true);
                        editor.putString("mathuthu", user);
                        editor.putString("pass", pass);
                        editor.commit();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Đăng nhập không thành công!", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(LoginActivity.this, "Nhập User và Password!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}