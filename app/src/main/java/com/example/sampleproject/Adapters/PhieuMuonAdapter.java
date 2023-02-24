package com.example.sampleproject.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleproject.DAO.PhieuMuonDAO;
import com.example.sampleproject.Model.PhieuMuon;
import com.example.sampleproject.R;

import java.util.ArrayList;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.PhieuMuonViewHolder>{
    PhieuMuonDAO phieuMuonDAO;
    Context context;
    ArrayList<PhieuMuon> list;
    public PhieuMuonAdapter(ArrayList<PhieuMuon> list, Context context) {
        this.list=list;
        this.context = context;
        phieuMuonDAO=new PhieuMuonDAO(context);
    }

    @NonNull
    @Override
    public PhieuMuonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= ((Activity)context).getLayoutInflater();
        View view=inflater.inflate(R.layout.phieumuon_view_item,parent,false);
        PhieuMuonViewHolder viewHolder=new PhieuMuonViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhieuMuonViewHolder holder, int position) {
            PhieuMuon pm=list.get(position);
            holder.tvTenThanhVien.setText(pm.getTenThanhVien());
            holder.tvMaThanhVien.setText("Mã Thành Viên: "+pm.getMaThanhVien());
            holder.tvMaPhieuMuon.setText("Mã Phiếu Mượn: "+pm.getMaPhieuMuon());
            holder.tvTenThuThu.setText("Thủ Thư: "+pm.getTenThuThu());
            holder.tvMaThuThu.setText("Mã Thủ Thư: "+pm.getMaThuThuPhieuMuon());
            holder.tvTenSach.setText("Tên Sách: "+pm.getTenSach());
            holder.tvMaSach.setText("Mã Sách: "+pm.getMaSachPhieuMuon());
            holder.tvNgay.setText("Ngày: "+pm.getNgay());
            holder.tvTien.setText("Tiền: "+pm.getTienThue()+"₫");
            String trangthai="";
            if(pm.getTraSach()==0){
                trangthai="Đã trả sách";
                holder.ivKhongTraSach.setVisibility(View.VISIBLE);
                holder.ivTraSach.setVisibility(View.INVISIBLE);
                holder.relativeLayout.setBackgroundResource(R.color.white);
                holder.ivDelete.setVisibility(View.VISIBLE);
            }else {
                trangthai="Chưa trả";
                holder.ivKhongTraSach.setVisibility(View.INVISIBLE);
                holder.ivTraSach.setVisibility(View.VISIBLE);
                holder.relativeLayout.setBackgroundResource(R.color.pink_men);
                holder.ivDelete.setVisibility(View.INVISIBLE);
            }
            holder.tvTrangThai.setText("Trạng thái: "+trangthai);

            holder.ivTraSach.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PhieuMuonDAO phieuMuonDAO=new PhieuMuonDAO(context);
                    boolean check=phieuMuonDAO.thayDoiTrangThai(list.get(holder.getAdapterPosition()).getMaPhieuMuon(),list.get(holder.getAdapterPosition()).getTraSach());
                    if(check){
                        list.clear();
                        list=phieuMuonDAO.getDSPhieuMuon();
                        notifyDataSetChanged();
                        Toast.makeText(context,"Trả sách thành công!",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context,"Thay đổi trạng thái thất bại!",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        holder.ivKhongTraSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhieuMuonDAO phieuMuonDAO=new PhieuMuonDAO(context);
                boolean check=phieuMuonDAO.thayDoiTrangThai(list.get(holder.getAdapterPosition()).getMaPhieuMuon(),list.get(holder.getAdapterPosition()).getTraSach());
                if(check){
                    list.clear();
                    list=phieuMuonDAO.getDSPhieuMuon();
                    notifyDataSetChanged();
                    Toast.makeText(context,"Thay đổi trạng thái thành công!",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context,"Thay đổi trạng thái thất bại!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhieuMuonDAO phieuMuonDAO=new PhieuMuonDAO(context);
                boolean check=phieuMuonDAO.deletePhieuMuon(list.get(holder.getAdapterPosition()).getMaPhieuMuon());
                if(check){
                    list.clear();
                    list=phieuMuonDAO.getDSPhieuMuon();
                    notifyDataSetChanged();
                    Toast.makeText(context,"Xóa thành công!",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context,"Xóa thất bại!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PhieuMuonViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenThanhVien,tvMaThanhVien, tvMaPhieuMuon, tvTenThuThu, tvMaThuThu,tvTenSach,tvMaSach,tvNgay,tvTien,tvTrangThai;
        ImageView  ivDelete,ivTraSach,ivKhongTraSach;
        RelativeLayout relativeLayout;
        CardView cvPhieuMuon;

        public PhieuMuonViewHolder(View view) {
            super(view);
            tvTenThanhVien = view.findViewById(R.id.tvTenThanhVien);
            tvMaThanhVien = view.findViewById(R.id.tvMaThanhVien);
            tvMaPhieuMuon = view.findViewById(R.id.tvMaPhieuMuon);
            tvTenThuThu = view.findViewById(R.id.tvTenThuThu);
            tvMaThuThu = view.findViewById(R.id.tvMaThuThu);
            tvTenSach = view.findViewById(R.id.tvTenSach);
            tvMaSach=view.findViewById(R.id.tvMaSach);
            tvNgay = view.findViewById(R.id.tvNgay);
            tvTien = view.findViewById(R.id.tvTien);
            tvTrangThai = view.findViewById(R.id.tvTrangThai);
            ivKhongTraSach=view.findViewById(R.id.ivKhongTraSach);
            ivTraSach = view.findViewById(R.id.ivTraSach);
            ivDelete = view.findViewById(R.id.ivDeletePM);
            cvPhieuMuon = view.findViewById(R.id.cardPhieuMuon);
            relativeLayout=view.findViewById(R.id.relativeCardview);

        }
    }
}
