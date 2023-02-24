package com.example.sampleproject.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.sampleproject.DAO.SachDAO;
import com.example.sampleproject.Fragments.DoiMatKhauFragment;
import com.example.sampleproject.Fragments.QLLoaiSachFragment;
import com.example.sampleproject.Fragments.QLPhieuMuonFragment;
import com.example.sampleproject.Fragments.QLSachFragment;
import com.example.sampleproject.Fragments.QLThanhVienFragment;
import com.example.sampleproject.Fragments.ThongKeDoanhThuFragment;
import com.example.sampleproject.Fragments.TopSachFragment;
import com.example.sampleproject.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //add toolbar
        Toolbar toolbar=findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        drawerLayout=findViewById(R.id.drawer_layout);
        NavigationView navigationView=findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(MainActivity.this, drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if(savedInstanceState==null){
            QLPhieuMuonFragment fragment=new QLPhieuMuonFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).commit();
            setTitle("Quản Lý Phiếu Mượn");
        }
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                Fragment fragment=new Fragment();
                switch (id){
                    case R.id.mQLPhieuMuon:
                        fragment=new QLPhieuMuonFragment();
                        break;
                    case R.id.mQLLoaiSach:
                        fragment=new QLLoaiSachFragment();
                        break;
                    case R.id.mDoiMatKhau:
                        fragment=new DoiMatKhauFragment();
                        break;
                    case R.id.mTop10:
                        fragment=new TopSachFragment();
                        break;
                    case R.id.mDoanhThu:
                        fragment=new ThongKeDoanhThuFragment();
                        break;
                    case R.id.mQLThanhVien:
                        fragment=new QLThanhVienFragment();
                        break;
                    case R.id.mQLSach:
                        fragment=new QLSachFragment();
                        break;
                    case R.id.mDangXuat:
                        logOut();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                setTitle(item.getTitle());
                return true;
            }
            public void logOut(){
                SharedPreferences preferences=getSharedPreferences("THONGTIN",MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.remove("isLoggedIn");
                editor.remove("mathuthu");
                editor.remove("pass");
                editor.apply();
                Intent loginIntent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        });
    }

}