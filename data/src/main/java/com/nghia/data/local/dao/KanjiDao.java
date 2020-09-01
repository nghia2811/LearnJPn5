package com.nghia.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import com.nghia.data.local.entity.KanjiEntity;

@Dao
public interface KanjiDao {

    @Query("SELECT * FROM kanji")
    Single<List<KanjiEntity>> getKanjis();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertKanjis(List<KanjiEntity> entities);
}
