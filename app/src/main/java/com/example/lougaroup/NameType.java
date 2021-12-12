package com.example.lougaroup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class NameType extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nametype);

        try
        {
            Objects.requireNonNull(this.getSupportActionBar()).hide();
        }
        catch (NullPointerException ignored){}

        ImageView rand = (ImageView)findViewById(R.id.rand);




        rand.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                try{
                    Bundle b = getIntent().getExtras();

                    @SuppressWarnings("unchecked")
                    ArrayList<Roles> allRoles = (ArrayList<Roles>)b.getSerializable("AllRoles");
                    @SuppressWarnings("unchecked")
                    ArrayList<Roles> giveRoles  = (ArrayList<Roles>) b.getSerializable("GiveRoles");

                    int max = giveRoles.size()-1;
                    int random = new Random().nextInt((max) + 1);
                EditText txName = (EditText) findViewById(R.id.namee);
                String name = txName.getText().toString();

                if(name.length()>3){

                    //put the player name in the object
                    String r = giveRoles.get(random).getPlayerRole();
                    for(int j = 0 ; j<allRoles.size(); j++){
                        if(allRoles.get(j).getPlayerRole().equals(r)){
                            allRoles.get(j).setPlayerName(name);
                            break;
                        }
                    }

                    boolean loup = giveRoles.get(random).getLoup();
                    String jRole = giveRoles.get(random).getPlayerRole();
                    giveRoles.remove(random);


                    Bundle bb = new Bundle();
                    bb.putSerializable("AllRoles", (Serializable) allRoles);
                    bb.putSerializable("GiveRoles", (Serializable) giveRoles);
                    bb.putString("jRole",jRole);

                    if(!loup){
                        Toast.makeText(getApplicationContext(),"Tue joue avec les villages !!",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(NameType.this, Village.class);
                        intent = intent.putExtras(bb);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(),"Tue joue avec les loups !!",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(NameType.this, Loup.class);
                        intent = intent.putExtras(bb);
                        startActivity(intent);
                    }




                }else{
                    Toast.makeText(getApplicationContext(),"le nom doit etre > 3 lettres",Toast.LENGTH_SHORT).show();
                }
                }catch(Exception error1) {
                    Toast.makeText(getApplicationContext(),error1.toString(),Toast.LENGTH_SHORT).show();
                    error1.printStackTrace();
                }


            }


        }


        );







    }
}