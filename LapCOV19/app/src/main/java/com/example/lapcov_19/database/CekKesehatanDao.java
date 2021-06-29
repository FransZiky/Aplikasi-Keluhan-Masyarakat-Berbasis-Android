package com.example.lapcov_19.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.lapcov_19.entity.ECekKesehatan;

import java.util.List;

@Dao
public interface CekKesehatanDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertKesehatan(ECekKesehatan cekkesehatan);

    @Query("SELECT * FROM CekKesehatan")
    LiveData<List<ECekKesehatan>> getCekKesehatan();

    @Query("DELETE FROM CekKesehatan")
    void deleteAll();
}
