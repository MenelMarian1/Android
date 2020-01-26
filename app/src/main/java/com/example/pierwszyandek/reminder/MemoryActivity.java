package com.example.pierwszyandek.reminder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pierwszyandek.R;

public class MemoryActivity extends AppCompatActivity {

    ReminderViewModel reminderViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);

        reminderViewModel = ViewModelProviders.of(this)
                .get(ReminderViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.reminderRecyclerView);

        ReminderAdapter adapter = new ReminderAdapter(reminderViewModel);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        reminderViewModel.getAllReminders().observe(this, adapter::setReminders);


    }

    public void addReminder(View view){
        startActivity(new Intent(MemoryActivity.this, AddReminder.class));
    }
}
