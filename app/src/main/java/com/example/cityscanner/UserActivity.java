package com.example.cityscanner;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class UserActivity extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextPassword, editTextPhone;
    private ProgressBar progressBar;
    private Button registerButton;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        editTextName = findViewById(R.id.edit_text_name);
        editTextEmail = findViewById(R.id.edit_text_email);
        editTextPassword = findViewById(R.id.edit_text_password);
        editTextPhone = findViewById(R.id.edit_text_phone);
        progressBar = findViewById(R.id.progressbar);
        registerButton = (Button)findViewById(R.id.button_register);
        progressBar.setVisibility(View.GONE);

        FirebaseApp.initializeApp(this);

        mAuth = FirebaseAuth.getInstance();


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        registerUser();

            }
        });
    }

    private void registerUser() {
        final String name = editTextName.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        final String phone = editTextPhone.getText().toString().trim();

        if (name.isEmpty()) {
            editTextName.setError(getString(R.string.input_error_name));
            editTextName.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextEmail.setError(getString(R.string.input_error_email));
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError(getString(R.string.input_error_email_invalid));
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError(getString(R.string.input_error_password));
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError(getString(R.string.input_error_password_length));
            editTextPassword.requestFocus();
            return;
        }

        if (phone.isEmpty()) {
            editTextPhone.setError(getString(R.string.input_error_phone));
            editTextPhone.requestFocus();
            return;
        }

        if (phone.length() != 10) {
            editTextPhone.setError(getString(R.string.input_error_phone_invalid));
            editTextPhone.requestFocus();
            return;
        }


        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            User user = new User(
                                    name,
                                    email,
                                    phone
                            );

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        Toast.makeText(UserActivity.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
                                    } else {
                                        //display a failure message
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(UserActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

//        User user = new User(
//                name,
//                email,
//                phone
//        );
//
//                FirebaseDatabase.getInstance().getReference("Users")
//                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        progressBar.setVisibility(View.GONE);
//                        if (task.isSuccessful()) {
//                            Toast.makeText(UserActivity.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
//                        } else {
//                            //display a failure message
//                        }
//                    }
//                });

    }


}