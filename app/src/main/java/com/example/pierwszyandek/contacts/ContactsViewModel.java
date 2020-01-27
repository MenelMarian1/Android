package com.example.pierwszyandek.contacts;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pierwszyandek.reminder.AppDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ContactsViewModel extends AndroidViewModel {
    private final ContactDao contactDao;
    private final ExecutorService executorService;


    public ContactsViewModel(@NonNull Application application) {
        super(application);

        contactDao = AppDatabase.get(application.getApplicationContext()).contactDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Contact>> getAllContacts() {
        return contactDao.getAll();
    }

    void saveContact(Contact contact) {
        executorService.execute(() -> {
            contactDao.insertAll(contact);
            Log.d("CVM", "saveContact: " + contact);
        });
    }

    void deleteContact(Contact contact) {
        executorService.execute(() -> contactDao.delete(contact));
    }

    public void update(Contact contact) {
        executorService.execute(() -> contactDao.update(contact));
    }
}
