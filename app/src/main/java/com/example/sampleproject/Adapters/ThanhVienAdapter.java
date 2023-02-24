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

import com.example.sampleproject.DAO.ThanhVienDAO;
import com.example.sampleproject.Model.LoaiSach;
import com.example.sampleproject.Model.ThanhVien;
import com.example.sampleproject.R;

import java.util.ArrayList;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ViewHolder>{
    private Context context;
    private ArrayList<ThanhVien> list;
    private ThanhVienDAO thanhVienDAO;
    public ThanhVienAdapter(Context context, ArrayList<ThanhVien> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= ((Activity)context).getLayoutInflater();
        View view=inflater.inflate(R.layout.thanhvien_view_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            ThanhVien thanhVien=list.get(position);
            holder.tvTenTV.setText(thanhVien.getHoTen());
            holder.tvMaTV.setText("Mã thành viên: "+thanhVien.getMaThanhVien());
            holder.tvNamSinhTV.setText("Năm sinh: "+thanhVien.getNamSinh());
            thanhVienDAO=new ThanhVienDAO(context);
        if(thanhVienDAO.checkThanhVien(list.get(holder.getAdapterPosition()).getMaThanhVien())){
            holder.ivDeleteTV.setVisibility(View.INVISIBLE);
        }
        holder.ivDeleteTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean check=thanhVienDAO.deleteThanhVien(list.get(holder.getAdapterPosition()).getMaThanhVien());
                if(check){
                    list.clear();
                    list=thanhVienDAO.getDSThanhVien();
                    notifyDataSetChanged();
                    Toast.makeText(context,"Xóa thành công!",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context,"Xóa thất bại!",Toast.LENGTH_SHORT).show();
                }
            }

        });
            holder.ivEditTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ThanhVien thanhVien1=list.get(holder.getAdapterPosition());
                    AlertDialog.Builder builder=new AlertDialog.Builder(context);
                    LayoutInflater inflater=((Activity)context).getLayoutInflater();
                    View view=inflater.inflate(R.layout.dialog_thanh_vien,null);
                    TextView edtTenDialog=view.findViewById(R.id.tvTenDialogThanhVien);
                    edtTenDialog.setText("SỬA THÀNH VIÊN");
                    EditText edtTen=view.findViewById(R.id.edtThanhVienThem);
                    EditText edtNamSinh=view.findViewById(R.id.edtNamSinhThem);
                    edtTen.setText(thanhVien1.getHoTen());
                    edtNamSinh.setText(thanhVien1.getNamSinh());
                    builder.setView(view);
                    builder.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            boolean check = thanhVienDAO.suaThanhVien(thanhVien1,edtTen.getText().toString(),edtNamSinh.getText().toString());
                            if (check) {
                                list.clear();
                                list=thanhVienDAO.getDSThanhVien();
                                notifyDataSetChanged();
                                Toast.makeText(context, "Sửa Thành Viên Thành Công!", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(context, "Sửa Thành Viên Thất Bại!", Toast.LENGTH_SHORT).show();
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
        TextView tvTenTV,tvMaTV,tvNamSinhTV;
        ImageView ivEditTV,ivDeleteTV;
        CardView cardThanhVien;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenTV=itemView.findViewById(R.id.tvTenTV);
            tvMaTV=itemView.findViewById(R.id.tvMaTV);
            tvNamSinhTV=itemView.findViewById(R.id.tvNamSinhTV);
            ivEditTV=itemView.findViewById(R.id.ivEditTV);
            ivDeleteTV=itemView.findViewById(R.id.ivDeleteTV);
            cardThanhVien=itemView.findViewById(R.id.cardThanhVien);
        }
    }

}
