package com.example.pierwszyandek.reminder;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ReminderDao {
    @Query("SELECT * FROM reminder")
    LiveData<List<Reminder>> getAll();

    @Query("SELECT * FROM reminder WHERE id IN (:reminderids)")
    LiveData<List<Reminder>> loadAllByIds(int[] reminderids);

    @Query("SELECT * FROM reminder WHERE id = :id")
    Reminder findById (int id);

    @Insert
    void insertAll (Reminder... rmeinders);

    @Delete
    void delete (Reminder reminder);

    @Update
    void update (Reminder reminder);

    @Query("SELECT * FROM reminder WHERE time BETWEEN (:date - 1000) AND (:date + 1000)")
    List<Reminder> findByDateAndTime(long date);
}
