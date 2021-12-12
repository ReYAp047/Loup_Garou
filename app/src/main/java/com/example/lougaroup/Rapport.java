package com.example.lougaroup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class Rapport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rapport);

        try
        {
            Objects.requireNonNull(this.getSupportActionBar()).hide();
        }
        catch (NullPointerException ignored){}

try {

        Bundle b = getIntent().getExtras();
        @SuppressWarnings("unchecked")
        ArrayList<Roles> allRoles  = (ArrayList<Roles>)b.getSerializable("AllRoles");
        String vue = (String) b.getString("Vue");
        @SuppressWarnings("unchecked")
        ArrayList<Roles> killedNow = (ArrayList<Roles>) b.getSerializable("KilledNow");
        String annoncer = (String) b.getString("Annoncer");

        TextView deadOne = findViewById(R.id.deadOne);
        TextView deadTow = findViewById(R.id.deadTow);
        TextView vuee = findViewById(R.id.vue);
        TextView showedd = findViewById(R.id.showed);

        if(killedNow.size()>0){
            deadOne.setText((CharSequence) killedNow.get(0));
            if(killedNow.size()==2){
                deadTow.setText((CharSequence) killedNow.get(1));
            }else{
                deadTow.setText("");
            }

        }else{
            deadOne.setText(R.string.noDeath);
            deadTow.setText("");
        }

        vuee.setText(vue);

        showedd.setText(annoncer);


        ImageView next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Partie Vote !!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Rapport.this, Vote.class);
                Bundle bb = new Bundle();
                bb.putSerializable("AllRoles", (Serializable) allRoles);
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