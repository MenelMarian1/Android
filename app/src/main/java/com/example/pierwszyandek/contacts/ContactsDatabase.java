package com.example.pierwszyandek.contacts;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Contact.class}, version = 1, exportSchema = false)
public abstract class ContactsDatabase extends RoomDatabase {
    public abstract ContactDao contactDao();

    private static ContactsDatabase INSTANCE = null;

    public static ContactsDatabase get(Context context) {
        ContactsDatabase tempInstance = INSTANCE;
        if (tempInstance != null) {
            return tempInstance;
        }
        ContactsDatabase instance = Room.databaseBuilder(
                context.getApplicationContext(),
                ContactsDatabase.class,
                "word_database"
        ).build();
        INSTANCE = instance;
        return instance;
    }
}
