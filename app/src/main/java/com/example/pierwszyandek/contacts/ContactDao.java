package com.example.pierwszyandek.contacts;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import static android.icu.text.MessagePattern.ArgType.SELECT;

@Dao
public interface ContactDao {
    @Query("SELECT * FROM contact")
    LiveData<List<Contact>> getAll();

    @Query("SELECT * FROM contact WHERE id IN (:ids)")
    LiveData<List<Contact>> loadAllByIds (int[] ids);

    @Query("SELECT * FROm contact WHERE id = :id")
    Contact findById (int id);

    @Insert
    void insertAll (Contact... contacts);

    @Delete
    void delete (Contact contact);

    @Update
    void update (Contact contact);
}
