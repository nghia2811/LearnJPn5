package com.nghia.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import com.nghia.data.local.entity.WordEntity;

@Dao
public interface WordDao {

    @Query("SELECT * FROM words")
    Single<List<WordEntity>> getWords();

    @Query("SELECT * FROM words WHERE isFavorite =1")
    Flowable<List<WordEntity>> getFavoriteWords();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertWords(List<WordEntity> wordEntities);

    @Update
    Completable updateFavoriteWord(WordEntity wordEntity);
//    @Query("UPDATE words SET isFavorite = :isFavorite WHERE id = :id")
//    Completable updateFavoriteWord(int id, boolean isFavorite);
}
