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
import com.example.lapcov_19.entity.EInformasi;
import com.example.lapcov_19.ui.Detailkeluhan;
import com.example.lapcov_19.ui.detail_informasi;

import java.util.List;

public class InformasiAdapter extends RecyclerView.Adapter<InformasiAdapter.InformasiHolder> {

    private final LayoutInflater mInflater;

    private List<EInformasi> mInformasi;

    public InformasiAdapter(Context context, List<EInformasi> mInformasi){
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public InformasiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recycleview_informasi, parent, false);
        return new InformasiHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InformasiHolder holder, int position) {
       if (mInformasi!=null){
           EInformasi current = mInformasi.get(position);
           holder.judul.setText(current.getJudul());
           holder.tanggal.setText(String.valueOf(current.getTanggal()));
           System.out.println("as "+current.getKategori());
           holder.kategori.setText(current.getKategori());
       }else{
           holder.judul.setText("Tidak ada informasi");
       }
    }

    @Override
    public int getItemCount() {
        if (mInformasi!=null)
            return mInformasi.size();
        else
            return 0;
    }

    public void setInformasi(List<EInformasi> Informasi){
        mInformasi = Informasi;
        notifyDataSetChanged();
    }

    class InformasiHolder extends RecyclerView.ViewHolder{
        private final TextView judul;
        private final TextView kategori;
        private final TextView tanggal;
        private LinearLayout itemList;
        private Context context;
        SharedPreferences sharedpreferences;

        private InformasiHolder(View itemView){
            super(itemView);
            judul = itemView.findViewById(R.id.judul);
            kategori = itemView.findViewById(R.id.kategori);
            tanggal = itemView.findViewById(R.id.tanggal);
            context = itemView.getContext();
            itemList = itemView.findViewById(R.id.informasi_list);
            itemList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                         for(int i=0;i<mInformasi.size();i++){
                             if (getAdapterPosition()==i){
                                 intent = new Intent(context, detail_informasi.class);
                                 intent.putExtra("isi", mInformasi.get(i).getIsi());
                                 intent.putExtra("tanggal", mInformasi.get(i).getTanggal());
                                 intent.putExtra("kategori", mInformasi.get(i).getKategori());
                                 intent.putExtra("judul", mInformasi.get(i).getJudul());
                                 intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                             }
                         }
                    context.startActivity(intent);
                }
            });
        }
    }
}
