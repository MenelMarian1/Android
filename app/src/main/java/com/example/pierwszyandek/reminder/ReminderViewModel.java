package com.example.pierwszyandek.reminder;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pierwszyandek.reminder.Reminder;
import com.example.pierwszyandek.reminder.ReminderDao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReminderViewModel extends AndroidViewModel {
    public ReminderViewModel(@NonNull Application application, ReminderDao reminderDao, ExecutorService executorService) {
        super(application);
        this.reminderDao = reminderDao;
        this.executorService = executorService;
    }

    private final ReminderDao reminderDao;
    private final ExecutorService executorService;

    public ReminderViewModel(@NonNull Application application) {
        super(application);

        reminderDao = AppDatabase.get(application.getApplicationContext()).reminderDao();
        executorService = Executors.newSingleThreadExecutor();

    }

    public LiveData<List<Reminder>> getAllReminders() {
        return reminderDao.getAll();
    }

    void saveReminder(Reminder reminder) {
        executorService.execute(() -> reminderDao.insertAll(reminder));
    }

    void deleteReminder(Reminder reminder) {
        executorService.execute(() -> reminderDao.delete(reminder));
    }

    public void update(Reminder reminder) {
        executorService.execute(() -> reminderDao.update(reminder));
    }
}