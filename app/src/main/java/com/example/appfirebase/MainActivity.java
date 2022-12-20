package com.example.appfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ConcurrentModificationException;

public class MainActivity extends AppCompatActivity {
    //for animation
    TextView tv;
    String text[] = {"Register"," OR ", "LogIn..."}; // for display the string
    int i = 0; // for count the letter
    int j=0;
    //array for colors
    String Color[]={"#29665C","#ffffff","#29665C"};

    EditText etEmail,etPass;
    Button btReg,btLog;
    ProgressBar progress;
    FirebaseAuth fAuth;  // to use firebase dependencies

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        tv = findViewById(R.id.tv);
        etEmail = findViewById(R.id.email);
        etPass = findViewById(R.id.password);
        btReg = findViewById(R.id.btReg);
        btLog = findViewById(R.id.login);
        progress = findViewById(R.id.progressBar);

        //shared preferance
        SharedPreferences sharedPreferences = getSharedPreferences(HomeAScreen.PREFS_NAME,0);
        boolean hasLoggedIn = sharedPreferences.getBoolean("hasLoggedIn",false);

        if(hasLoggedIn){
            Intent intent = new Intent(getApplicationContext(),HomeAScreen.class);
            startActivity(intent);
            finish();
        }
        else{
//            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//            startActivity(intent);
//            finish();
        }


        initialize(text[j]);   //for animation of the text

        progress.setVisibility(View.GONE);// invisible karva mate

        fAuth = FirebaseAuth.getInstance(); //use to connect to authentication part in current firebase project

        btReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent ii = new Intent(getApplicationContext(),Registration.class);
               startActivity(ii);
               /*
                String mail = etEmail.getText().toString();

                String pass = etPass.getText().toString();

                if(mail.isEmpty() || pass.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Fill the values",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    progress.setVisibility(View.VISIBLE);
                    //userid and pass thi navo user banayo and then record banyo che k nai ana mate nu confirmation mate a addonCpmlete add karvanu
                    fAuth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful())
                            {
                                Toast.makeText(getApplicationContext(),"user registered successfully",Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"user registration failed...",Toast.LENGTH_SHORT).show();
                            }

                            etEmail.setText("");
                            etPass.setText("");
                            progress.setVisibility(View.GONE);
                        }
                    });
                }*/
            }
        });

        btLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mail = etEmail.getText().toString();
                String pass = etPass.getText().toString();

                if(mail.isEmpty() || pass.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Fill the values",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //shared preferance
                    SharedPreferences shad = getSharedPreferences(HomeAScreen.PREFS_NAME,0);
                    SharedPreferences.Editor edit = shad.edit();
                    edit.putBoolean("hasLoggedIn",true);
                    edit.commit();
                    startActivity(new Intent(getApplicationContext(),HomeAScreen.class));
                    finish();

                    progress.setVisibility(View.VISIBLE);
                    fAuth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "user Login successfully", Toast.LENGTH_SHORT).show();
                                Intent ii = new Intent(getApplicationContext(), HomeAScreen.class);
                                startActivity(ii);
                            } else {
                                Toast.makeText(getApplicationContext(), "user Login failed...", Toast.LENGTH_SHORT).show();
                            }


                            etEmail.setText("");
                            etPass.setText("");
                            progress.setVisibility(View.GONE);
                        }
                    });
                }
            }
        });


    }

    //for the text animation ...
    private void initialize(String passed_text) {
    if(i<= passed_text.length()){
        String outputSting = "You can <font color='"+Color[j]+"'>"+passed_text.substring(0,i)+"</font>";
        tv.setText(Html.fromHtml(outputSting));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                i++;
                initialize(passed_text);
            }
        },190);
    }
    else {
        j++;
            if (j==text.length){
                i=0;
                j=0;
                initialize(text[j]);
            }
            else{
                i=0;
                initialize(text[j]);
            }
        }
    }




}

