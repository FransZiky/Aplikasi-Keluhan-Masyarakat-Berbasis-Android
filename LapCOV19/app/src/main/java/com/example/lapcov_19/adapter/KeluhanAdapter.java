package com.example.lapcov_19.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lapcov_19.R;
import com.example.lapcov_19.entity.EKeluhan;
import com.example.lapcov_19.ui.Detailkeluhan;
import com.example.lapcov_19.ui.Editkeluhan;

import java.util.List;


public class KeluhanAdapter extends RecyclerView.Adapter<KeluhanAdapter.KeluhanHolder> {
    private final LayoutInflater mInflater;
    private List<EKeluhan> mKeluhan;

    public KeluhanAdapter(Context context, List<EKeluhan> mKeluhan){
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public KeluhanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recycleview_keluhan, parent, false);
        return new KeluhanHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull KeluhanHolder holder, int position) {
        if(mKeluhan!=null){
            EKeluhan keluhan = mKeluhan.get(position);
            holder.kategori.setText(keluhan.getKategori());
            holder.tanggal.setText(keluhan.getTanggal());
            holder.pengirim.setText(keluhan.getPengirim());
        }else{
            holder.kategori.setText("Tidak ada keluhan");
        }
    }

    @Override
    public int getItemCount() {
        if(mKeluhan!=null)
            return mKeluhan.size();
        else
            return 0;
    }

    public void setKeluhan(List<EKeluhan> eKeluhan){
        mKeluhan = eKeluhan;
        notifyDataSetChanged();
    }

    class KeluhanHolder extends RecyclerView.ViewHolder{
        private final TextView kategori;
        private final  TextView tanggal;
        private final TextView pengirim;
        private LinearLayout itemList;
        private Context context;
        SharedPreferences sharedpreferences;


        private KeluhanHolder(View itemView){
            super(itemView);
            kategori = itemView.findViewById(R.id.kategoriKeluhan);
            tanggal = itemView.findViewById(R.id.tanggalKeluhan);
            pengirim = itemView.findViewById(R.id.pengirimKeluhan);
            context = itemView.getContext();
            itemList = itemView.findViewById(R.id.keluhan_list);
            itemList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                        for(int i=0;i<mKeluhan.size();i++){
                            if (getAdapterPosition()==i){
                                intent = new Intent(context, Detailkeluhan.class);
                                intent.putExtra("kategori", mKeluhan.get(i).getKategori());
                                intent.putExtra("isi", mKeluhan.get(i).getIsi());
                                intent.putExtra("id_keluhan", mKeluhan.get(i).getId_keluhan());
                                intent.putExtra("status", mKeluhan.get(i).getStatus());
                                intent.putExtra("tanggal", mKeluhan.get(i).getTanggal());
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            }
                        }
                        context.startActivity(intent);
                }
            });
        }
    }
}
