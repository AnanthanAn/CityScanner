package com.example.cityscanner;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private static final String SEARCH_KEY = "SEARCH_KEY";
    Button mapButton,userButton;
    EditText searchText;
    TextView textView;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapButton = findViewById(R.id.button);
        userButton = findViewById(R.id.userButton);
        searchText = findViewById(R.id.searchText);
        textView = findViewById(R.id.textView);
        progressBar = findViewById(R.id.progressBar);

        //Text view override prop
        textView.setTextSize(50);
        textView.setTextColor(Color.WHITE);

        mAuth = FirebaseAuth.getInstance();


        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,NearbyListActivity.class);

                String searchStrng = searchText.getText().toString();
                intent.putExtra(SEARCH_KEY,searchStrng);

                startActivity(intent);

            }
        });

        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,FirestoreEntryActivity.class);

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

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null){
            progressBar.setVisibility(View.GONE);

        }else{
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
        }
    }

    //Option menu part
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_signout) {

            Toast.makeText(getApplicationContext(),"Signed Out Successfully",Toast.LENGTH_SHORT).show();

            FirebaseAuth.getInstance().signOut();

            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);

            Log.i("Logggggggggggouttttttt", String.valueOf(FirebaseAuth.getInstance().getCurrentUser()));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
