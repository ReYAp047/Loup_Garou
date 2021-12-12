package com.example.lougaroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Vote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        try
        {
            Objects.requireNonNull(this.getSupportActionBar()).hide();
        }
        catch (NullPointerException ignored){}

try {

    TextView ch = findViewById(R.id.chass);
    ch.setVisibility(View.INVISIBLE);


        Bundle b = getIntent().getExtras();
        @SuppressWarnings("unchecked")
        ArrayList<Roles> allRoles  = (ArrayList<Roles>)b.getSerializable("AllRoles");
        ArrayList<Roles> alive = IntStream.range(0, allRoles.size()).filter(i -> allRoles.get(i).getAlive()).mapToObj(allRoles::get).collect(Collectors.toCollection(ArrayList::new));
    //affichage de la list des joueurs
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(alive, Vote.this);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(Vote.this);
        RecyclerView rv = findViewById(R.id.rv);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);
        rv.setHasFixedSize(true);

    LocalBroadcastManager.getInstance(Vote.this).registerReceiver(mMessageReceiver,
            new IntentFilter("custom-message"));

    ImageView killB = findViewById(R.id.killB);
    killB.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TextView hided = findViewById(R.id.hided);
            String role = hided.getText().toString();

            ArrayList<Roles> aRoles = new ArrayList<>();
            boolean b1 = aRoles.addAll(allRoles);
            ArrayList<Roles> aAlive = new ArrayList<>();
            boolean b2 = aAlive.addAll(alive);

            int i = findMeRole(aRoles, role);
            aRoles.get(i).setAlive(false);

            i = findMeRole(aAlive, role);
            aAlive.remove(i);

            Toast.makeText(getApplicationContext(),"Le joueur a éter éliminer !!",Toast.LENGTH_SHORT).show();


            if(role.equals("Chasseur")){
                ch.setVisibility(View.VISIBLE);
                RecyclerViewAdapter adapter = new RecyclerViewAdapter(aAlive, Vote.this);
                RecyclerView.LayoutManager lm = new LinearLayoutManager(Vote.this);
                RecyclerView rv = findViewById(R.id.rv);
                rv.setLayoutManager(lm);
                rv.setAdapter(adapter);
                rv.setHasFixedSize(true);

                LocalBroadcastManager.getInstance(Vote.this).registerReceiver(mMessageReceiver,
                        new IntentFilter("custom-message"));

                ImageView killB = findViewById(R.id.killB);
                killB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView hided = findViewById(R.id.hided);
                        String role = hided.getText().toString();

                        ArrayList<Roles> ARoles = new ArrayList<>();
                        boolean b1 = ARoles.addAll(aRoles);
                        ArrayList<Roles> AAlive = new ArrayList<>();
                        boolean b2 = AAlive.addAll(aAlive);

                        int i = findMeRole(ARoles, role);
                        ARoles.get(i).setAlive(false);

                        i = findMeRole(AAlive, role);
                        AAlive.remove(i);

                        Toast.makeText(getApplicationContext(),"Le joueur a éter éliminer !!",Toast.LENGTH_SHORT).show();



                int nbLoup = NombreLoup(AAlive);
                int nbVillage = NombreVillage(AAlive);

                if(nbLoup == 0){
                    Intent intent = new Intent(Vote.this, VillageVictory.class);
                    startActivity(intent);
                }
                else if(nbLoup >= nbVillage){
                    Intent intent = new Intent(Vote.this, VictoireLoup.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(Vote.this, BonneNuit.class);
                    Bundle bb = new Bundle();
                    bb.putSerializable("AllRoles", (Serializable) ARoles);
                    intent = intent.putExtras(bb);
                    startActivity(intent);
                }

            }
        });
            }else {
                int nbLoup = NombreLoup(aAlive);
                int nbVillage = NombreVillage(aAlive);

                if(nbLoup == 0){
                    Intent intent = new Intent(Vote.this, VillageVictory.class);
                    startActivity(intent);
                }
                else if(nbLoup >= nbVillage){
                    Intent intent = new Intent(Vote.this, VictoireLoup.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(Vote.this, BonneNuit.class);
                    Bundle bb = new Bundle();
                    bb.putSerializable("AllRoles", (Serializable) aRoles);
                    intent = intent.putExtras(bb);
                    startActivity(intent);
                }
            }



        }
    });

}catch(Exception error1) {
    Toast.makeText(getApplicationContext(),error1.toString(),Toast.LENGTH_SHORT).show();
    System.out.println(error1.toString());
    error1.printStackTrace();
}
    }


    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String role = intent.getStringExtra("role");
            String name = intent.getStringExtra("name");
            Toast.makeText(Vote.this,role +"____"+name ,Toast.LENGTH_SHORT).show();

            TextView hided = findViewById(R.id.hided);
            hided.setText(role);
        }
    };

    public int findMeRole(ArrayList<Roles> allAlive, String role){
        int i = 0;

        while (true) {
            if (allAlive.get(i).getPlayerRole().equals(role)) {
                return i;
            }

            i++;
        }
    }

    public int NombreLoup(ArrayList<Roles> alive){
        int nb=0;
        for(int k=0; k<alive.size(); k++){
            if(alive.get(k).getLoup()){
                nb++;
            }
        }
        return nb;
    }

    public int NombreVillage(ArrayList<Roles> alive){
        int nb=0;
        for(int k=0; k<alive.size(); k++){
            if(!alive.get(k).getLoup()){
                nb++;
            }
        }
        return nb;
    }

}