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
import java.util.Objects;

public class Loup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loup);

        try
        {
            Objects.requireNonNull(this.getSupportActionBar()).hide();
        }
        catch (NullPointerException ignored){}

        Bundle bc = getIntent().getExtras();
        String jRole  = bc.getString("jRole");


        ImageView roleImage = findViewById(R.id.roleImage);
        switch(jRole) {
            case "Serber":
                roleImage.setBackgroundResource(R.drawable.grand);
                break;
            case "Pere":
                roleImage.setBackgroundResource(R.drawable.pere);
                break;
            default:
                roleImage.setBackgroundResource(R.drawable.loup);
                break;
        }
        TextView playerName = findViewById(R.id.playerName) ;
        playerName.setText(jRole);

        ImageView next = findViewById(R.id.pl);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b = getIntent().getExtras();
                @SuppressWarnings("unchecked")
                ArrayList<Roles> allRoles  = (ArrayList<Roles>)b.getSerializable("AllRoles");
                @SuppressWarnings("unchecked")
                ArrayList<Roles> giveRoles  = (ArrayList<Roles>) b.getSerializable("GiveRoles");

                Bundle bb = new Bundle();
                bb.putSerializable("AllRoles", (Serializable) allRoles);
                bb.putSerializable("GiveRoles", (Serializable) giveRoles);
                if(giveRoles.size()>0){
                    Toast.makeText(getApplicationContext(),"Passer le smartphoe vers le joueur suivant !",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Loup.this, NameType.class);
                    intent = intent.putExtras(bb);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Bonne Nuit !!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Loup.this, BonneNuit.class);
                    intent = intent.putExtras(bb);
                    startActivity(intent);
                }

            }
        });
    }
}