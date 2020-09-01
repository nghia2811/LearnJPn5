package com.nghia.data.local.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.nghia.data.local.dao.GrammarDao;
import com.nghia.data.local.dao.KanjiDao;
import com.nghia.data.local.dao.WordDao;
import com.nghia.data.local.entity.GrammarEntity;
import com.nghia.data.local.entity.KanjiEntity;
import com.nghia.data.local.entity.WordEntity;

@androidx.room.Database(entities = {WordEntity.class, GrammarEntity.class, KanjiEntity.class}, version = 1, exportSchema = false)
public abstract class AppRoomDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "japandb";

    private static AppRoomDatabase INSTANCE;

    public static AppRoomDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppRoomDatabase.class, DATABASE_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract WordDao wordDao();

    public abstract GrammarDao grammarDao();

    public abstract KanjiDao kanjiDao();

}
