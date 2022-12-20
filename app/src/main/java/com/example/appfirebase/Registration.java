package com.example.appfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.backup.FileBackupHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileReader;

public class Registration extends AppCompatActivity {
    EditText etName,etPass,etMobile,etAge,etMail;
    Button btReg,btCan;
    ImageButton back;
    FirebaseAuth fAuth; //firebase jode connect thava mate
    DatabaseReference dbRef=null; //to connect to RealTimeDatabase

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().hide();

        etMail = findViewById(R.id.etMail);
        etPass = findViewById(R.id.etPass);
        etName = findViewById(R.id.etName);
        etMobile = findViewById(R.id.etMobile);
        etAge = findViewById(R.id.etAge);

        btCan = findViewById(R.id.btCancel);
        btReg = findViewById(R.id.btRegister);
        back = findViewById(R.id.btback);

        fAuth = FirebaseAuth.getInstance(); //use to connect to authentication part in current firebase project
        dbRef = FirebaseDatabase.getInstance().getReference("student"); // reference mate Student no s small avse firebase ma node small ma j aveme
        // it will create new node name "student" in realtimedatabase


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = etMail.getText().toString();
                String pass = etPass.getText().toString();
                String name = etName.getText().toString();
                String mobile = etMobile.getText().toString();
                int age = Integer.parseInt(etAge.getText().toString());


                if(mail.isEmpty() || pass.isEmpty() || name.isEmpty() || mobile.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Fill the values",Toast.LENGTH_SHORT).show();
                }
                else{

                    String uId = dbRef.push().getKey();  //to generate unique key for every student

                    Student st = new Student(uId,mail,pass,name,mobile,age);

                    dbRef.child(uId).setValue(st); //to insert new student record under newly created unique id under student node

                    //create new user
                    fAuth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Student Register Successfully!!",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Student Register Failed.",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    etMail.setText("");
                    etAge.setText("");
                    etPass.setText("");
                    etMobile.setText("");
                    etName.setText("");

                }



            }
        });

        btCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                etMail.setText("");
                etAge.setText("");
                etPass.setText("");
                etMobile.setText("");
                etName.setText("");
                Toast.makeText(getApplicationContext(),"Cleared successfully",Toast.LENGTH_SHORT).show();

            }
        });

    }
}