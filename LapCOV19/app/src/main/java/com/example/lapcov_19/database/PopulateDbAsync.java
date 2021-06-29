package com.example.lapcov_19.database;

import android.os.AsyncTask;

import com.example.lapcov_19.entity.ECekKesehatan;

public class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
    CekKesehatanDao mDao;

    PopulateDbAsync(DBRoom db ){
        mDao = db.cDao();
    }

//    String gejala, String aktivitas, String username, String hasil
    int[] id = {99999};
    String[] aktivias = {"1"};
    String[] gejala = {"0"};
    String[] username = {"ako1"};
    String[] hasil = {"0"};
    @Override
    protected Void doInBackground(Void... voids) {
        mDao.deleteAll();
        for (int i = 0; i <=id.length-1; i++) {
            ECekKesehatan cek = new ECekKesehatan(id[i], gejala[i], aktivias[i], username[i], hasil[i]);
            mDao.insertKesehatan(cek);
        }
        return null;
    }

}
