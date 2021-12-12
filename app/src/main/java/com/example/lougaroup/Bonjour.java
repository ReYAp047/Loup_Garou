package com.example.lougaroup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Bonjour extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonjour);

            try
            {
                Objects.requireNonNull(this.getSupportActionBar()).hide();
            }
            catch (NullPointerException ignored){}


        Bundle b = getIntent().getExtras();
        @SuppressWarnings("unchecked")
        ArrayList<Roles> allRoles  = (ArrayList<Roles>)b.getSerializable("AllRoles");
        String vue = (String) b.getString("Vue");
        @SuppressWarnings("unchecked")
        ArrayList<Roles> killedNow = (ArrayList<Roles>) b.getSerializable("KilledNow");
        String annoncer = (String) b.getString("Annoncer");

        ImageView next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"BONJOUR !!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Bonjour.this, Rapport.class);
                Bundle bb = new Bundle();
                bb.putString("Vue", vue);
                bb.putSerializable("AllRoles", (Serializable) allRoles);
                bb.putSerializable("KilledNow", killedNow);
                bb.putString("Annoncer",annoncer);
                intent = intent.putExtras(bb);
                startActivity(intent);
            }
        });


        }catch(Exception error1) {
            Toast.makeText(getApplicationContext(),error1.toString(),Toast.LENGTH_SHORT).show();
            System.out.println(error1.toString());
            error1.printStackTrace();
        }

    }

}