package com.example.appfirebase;


import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends Activity {
    ListView lv;
    DatabaseReference dbRef=null;
    List<Student> list = new ArrayList<Student>(); //pojo student mathi record leva mate

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        lv = findViewById(R.id.lv);

        dbRef = FirebaseDatabase.getInstance().getReference("student"); // realtime ma table jode connect karyu

        //database mathi badha record ahiya lava mate -- dbRef.addValueEventListener(new Va...)
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear(); // pela 5 hata record pachi bija 2 add karya to ave fari vaar ma 7 record j thava joye show ,,, baki juna 5 ane nava 7 banne total 5+7 print thay atle juna clear kari ne nava nakhva pade

                for (DataSnapshot shot : snapshot.getChildren()) // tya jetla child hase atli vaar farse and shot ma save thase badhu
                {
                    Student s1 = shot.getValue(Student.class);
                    list.add(s1);
                }
                ArrayAdapter<Student> ad = new ArrayAdapter<Student>(getApplicationContext(), android.R.layout.simple_list_item_1,list);
                lv.setAdapter(ad);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}