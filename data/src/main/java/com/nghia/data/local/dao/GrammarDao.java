package com.nghia.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import com.nghia.data.local.entity.GrammarEntity;

@Dao
public interface GrammarDao {

    @Query("SELECT * FROM grammar")
    Single<List<GrammarEntity>> getGrammar();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertGrammars(List<GrammarEntity> entities);
}
