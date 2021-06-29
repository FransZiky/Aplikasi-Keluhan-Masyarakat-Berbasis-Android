package com.example.lapcov_19.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.lapcov_19.entity.ECekKesehatan;

import java.util.List;

public class LocalRepository {
    private CekKesehatanDao mCekDao;

    private LiveData<List<ECekKesehatan>>  mCekKesehatan;

    public LocalRepository(Application application){
        DBRoom db =DBRoom.getDatabase(application);
        mCekDao = db.cDao();
        mCekKesehatan = mCekDao.getCekKesehatan();
    }

    public LiveData<List<ECekKesehatan>> getCekKesehatan(){
        return mCekKesehatan;
    }

    public void insertCekKesehatan(ECekKesehatan ECekKesehatan){
            new insertAsyncTask(mCekDao).execute(ECekKesehatan);
    }

    public void deletCekKesehatan(){
        new deletAsyncTask(mCekDao).execute();
    }

    private static class deletAsyncTask extends AsyncTask<ECekKesehatan, Void, Void>{
        private CekKesehatanDao mAsyncTaskDao;

       deletAsyncTask(CekKesehatanDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final  ECekKesehatan... params){
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    private static class insertAsyncTask extends AsyncTask<ECekKesehatan, Void, Void>{
        private CekKesehatanDao mAsyncTaskDao;

            insertAsyncTask(CekKesehatanDao dao){
                mAsyncTaskDao = dao;
            }

            @Override
            protected Void doInBackground(final  ECekKesehatan... params){
                mAsyncTaskDao.insertKesehatan(params[0]);
                return null;
            }
    }


}
