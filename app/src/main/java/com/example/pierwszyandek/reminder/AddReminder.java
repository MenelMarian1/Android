package com.example.pierwszyandek.reminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.pierwszyandek.R;

import java.util.Calendar;
import java.util.concurrent.Executors;

public class AddReminder extends AppCompatActivity {

    private ReminderViewModel reminderViewModel;

    private Reminder current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);

        TextView reminderName = findViewById(R.id.reminderName);
        TextView reminderNote = findViewById(R.id.reminderInfo);
        TimePicker reminderTime = findViewById(R.id.timePicker);
        DatePicker reminderDate = findViewById(R.id.simpleDatePicker);

        reminderViewModel = ViewModelProviders
                .of(this)
                .get(ReminderViewModel.class);

        if (getIntent().hasExtra("editing")) {
            ReminderDao dao = AppDatabase.get(this).reminderDao();
            int id = getIntent().getIntExtra("editing", -1);
            Calendar cur = Calendar.getInstance();

            // Hack, bo baza danych nie lubi być na głównym wątku
            Executors.newSingleThreadExecutor().execute(() -> {
                current = dao.findById(id);
                cur.setTimeInMillis(current.getTime());
                reminderName.setText(current.getReminderName());
                reminderNote.setText(current.getReminderInfo());
                reminderDate.updateDate(cur.get(Calendar.YEAR),
                        cur.get(Calendar.MONTH),
                        cur.get(Calendar.DAY_OF_MONTH));

                reminderTime.setHour(cur.get(Calendar.HOUR_OF_DAY));
                reminderTime.setMinute(cur.get(Calendar.MINUTE));
            });
        }
    }

    public void onSaveButtonClick(View view) {
        TextView reminderName = findViewById(R.id.reminderName);
        TextView reminderInfo = findViewById(R.id.reminderInfo);
        TimePicker reminderTime = findViewById(R.id.timePicker);
        DatePicker reminderDate = findViewById(R.id.simpleDatePicker);

        String name = reminderName.getText().toString();
        String info = reminderInfo.getText().toString();

        Calendar calendar = Calendar.getInstance();
        calendar.set(reminderDate.getYear(),
                reminderDate.getMonth(),
                reminderDate.getDayOfMonth(),
                reminderTime.getHour(),
                reminderTime.getMinute(),
                0);

        Reminder reminder = new Reminder(name, info, calendar.getTimeInMillis());

        if (current != null) {
            reminder.setId(current.getId());
            reminderViewModel.update(reminder);
        } else {
            reminderViewModel.saveReminder(reminder);
        }
        finish();
    }
}
