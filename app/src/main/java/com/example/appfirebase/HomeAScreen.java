package com.example.appfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;

public class HomeAScreen extends AppCompatActivity {
    Button btHomeAdd,btHomeDel,btHomeUpd,btHomeShow;
    LottieAnimationView logout;
    FirebaseAuth fAuth;
    public static String PREFS_NAME="MyPrefsFile"; //sharedPreferences

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_ascreen);
        getSupportActionBar().hide();

        btHomeAdd = findViewById(R.id.btHomeAdd);
        btHomeDel = findViewById(R.id.btHomeDelete);
        btHomeUpd = findViewById(R.id.btHomeUpdate);
        btHomeShow = findViewById(R.id.btHomeShow);

        logout = findViewById(R.id.logout);
        fAuth = FirebaseAuth.getInstance();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Dialog box
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeAScreen.this);
                builder.setMessage("Do you want to Logout ?");
                builder.setTitle("Logout !");

                builder.setCancelable(false);
                builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                    fAuth.signOut();
                    Intent ii = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(ii);
                    finish();
                });
                builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                    dialog.cancel();
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

        btHomeShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii = new Intent(getApplicationContext(), ShowActivity.class);
                startActivity(ii);
            }
        });
        btHomeDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii = new Intent(getApplicationContext(), DeleteActivity.class);
                startActivity(ii);
            }
        });
        btHomeUpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii = new Intent(getApplicationContext(), UpdateActivity.class);
                startActivity(ii);
            }
        });




    }
}