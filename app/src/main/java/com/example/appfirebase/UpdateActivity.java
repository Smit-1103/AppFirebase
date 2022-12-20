package com.example.appfirebase;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UpdateActivity extends AppCompatActivity {
    Spinner spin;
    Button btUpdateUpd,btUpdateCan;
    EditText etPass,etName,etMobile,etAge;
    DatabaseReference dbRef,dbUpd;
    List<Student> liststd = new ArrayList<Student>();
    List<String> listmail = new ArrayList<String>();
    String sid="",mail="",pass="";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        getSupportActionBar().hide();


        spin = findViewById(R.id.spUserUpdMail);
        btUpdateCan = findViewById(R.id.btUpdateCan);
        btUpdateUpd = findViewById(R.id.btUpdateUpd);

        etPass = findViewById(R.id.etUpdatePassword);
        etName = findViewById(R.id.etUpdateName);
        etMobile = findViewById(R.id.etUpdateMobile);
        etAge = findViewById(R.id.etUpdateAge);

        dbRef = FirebaseDatabase.getInstance().getReference("student");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                listmail.clear();
                liststd.clear();
                for(DataSnapshot shot : snapshot.getChildren())
                {
                    Student ss = shot.getValue(Student.class);
                    liststd.add(ss);
                    listmail.add(ss.getMail());

                }
                ArrayAdapter ad = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,listmail);
                spin.setAdapter(ad);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //i --> apdne index apse k kayu select karyu ex... 5th kariye to i=4 madse

                Student ss = liststd.get(i);

                mail = ss.getMail();
                sid = ss.getuId();
                pass = ss.getPass();

                etPass.setText(ss.getPass());
                etName.setText(ss.getName());
                etMobile.setText(ss.getMobile());
                etAge.setText(""+ss.getAge());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btUpdateUpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name= etName.getText().toString();
                String mobile = etMobile.getText().toString();
                int age = Integer.parseInt(etAge.getText().toString());

                dbUpd = FirebaseDatabase.getInstance().getReference("student");

                dbUpd.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (DataSnapshot shot : snapshot.getChildren()){
                            Student ss = shot.getValue(Student.class);

                            if(mail.equals(ss.getMail())){
                                dbUpd = FirebaseDatabase.getInstance().getReference("student").child(sid);
                                Student s1 = new Student(sid,mail,pass,name,mobile,age);
                                dbUpd.setValue(s1);
                                break;
                            }
                        }
                        etAge.setText("");
                        etPass.setText("");
                        etName.setText("");
                        etMobile.setText("");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



            }
        });


    }
}