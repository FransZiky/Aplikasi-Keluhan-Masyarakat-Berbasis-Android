package com.example.lapcov_19.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lapcov_19.R;
import com.example.lapcov_19.entity.Account;
import com.example.lapcov_19.ui.InformasiDataPenduduk;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PendudukAdapter  extends RecyclerView.Adapter<PendudukAdapter.PendudukHolder> {
    private final LayoutInflater mInflater;
    private List<Account> mAccount;

    public PendudukAdapter(Context context, List<Account> mAccount){
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PendudukHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recycleview_penduduk, parent, false);
        return new PendudukHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PendudukHolder holder, int position) {
        if (mAccount!=null){
            Account account = mAccount.get(position);
            holder.txt_peofil.setText(account.getNama());
            Picasso.get()
                    .load("http://192.168.43.202:/WebServices/Web_Services_Lapcovid19/public/FotoProfil/"+account.getFoto())
                    .into(holder.img_profil);
        }else{
            holder.txt_peofil.setText("Tidak ada penduduk terdaftar");
        }
    }

    @Override
    public int getItemCount() {
        if (mAccount!=null)
            return mAccount.size();
        else
            return 0;
    }


    public void setAccount(List<Account> account){
        mAccount = account;
        notifyDataSetChanged();
    }

    class PendudukHolder extends RecyclerView.ViewHolder{
        private final TextView txt_peofil;
        private final ImageView img_profil;
        private LinearLayout itemList;
        private Context context;

        private PendudukHolder(View itemView){
                super(itemView);

                txt_peofil = itemView.findViewById(R.id.txt_profil);
                img_profil = itemView.findViewById(R.id.pProfil);
                context = itemView.getContext();
                itemList = itemView.findViewById(R.id.penduduk_list);

                itemList.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                            for (int i=0; i<mAccount.size();i++){
                                if (getAdapterPosition()== i){
                                    intent = new Intent(context, InformasiDataPenduduk.class);
                                    intent.putExtra("username",mAccount.get(i).getUsername());
                                    intent.putExtra("nama", mAccount.get(i).getNama());
                                    intent.putExtra("jk", mAccount.get(i).getJk());
                                    intent.putExtra("tanggalLahir", mAccount.get(i).getTanggalLahir());
                                    intent.putExtra("alamat", mAccount.get(i).getAlamat());
                                    intent.putExtra("nik", mAccount.get(i).getNik());
                                    intent.putExtra("foto", mAccount.get(i).getFoto());
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                }
                            }
                            context.startActivity(intent);
                    }
                });
        }
    }
}
