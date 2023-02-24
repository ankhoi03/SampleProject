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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleproject.DAO.LoaiSachDAO;
import com.example.sampleproject.DAO.PhieuMuonDAO;
import com.example.sampleproject.Model.LoaiSach;
import com.example.sampleproject.Model.PhieuMuon;
import com.example.sampleproject.R;

import java.util.ArrayList;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.ViewHolder>{
    LoaiSachDAO loaiSachDAO;
    Context context;
    ArrayList<LoaiSach> list;
    public LoaiSachAdapter(ArrayList<LoaiSach> list, Context context) {
        this.list=list;
        this.context = context;
        loaiSachDAO=new LoaiSachDAO(context);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= ((Activity)context).getLayoutInflater();
        View view=inflater.inflate(R.layout.loai_sach_view_item,parent,false);
        LoaiSachAdapter.ViewHolder viewHolder=new LoaiSachAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LoaiSach loaiSach=list.get(position);
        holder.tvTenLoai.setText(loaiSach.getTenTheLoai());
        holder.tvMaLoai.setText("Mã Loại: "+loaiSach.getMaTheLoai());
        loaiSachDAO=new LoaiSachDAO(context);
        if(loaiSachDAO.checkLoaiSach(list.get(holder.getAdapterPosition()).getMaTheLoai())){
            holder.ivDeleteLoaiSach.setVisibility(View.INVISIBLE);
        }
        holder.ivDeleteLoaiSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean check=loaiSachDAO.deleteLoaiSach(list.get(holder.getAdapterPosition()).getMaTheLoai());
                if(check){
                    list.clear();
                    list=loaiSachDAO.getDSLoaiSach();
                    notifyDataSetChanged();
                    Toast.makeText(context,"Xóa thành công!",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context,"Xóa thất bại!",Toast.LENGTH_SHORT).show();
                }
            }

        });
        holder.ivEditLoaiSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaiSach loaiSach=list.get(holder.getAdapterPosition());
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                LayoutInflater inflater=((Activity)context).getLayoutInflater();
                View view=inflater.inflate(R.layout.dialog_them_loai_sach,null);
                TextView edtTenDialog=view.findViewById(R.id.tvTenDialogLoaiSach);
                edtTenDialog.setText("SỬA LOẠI SÁCH");
                EditText edtTen=view.findViewById(R.id.edtLoaiSachThem);
                edtTen.setText(loaiSach.getTenTheLoai());
                builder.setView(view);
                builder.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean check = loaiSachDAO.suaLoaiSach(loaiSach,edtTen.getText().toString());
                        if (check) {
                            list.clear();
                            list=loaiSachDAO.getDSLoaiSach();
                            notifyDataSetChanged();
                            Toast.makeText(context, "Sửa Phiếu Mượn Thành Công!", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(context, "Sửa Phiếu Mượn Thất Bại!", Toast.LENGTH_SHORT).show();
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
        TextView tvTenLoai,tvMaLoai;
        ImageView ivDeleteLoaiSach,ivEditLoaiSach;
        CardView cardViewLoaiSach;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenLoai=itemView.findViewById(R.id.tvTenLoai);
            tvMaLoai=itemView.findViewById(R.id.tvMaLoai);
            ivDeleteLoaiSach=itemView.findViewById(R.id.ivDeleteLoai);
            ivEditLoaiSach=itemView.findViewById(R.id.ivEditLoai);
            cardViewLoaiSach=itemView.findViewById(R.id.cardLoaiSach);
        }
    }

}
