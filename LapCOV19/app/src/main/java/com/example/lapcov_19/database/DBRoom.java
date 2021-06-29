package com.example.lapcov_19.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.lapcov_19.entity.ECekKesehatan;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {ECekKesehatan.class}, version = 2, exportSchema = false)
public abstract class DBRoom extends RoomDatabase {

    public abstract CekKesehatanDao cDao();

    private static volatile DBRoom INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    static DBRoom getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (DBRoom.class){
                if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        DBRoom.class,"lapcov19")
                        .fallbackToDestructiveMigration()
                        .addCallback(sRoomDatabaseCallback)
                        .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

}
