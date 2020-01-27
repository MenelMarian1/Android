package com.example.pierwszyandek;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pierwszyandek.Multimedia.MultimediaActivity;
import com.example.pierwszyandek.contacts.ContactsActivity;
import com.example.pierwszyandek.reminder.MemoryActivity;
import com.example.pierwszyandek.reminder.ReminderScheduler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.createNotificationChannel(new NotificationChannel("reminder", "reminder", NotificationManager.IMPORTANCE_HIGH));

        ReminderScheduler.schedule(this);

    }
    public void numerek1(View view){
        startActivity(new Intent(MainActivity.this, NoteActivity.class));
    }
    public void numerek2(View view){
        startActivity(new Intent(MainActivity.this, MemoryActivity.class));
    }
    public void numerek3(View view){
        startActivity((new Intent(MainActivity.this, ContactsActivity.class)));
    }
    public void numerek4(View view){
        startActivity((new Intent(MainActivity.this, MultimediaActivity.class)));
    }
}
