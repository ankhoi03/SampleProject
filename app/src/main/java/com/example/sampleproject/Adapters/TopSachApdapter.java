package com.example.sampleproject.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sampleproject.Model.Sach;
import com.example.sampleproject.R;

import java.util.ArrayList;

public class TopSachApdapter extends RecyclerView.Adapter<TopSachApdapter.ViewHoler> {
    private Context context;
    private ArrayList<Sach>list;

    public TopSachApdapter(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=((Activity)context).getLayoutInflater();
        View view=inflater.inflate(R.layout.top_sach_view_item,parent,false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        holder.tvTenSach.setText(list.get(position).getTenSach());
        holder.tvSoLuongMuon.setText("Lượt mượn: "+list.get(position).getSoLuongDaMuon());
        holder.tvMaSach.setText("Mã sách: "+list.get(position).getMaSach());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder{
        TextView tvMaSach,tvTenSach,tvSoLuongMuon;
        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            tvMaSach=itemView.findViewById(R.id.tvMaSachTop);
            tvTenSach=itemView.findViewById(R.id.tvTenSachTop);
            tvSoLuongMuon=itemView.findViewById(R.id.tvSoLuongMuon);
        }
    }
}
