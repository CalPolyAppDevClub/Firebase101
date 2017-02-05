package com.tinkersstudio.firebase101;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView condition;
    Button buttonHappy;
    Button buttonSad;

    //connect to the database with the reference to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference conditionRef = database.getReference("condition");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        condition = (TextView) findViewById(R.id.textView2);
        buttonHappy = (Button) findViewById(R.id.button_happy);
        buttonSad = (Button) findViewById(R.id.button_sad);

        conditionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                condition.setText(text);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        buttonHappy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conditionRef.setValue("Happy");
            }
        });

        buttonSad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conditionRef.setValue("Sad");
            }
        });
    }

}
