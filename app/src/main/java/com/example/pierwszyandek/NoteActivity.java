package com.example.pierwszyandek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
    }
    public void onAddNote(View view){
        startActivity(new Intent(NoteActivity.this, AddingNote.class));
    }
        public void viewNotes(View view){
        startActivity(new Intent(getApplicationContext(), ViewNotes.class));
    }
}
