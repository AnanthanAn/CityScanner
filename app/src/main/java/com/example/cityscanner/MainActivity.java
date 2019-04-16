package com.example.cityscanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button mapButton,userButton;
    EditText searchText;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapButton = findViewById(R.id.button);
        userButton = findViewById(R.id.userButton);
        searchText = findViewById(R.id.searchText);
        textView = findViewById(R.id.textView);

        textView.setTextSize(50);




        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MapsActivity.class);

                String searchStrng = searchText.getText().toString();
                intent.putExtra("searchTxt",searchStrng);

                startActivity(intent);

            }
        });

        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,UserActivity.class);

                startActivity(intent);

            }
        });


        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,MapsActivity.class);
                intent.putExtra("ID",1);
                startActivity(intent);

            }
        });



    }
}
