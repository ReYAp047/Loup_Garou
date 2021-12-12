package com.example.lougaroup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class playerRole extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players_number);

        try
        {
            Objects.requireNonNull(this.getSupportActionBar()).hide();
        }
        catch (NullPointerException ignored){}


        EditText txNb = (EditText) findViewById(R.id.nb);
        ImageView play = (ImageView) findViewById(R.id.pl);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nbb = Integer.parseInt(txNb.getText().toString());

                if(nbb > 5 && nbb < 13){

                    Roles[] rolesTab = new Roles[12];
                    rolesTab[0] = new Roles("Paladin");
                        rolesTab[0].setQuestion("Voulez vous proteger qui ?");
                        rolesTab[0].setUse(true);

                    rolesTab[1] = new Roles("Serber");
                        rolesTab[1].setLoup(true);
                        rolesTab[1].setQuestion("Voulez vous blocker qui ?");
                        rolesTab[1].setUse(true);

                    rolesTab[2] = new Roles("Voyante");
                         rolesTab[2].setQuestion("Voulez vous voir qui ?");
                         rolesTab[2].setUse(true);

                    rolesTab[3] = new Roles("Enfant");
                        rolesTab[3].setQuestion("Choisir votre idol ?");
                        rolesTab[3].setUse(true);

                    rolesTab[4] = new Sorciere("Sorciere");
                        rolesTab[4].setQuestion("Voulez vous tuer ou viver qui ?");
                        rolesTab[4].setUse(true);
                        rolesTab[4].setKill(true);

                    rolesTab[5] = new Roles("Chasseur");
                        rolesTab[5].setQuestion("Voulez vous chasser qui ?");

                    rolesTab[6] = new Roles("LoupSimple");
                    rolesTab[6].setLoup(true);
                    rolesTab[6].setQuestion("");

                    rolesTab[7] = new Roles("Cupidant");
                        rolesTab[7].setQuestion("Choisir les deux amoureux <3>");
                        rolesTab[7].setUse(true);

                    rolesTab[8] = new Roles("Barbie");
                        rolesTab[8].setQuestion("Faite moi une signe ?");
                        rolesTab[8].setUse(true);

                    rolesTab[9] = new Roles("Prist");
                        rolesTab[9].setQuestion("Voulez vous connaitre un role ?");
                        rolesTab[9].setUse(true);

                    rolesTab[10] = new Roles("Pere");
                    rolesTab[10].setLoup(true);
                    rolesTab[10].setQuestion("Infecter le joueur tuer ?");
                    rolesTab[10].setUse(true);

                    rolesTab[11] = new Roles("Simple");
                        rolesTab[11].setQuestion("");

                    ArrayList<Roles> allRoles = new ArrayList<>(Arrays.asList(rolesTab).subList(0, nbb));
                    ArrayList<Roles> giveRoles = new ArrayList<>(Arrays.asList(rolesTab).subList(0, nbb));

                    Toast.makeText(getApplicationContext(),"Le Jeu commance !",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(playerRole.this, NameType.class);
                    Bundle b = new Bundle();
                    b.putSerializable("AllRoles", (Serializable)allRoles);
                    b.putSerializable("GiveRoles", (Serializable)giveRoles);
                    intent = intent.putExtras(b);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Le nombre doit etre entre 5 et 12",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}