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

public class Village extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_village);

        try
        {
            Objects.requireNonNull(this.getSupportActionBar()).hide();
        }
        catch (NullPointerException ignored){}

        Bundle bc = getIntent().getExtras();
        String jRole  = bc.getString("jRole");

        //Show the player role image
        ImageView roleImage = (ImageView)findViewById(R.id.roleImage);
        switch(jRole) {
            case "Paladin":
                roleImage.setBackgroundResource(R.drawable.salvateur);
                break;
            case "Enfant":
                roleImage.setBackgroundResource(R.drawable.enfant);
                break;
            case "Sorciere":
                roleImage.setBackgroundResource(R.drawable.soci);
                break;
            case "Chasseur":
                roleImage.setBackgroundResource(R.drawable.chasseur);
                break;
            case "Cupidant":
                roleImage.setBackgroundResource(R.drawable.cupidon);
                break;
            case "Barbie":
                roleImage.setBackgroundResource(R.drawable.servante);
                break;
            case "Prist":
                roleImage.setBackgroundResource(R.drawable.prist);
                break;
            case "Voyante":
                roleImage.setBackgroundResource(R.drawable.voyante);
                break;
            default:
                roleImage.setBackgroundResource(R.drawable.simple);
                break;
        }

        //Show the player role
        TextView playerName =findViewById(R.id.playerName) ;
        playerName.setText(jRole);

        ImageView next = findViewById(R.id.next);
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
                    Intent intent = new Intent(Village.this, NameType.class);
                    intent = intent.putExtras(bb);
                    startActivity(intent);
                }else{
                    String aa =String.valueOf(giveRoles.size()-1);
                    Toast.makeText(getApplicationContext(),"Bonne Nuit !!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Village.this, BonneNuit.class);
                    intent = intent.putExtras(bb);
                    startActivity(intent);
                }
            }
        });

    }
}