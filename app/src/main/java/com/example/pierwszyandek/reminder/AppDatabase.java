package com.example.pierwszyandek.reminder;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Reminder.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ReminderDao reminderDao();

    private static AppDatabase INSTANCE = null;

    public static AppDatabase get(Context context) {
        AppDatabase tempInstance = INSTANCE;
        if (tempInstance != null) {
            return tempInstance;
        }
        AppDatabase instance = Room.databaseBuilder(
                context.getApplicationContext(),
                AppDatabase.class,
                "word_database"
        ).build();
        INSTANCE = instance;
        return instance;
    }
}
