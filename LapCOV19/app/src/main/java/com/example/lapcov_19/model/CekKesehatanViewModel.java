package com.example.lapcov_19.model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.lapcov_19.database.LocalRepository;
import com.example.lapcov_19.entity.ECekKesehatan;

import java.util.List;

public class CekKesehatanViewModel extends AndroidViewModel {

    private LocalRepository mRipository;

    private LiveData<List<ECekKesehatan>> mCekKesehatan;

    public CekKesehatanViewModel(Application application){
        super(application);
        mRipository = new LocalRepository(application);
        mCekKesehatan = mRipository.getCekKesehatan();
    }

    public LiveData<List<ECekKesehatan>> getCekKesehatan(){
        return mCekKesehatan;
    }

    public void insetCekKesehata(ECekKesehatan ECekKesehatan){
        mRipository.insertCekKesehatan(ECekKesehatan);
    }

    public void deletKelas(){
        mRipository.deletCekKesehatan();
    }
}
