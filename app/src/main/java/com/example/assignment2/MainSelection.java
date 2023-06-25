package com.example.assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainSelection extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    public Spinner spinner_main;
    public int spinner_pointer;
    public String chosen_gesture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner_main = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.gesture_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_main.setAdapter(adapter);
        spinner_main.setOnItemSelectedListener(this);

        Button btn1 = (Button) findViewById(R.id.button_next_page);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int2 = new Intent(getApplicationContext(), ViewGesture.class);
                int2.putExtra("gestures",chosen_gesture);
                startActivity(int2);

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        spinner_pointer = i;
        chosen_gesture = (String) adapterView.getSelectedItem();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}