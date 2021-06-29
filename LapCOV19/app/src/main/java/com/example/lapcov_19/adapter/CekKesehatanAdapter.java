package com.example.lapcov_19.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lapcov_19.R;
import com.example.lapcov_19.entity.CekKesehatan;

import java.util.List;

public class CekKesehatanAdapter extends RecyclerView.Adapter<CekKesehatanAdapter.CekKesehatanHolder> {

        private final LayoutInflater mInflater;
//    private List<ECekKesehatan> mECekKesehatan;
//    Context context;
    private List<CekKesehatan> mCekKesehatan;


    public CekKesehatanAdapter(Context context, List<CekKesehatan> mCekKesehatan){
        mInflater = LayoutInflater.from(context);
//        this.context = context;
//        this.mCekKesehatan = mCekKesehatan;
    }

    @NonNull
    @Override
    public CekKesehatanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recycleview_cek_kesehatan, parent, false);
        return new CekKesehatanHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CekKesehatanHolder holder, int position) {
        if(mCekKesehatan != null){
                CekKesehatan current = mCekKesehatan.get(position);
                holder.cekKesehatanItemView.setText(current.getUsername());
                if(Integer.valueOf(current.getDaftarpertanyaan_aktivitas())==1){
                    holder.kategori.setText("Aktivitas");
                }else if(Integer.valueOf(current.getDaftarpertanyaan_gejala())==1){
                    holder.kategori.setText("Gejala");
                }
                if(current.getHasil()==0){
                    holder.linearLayout.setBackgroundColor(Color.rgb(46,167,177));
                    holder.hasil.setText("Rendah");
                }else if(current.getHasil()>=1 && current.getHasil()<=2){
                    holder.linearLayout.setBackgroundColor(Color.rgb(27,92,222));
                    holder.hasil.setText("Sedang");
                }else if(current.getHasil()>=3){
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
        if (mCekKesehatan !=null)
                return mCekKesehatan.size();
        else
            return  0;
    }


//    public void setCekKesehatan(List<ECekKesehatan> ECekKesehatans){
//        mECekKesehatan = ECekKesehatans;
//        notifyDataSetChanged();
//    }

    public void setCekKesehatan(List<CekKesehatan> CekKesehatans){
        mCekKesehatan = CekKesehatans;
        notifyDataSetChanged();
    }


    class CekKesehatanHolder extends RecyclerView.ViewHolder{
        private final TextView cekKesehatanItemView;
        private final LinearLayout linearLayout;
        private  final TextView hasil;
        private final TextView kategori;
        private CekKesehatanHolder(View itemView){
                super(itemView);
                cekKesehatanItemView = itemView.findViewById(R.id.cekKes);
                linearLayout = itemView.findViewById(R.id.cekkesehatan_list);
                hasil = itemView.findViewById(R.id.hasil_tes);
                kategori = itemView.findViewById(R.id.kategori1);
        }
    }
}
