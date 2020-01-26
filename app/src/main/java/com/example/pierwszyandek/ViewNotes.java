package com.example.pierwszyandek;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class ViewNotes extends AppCompatActivity {

    private ListView list;
    Intent myIntent;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notes);
        myIntent = new Intent (this, AddingNote.class);
        String[] elements = fileList();
        list = (ListView) findViewById(R.id.viewer);
        ArrayList<String> listArray = new ArrayList<String>(Arrays.asList(elements));
        adapter = new ArrayAdapter<String>(this, R.layout.mail_list_elements, listArray);
        list.setAdapter(adapter);
        list.setOnItemClickListener(listClick);
    }
    private AdapterView.OnItemClickListener listClick = new AdapterView.OnItemClickListener () {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            String itemValue = (String) list.getItemAtPosition( position );

            Log.d("ViewNotes", "onItemClick: "+itemValue);
            
            myIntent.putExtra("note_selected", itemValue);
            startActivity(myIntent);
        }

    };
    public void onAddNote(View view){
        startActivity(new Intent(ViewNotes.this, AddingNote.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==123) {
            adapter.notifyDataSetChanged();
        }
    }
}
