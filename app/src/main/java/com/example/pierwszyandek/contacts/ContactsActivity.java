package com.example.pierwszyandek.contacts;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pierwszyandek.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ContactsActivity extends AppCompatActivity {

    ContactsViewModel contactsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        FloatingActionButton button = findViewById(R.id.button);
        button.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), AddContact.class)));

        contactsViewModel = ViewModelProviders.of(this).get(ContactsViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.contactsRecyclerView);

        ContactAdapter adapter = new ContactAdapter(contactsViewModel);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        contactsViewModel.getAllContacts().observe(this, adapter::setContacts);

    }
}
