package com.example.appfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class DeleteActivity extends AppCompatActivity {
    Spinner spin;
    Button btDel,btCal;
    DatabaseReference dbRef,dbDel;
    List<String> maillist = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        spin = findViewById(R.id.spUserDelMail);
        btDel = findViewById(R.id.btUserDelDel);
        btCal = findViewById(R.id.btUserDelCan);


        dbRef = FirebaseDatabase.getInstance().getReference("student");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                maillist.clear(); // pela 5 hata record pachi bija 2 add karya to ave fari vaar ma 7 record j thava joye show ,,, baki juna 5 ane nava 7 banne total 5+7 print thay atle juna clear kari ne nava nakhva pade

                for (DataSnapshot shot : snapshot.getChildren()) // tya jetla child hase atli vaar farse and shot ma save thase badhu
                {
                    Student s1 = shot.getValue(Student.class);
                    maillist.add(s1.getMail()); //khali mail levu che atle mate
                }
                ArrayAdapter<String> ad = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,maillist);
                spin.setAdapter(ad);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dbDel = FirebaseDatabase.getInstance().getReference(); // dattabase no reference

        btDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mail = spin.getSelectedItem().toString();

                Query delQue = dbDel.child("student").orderByChild("mail").equalTo(mail); // mail thi arrange karva // database na mail ne soinner na mail jode check karavi ne delete karse

                delQue.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot shot : snapshot.getChildren()){
                            shot.getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

    }
}