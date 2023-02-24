package com.example.sampleproject.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleproject.DAO.SachDAO;
import com.example.sampleproject.DAO.ThanhVienDAO;
import com.example.sampleproject.Model.Sach;
import com.example.sampleproject.Model.ThanhVien;
import com.example.sampleproject.R;

import java.util.ArrayList;
import java.util.HashMap;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Sach> list;
    private SachDAO sachDAO;
    private ArrayList<HashMap<String,Object>>listHM;
    public SachAdapter(Context context, ArrayList<Sach> list,ArrayList<HashMap<String,Object>>listHM) {
        this.context = context;
        this.list = list;
        this.listHM=listHM;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= ((Activity)context).getLayoutInflater();
        View view=inflater.inflate(R.layout.sach_view_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Sach sach=list.get(position);
        holder.tvTenSC.setText(sach.getTenSach());
        holder.tvMaSC.setText("Mã sách: "+sach.getMaSach());
        holder.tvMaLoaiSC.setText("Mã loại: "+sach.getMaLoai());
        holder.tvTheLoai.setText("Thể loại: "+sach.getTenLoai());
        holder.tvGiaThue.setText("Giá thuê: "+sach.getGiaThue());
        sachDAO=new SachDAO(context);
        if(sachDAO.checkSach(list.get(holder.getAdapterPosition()).getMaSach())){
            holder.ivDeleteSC.setVisibility(View.INVISIBLE);
        }
        holder.ivDeleteSC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean check=sachDAO.deleteSach(list.get(holder.getAdapterPosition()).getMaSach());
                if(check){
                    list.clear();
                    list=sachDAO.getDSDauSach();
                    notifyDataSetChanged();
                    Toast.makeText(context,"Xóa thành công!",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context,"Xóa thất bại!",Toast.LENGTH_SHORT).show();
                }
            }

        });
        holder.ivEditSC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Sach sach1=list.get(holder.getAdapterPosition());
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                LayoutInflater inflater=((Activity)context).getLayoutInflater();
                View view=inflater.inflate(R.layout.dialog_sach,null);
                TextView edtTenDialog=view.findViewById(R.id.tvSachDialog);
                edtTenDialog.setText("SỬA THÔNG TIN SÁCH");
                EditText edtTen=view.findViewById(R.id.edtTenSCDialog);
                EditText edtTien=view.findViewById(R.id.edtTienSCDialog);
                Spinner spinner=view.findViewById(R.id.spnLoaiSach);
                SimpleAdapter simpleAdapter = new SimpleAdapter(context,listHM, android.R.layout.simple_list_item_1,new String[]{"tenLoai"},new int[]{android.R.id.text1});
                spinner.setAdapter(simpleAdapter);
                int index=0;
                int position=-1;
                for (HashMap<String,Object> item:listHM) {
                    if((int)item.get("maLoai")==sach.getMaLoai()){
                        position=index;
                    }
                    index++;

                }
                spinner.setSelection(position);
                edtTen.setText(sach1.getTenSach());
                edtTien.setText(sach1.getGiaThue()+"");
                builder.setView(view);
                builder.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String tenSach=edtTen.getText().toString();
                        int tien=Integer.parseInt(edtTien.getText().toString()) ;
                        HashMap<String,Object> hs= (HashMap<String, Object>) spinner.getSelectedItem();
                        int maloai= (int) hs.get("maLoai");
                        boolean check = sachDAO.suaSach(sach.getMaSach(),tenSach,tien,maloai);
                        if (check) {
                            Toast.makeText(context, "Sửa Sách Thành Công!", Toast.LENGTH_SHORT).show();
                            list.clear();
                            list=sachDAO.getDSDauSach();
                            notifyDataSetChanged();
                        } else {
                            Toast.makeText(context, "Sửa Sách Thất Bại!", Toast.LENGTH_SHORT).show();
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

        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTenSC,tvMaSC,tvMaLoaiSC,tvTheLoai,tvGiaThue;
        ImageView ivEditSC,ivDeleteSC;
        CardView cardSach;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenSC=itemView.findViewById(R.id.tvTenSC);
            tvMaSC=itemView.findViewById(R.id.tvMaSC);
            tvMaLoaiSC=itemView.findViewById(R.id.tvMaLoaiSC);
            tvTheLoai=itemView.findViewById(R.id.tvTheLoai);
            tvGiaThue=itemView.findViewById(R.id.tvGiaThue);
            ivEditSC=itemView.findViewById(R.id.ivEditSC);
            ivDeleteSC=itemView.findViewById(R.id.ivDeleteSC);
            cardSach=itemView.findViewById(R.id.cardSach);
        }
    }

}