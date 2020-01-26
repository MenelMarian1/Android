package com.example.pierwszyandek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public class AddingNote extends AppCompatActivity {
    private static final String TAG = "NewNote";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_note);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if(extras != null && !extras.isEmpty()){
            String noteTitle = extras.getString("note_selected");

            Log.d(TAG, "onCreate: "+noteTitle);

            TextView titleView = findViewById(R.id.editText);
            TextView contentView = findViewById(R.id.editText2);
            File file = new File(getFilesDir(), noteTitle);
            String title = file.getName().replaceAll("\\.txt$", "");
            String content = "";
            try {
                content = String.join("\n", Files.readAllLines(file.toPath()));
            } catch (IOException e) {
                Log.e("NoteModel", "Error reading note", e);
                e.printStackTrace();
            }

            titleView.setText(title);
            contentView.setText(content);


        }
    }
    public void addNote(View view){
        TextView title= findViewById(R.id.editText);
        TextView text= findViewById(R.id.editText2);
        CharSequence title2 = title.getText();
        CharSequence text2 = text.getText();
        try(FileOutputStream out = openFileOutput(title2+".txt",Context.MODE_PRIVATE)) {
            out.write(text2.toString().getBytes());
            Log.d(TAG, "addNote: " + title2.toString());
            Toast.makeText(this, "added", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Log.e(TAG,"aaaaa" ,e);
        }
        finish();
    }
    public void deleteNote(View view){
        TextView title = findViewById(R.id.editText);

        String title2 =  title.getText().toString();
        Log.d(TAG, "deleteNote: "+title2);
        File filesDir = getFilesDir();

        File file = new File(filesDir, title2+".txt");
        Log.d(TAG, "deleteNote: "+file.getPath());
        if (file.delete()) {
            Toast.makeText(this, "deleted", Toast.LENGTH_LONG).show();
        }
        finishActivity(123);
        finish();
    }
}
