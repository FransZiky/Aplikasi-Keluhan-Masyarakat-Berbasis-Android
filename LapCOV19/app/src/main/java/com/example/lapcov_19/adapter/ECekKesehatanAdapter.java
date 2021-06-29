package com.example.lapcov_19.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lapcov_19.R;
import com.example.lapcov_19.entity.ECekKesehatan;
import com.example.lapcov_19.ui.Login;

import java.util.List;

public class ECekKesehatanAdapter extends RecyclerView.Adapter<ECekKesehatanAdapter.ECekKesehatanHolder> {
    private final LayoutInflater mInflater;

    private List<ECekKesehatan> ECekKesehatan;
    SharedPreferences sharedPreferences;





    public ECekKesehatanAdapter(Context context){
        mInflater = LayoutInflater.from(context);
//        this.context = context;
//        this.mCekKesehatan = mCekKesehatan;
    }

    @NonNull
    @Override
    public ECekKesehatanAdapter.ECekKesehatanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recycleview_cek_kesehatan, parent, false);
        return new ECekKesehatanAdapter.ECekKesehatanHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ECekKesehatanAdapter.ECekKesehatanHolder holder, int position) {
        if(ECekKesehatan != null){
            ECekKesehatan current = ECekKesehatan.get(position);
            holder.cekKesehatanItemView.setText(current.getUsername());
            if(Integer.valueOf(current.getAktivitas())==1){
                holder.kategori.setText("Aktivitas");
            }else if(Integer.valueOf(current.getGejala())==1){
                holder.kategori.setText("Gejala");
            }

            if(Integer.parseInt(current.getHasil())==0){
                holder.linearLayout.setBackgroundColor(Color.rgb(46,167,177));
                holder.hasil.setText("Rendah");
            }else if(Integer.parseInt(current.getHasil())>=1 && Integer.parseInt(current.getHasil())<=2){
                holder.linearLayout.setBackgroundColor(Color.rgb(27,92,222));
                holder.hasil.setText("Sedang");
            }else if(Integer.parseInt(current.getHasil())>=3){
                holder.linearLayout.setBackgroundColor(Color.rgb(220,20,60));
                holder.hasil.setText("Tinggi");
            }
        }else
        {
            holder.cekKesehatanItemView.setText("No Cek");
        }
    }

    @Override
    public int getItemCount() {
        if (ECekKesehatan !=null)
            return ECekKesehatan.size();
        else
            return  0;
    }

    public void eSetCekKesehatan(List<ECekKesehatan> CekKesehatans){
        ECekKesehatan = CekKesehatans;
        notifyDataSetChanged();
    }


    class ECekKesehatanHolder extends RecyclerView.ViewHolder{
        private final TextView cekKesehatanItemView;
        private final LinearLayout linearLayout;
        private  final TextView hasil;
        private final TextView kategori;
        private ECekKesehatanHolder(View itemView){
            super(itemView);
            cekKesehatanItemView = itemView.findViewById(R.id.cekKes);
            linearLayout = itemView.findViewById(R.id.cekkesehatan_list);
            hasil = itemView.findViewById(R.id.hasil_tes);
            kategori = itemView.findViewById(R.id.kategori1);
        }
    }
}

