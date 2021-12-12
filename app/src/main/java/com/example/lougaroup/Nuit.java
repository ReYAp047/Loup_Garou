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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Nuit extends AppCompatActivity {
    static boolean loupKill = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuit);

        try
        {
            Objects.requireNonNull(this.getSupportActionBar()).hide();
        }
        catch (NullPointerException ignored){}

        loupKill = false;

        Bundle b = getIntent().getExtras();
        @SuppressWarnings("unchecked")
        ArrayList<Roles> allRoles  = (ArrayList<Roles>)b.getSerializable("AllRoles");
        ArrayList<Roles> allAlive = new ArrayList<Roles>();
        for(int k=0; k<allRoles.size(); k++){
            if(allRoles.get(k).getAlive()){
                allAlive.add(allRoles.get(k));
            }
        }


        ArrayList<Roles> giveRoles = new ArrayList<Roles>();
        giveRoles.addAll(allAlive);


        ImageView im = findViewById(R.id.roleImage);
        TextView playerRole = findViewById(R.id.layout);
        TextView Question = findViewById(R.id.question);


        String vue="";
        ArrayList<String> killedNow = new ArrayList<>();
        String annoncer ="";

        FloatingActionButton fb = findViewById(R.id.save);
        fb.setClickable(false);
        FloatingActionButton lv = findViewById(R.id.live);
        lv.setClickable(false);

        int k = isShildedd(allAlive);
        if(k!=-1){
            allAlive.get(k).setBlocker(false);
        }

        ImageView next = findViewById(R.id.next);
        next.setOnClickListener(new MyOnClickListener(allAlive,giveRoles, vue, killedNow, annoncer ) {

            @Override
            public void onClick(View view) {
                try {


                FloatingActionButton fb = findViewById(R.id.save);
                    fb.setClickable(true);
                  fb.setVisibility(View.INVISIBLE);
                FloatingActionButton live = findViewById(R.id.live);
                    live.setClickable(true);
                    live.setVisibility(View.INVISIBLE);
                int x = giveRoles.size();
                if(x>0){

                    //affichage de la list des joueurs
                    RecyclerViewAdapter adapter = new RecyclerViewAdapter(allAlive, Nuit.this);
                    RecyclerView.LayoutManager lm = new LinearLayoutManager(Nuit.this);
                    RecyclerView rv = findViewById(R.id.rv);
                    rv.setLayoutManager(lm);
                    rv.setAdapter(adapter);
                    rv.setHasFixedSize(true);

                    playerRole.setText(giveRoles.get(0).getPlayerRole());
                    //Affichage de l'image est du role
                    switch(giveRoles.get(0).getPlayerRole()) {
                        case "Paladin":
                            im.setBackgroundResource(R.drawable.salvateur);
                            Question.setText(giveRoles.get(0).getQuestion());
                            fb.setVisibility(View.VISIBLE);
                            break;

                        case "Enfant":
                            im.setBackgroundResource(R.drawable.enfant);
                            if(giveRoles.get(0).getUse()){
                                Question.setText(giveRoles.get(0).getQuestion());
                                fb.setVisibility(View.VISIBLE);
                            }else{
                                Question.setText(R.string.pasIdole);
                                rv.setVisibility(View.INVISIBLE);
                            }
                            break;

                        case "Sorciere":
                            im.setBackgroundResource(R.drawable.soci);
                            if(giveRoles.get(0).getUse() && giveRoles.get(0).isKill()){
                                Question.setText(giveRoles.get(0).getQuestion());
                                live.setVisibility(View.VISIBLE);
                                fb.setVisibility(View.VISIBLE);
                            }
                            else if(giveRoles.get(0).getUse()){
                                Question.setText(R.string.dejaTuer);
                                rv.setVisibility(View.INVISIBLE);
                                live.setVisibility(View.VISIBLE);
                            }else{
                                Question.setText(R.string.dejaViver);
                                rv.setVisibility(View.VISIBLE);
                                live.setVisibility(View.INVISIBLE);
                            }
                            break;

                        case "Chasseur":
                            im.setBackgroundResource(R.drawable.chasseur);
                            rv.setVisibility(View.INVISIBLE);
                            Question.setText(R.string.montre);
                            giveRoles.remove(0);
                            break;

                        case "Simple":
                            im.setBackgroundResource(R.drawable.simple);
                            rv.setVisibility(View.INVISIBLE);
                            Question.setText(R.string.montre);
                            giveRoles.remove(0);
                            break;

                        case "Cupidant":
                            im.setBackgroundResource(R.drawable.cupidon);
                            if(giveRoles.get(0).getUse()){
                                Question.setText(R.string.dejaLiee);
                                rv.setVisibility(View.INVISIBLE);
                            }else{
                                Question.setText(giveRoles.get(0).getQuestion());
                                fb.setVisibility(View.VISIBLE);
                            }
                            break;

                        case "Barbie":
                            im.setBackgroundResource(R.drawable.servante);
                            Question.setText(giveRoles.get(0).getQuestion());
                            rv.setVisibility(View.INVISIBLE);
                            giveRoles.remove(0);
                            break;

                        case "Prist":
                            im.setBackgroundResource(R.drawable.prist);
                            if(giveRoles.get(0).getUse()){
                                Question.setText(R.string.dejaViver);
                                rv.setVisibility(View.INVISIBLE);
                            }else{
                                Question.setText(giveRoles.get(0).getQuestion());
                                fb.setVisibility(View.VISIBLE);
                            }
                            break;

                        case "Voyante":
                            im.setBackgroundResource(R.drawable.voyante);
                            Question.setText(giveRoles.get(0).getQuestion());
                            fb.setVisibility(View.VISIBLE);
                            break;

                        case "Serber":
                            im.setBackgroundResource(R.drawable.grand);
                            Question.setText(giveRoles.get(0).getQuestion());
                            fb.setVisibility(View.VISIBLE);
                            break;

                        case "Pere":
                            im.setBackgroundResource(R.drawable.pere);
                            Question.setText(giveRoles.get(0).getQuestion());
                            if(giveRoles.get(0).getUse()){
                                rv.setVisibility(View.INVISIBLE);
                            }else{
                                fb.setVisibility(View.VISIBLE);
                            }

                            break;

                        default:
                            im.setBackgroundResource(R.drawable.loup);
                            rv.setVisibility(View.INVISIBLE);
                            Question.setText(R.string.montre);
                            giveRoles.remove(0);
                            break;
                    }



                    LocalBroadcastManager.getInstance(Nuit.this).registerReceiver(mMessageReceiver,
                            new IntentFilter("custom-message"));


                    fb.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(getApplicationContext(),"Saved !",Toast.LENGTH_SHORT).show();

                            try{


                            TextView hided = findViewById(R.id.hidedRole);
                            String role = hided.getText().toString();
                            int i = findMeRole(allAlive, role);
                            int k = isShildedd(allAlive);
                            int j = isBlocked(allAlive);

                            switch(giveRoles.get(0).getPlayerRole()) {
                                case "Paladin":
                                    boolean b =allAlive.get(findMeRole(allAlive,"Paladin")).getBlocker();
                                    if(!b){
                                        if(k != i){
                                            allAlive.get(i).setShilded(true);
                                            if(k!=-1){
                                                allAlive.get(k).setShilded(false);
                                            }
                                            fb.setVisibility(View.INVISIBLE);
                                            giveRoles.remove(0);
                                        }else if (role.equals("Paladin") && !allRoles.get(i).isShilded()){
                                            allAlive.get(i).setShilded(true);
                                            if(k!=-1){
                                                allAlive.get(k).setShilded(false);
                                            }
                                            fb.setVisibility(View.INVISIBLE);
                                            giveRoles.remove(0);
                                        }
                                        else{
                                            Toast.makeText(getApplicationContext(),"You can not shild the same persone tow time successively!",Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    break;

                                case "Enfant":
                                    allAlive.get(i).setIdol(true);
                                    int r = findMeRole(allAlive,"Enfant");
                                    allAlive.get(r).setUse(true);
                                    fb.setVisibility(View.INVISIBLE);
                                    giveRoles.remove(0);
                                    break;

                                case "Sorciere":
                                    allAlive.get(findMeRole(allAlive,"Sorciere")).setKill(true);
                                    if(!allAlive.get(i).isShilded()){
                                        allAlive.get(i).setAlive(false);
                                        killedNow.add(allAlive.get(i).getPlayerRole());
                                    }
                                    fb.setVisibility(View.INVISIBLE);
                                    giveRoles.remove(0);
                                    break;

                                case "Chasseur":
                                    break;

                                case "Simple":
                                    break;

                                case "Cupidant":
                                    break;

                                case "Barbie":
                                    break;

                                case "Prist":
                                    allAlive.get(findMeRole(allAlive,"Prist")).setUse(true);

                                    if(!allAlive.get(findMeRole(allAlive,"prist")).getBlocker()){
                                        if(!allAlive.get(i).isShilded()){
                                            allAlive.get(i).setShowed(true);
                                            annoncer = allAlive.get(i).getPlayerName();
                                        }


                                    }

                                    fb.setVisibility(View.INVISIBLE);
                                    giveRoles.remove(0);
                                    break;

                                case "Voyante":

                                    if(!allAlive.get(findMeRole(allAlive,"Voyante")).getBlocker()){
                                        vue = allAlive.get(i).getPlayerRole();
                                    }
                                    fb.setVisibility(View.INVISIBLE);
                                    giveRoles.remove(0);

                                    break;

                                case "Serber":
                                    if(!allAlive.get(i).isShilded()){
                                        allAlive.get(i).setBlocker(true);
                                    }


                                    fb.setVisibility(View.INVISIBLE);
                                    giveRoles.remove(0);
                                    break;

                                case "Pere":
                                    break;

                                default:
                                    break;
                            }



                            }catch(Exception error1) {
                                Toast.makeText(getApplicationContext(),error1.toString(),Toast.LENGTH_SHORT).show();
                                error1.printStackTrace();
                            }


                        }
                    });


                    live.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getApplicationContext(),"Pouvoir appliquer",Toast.LENGTH_SHORT).show();
                            TextView hided = findViewById(R.id.hidedRole);
                            String role = hided.getText().toString();
                            int i = findMeRole(allAlive, role);
                            allAlive.get(i).setUse(true);
                            live.setVisibility(View.INVISIBLE);
                        }
                    });


                }
                else if(!loupKill){
                    loupKill=true;

                    RecyclerViewAdapter adapter = new RecyclerViewAdapter(allAlive, Nuit.this);
                    RecyclerView.LayoutManager lm = new LinearLayoutManager(Nuit.this);
                    RecyclerView rv = findViewById(R.id.rv);
                    rv.setLayoutManager(lm);
                    rv.setAdapter(adapter);
                    rv.setHasFixedSize(true);
                    rv.setVisibility(View.VISIBLE);

                    playerRole.setText(R.string.Loups);
                    im.setBackgroundResource(R.drawable.wolfs);
                    Question.setText(R.string.Kill);
                    fb.setVisibility(View.VISIBLE);

                    LocalBroadcastManager.getInstance(Nuit.this).registerReceiver(mMessageReceiver,
                            new IntentFilter("custom-message"));

                    fb.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(getApplicationContext(),"Saved !",Toast.LENGTH_SHORT).show();

                            try{


                                TextView hided = findViewById(R.id.hidedRole);
                                String role = hided.getText().toString();
                                int i = findMeRole(allAlive, role);
                                int k = isShildedd(allAlive);

                                        if(!allAlive.get(i).isShilded()){
                                            allAlive.get(i).setAlive(false);
                                            killedNow.add(allAlive.get(i).getPlayerRole());
                                        }
                                        fb.setVisibility(View.INVISIBLE);

                            }catch(Exception error1) {
                                Toast.makeText(getApplicationContext(),error1.toString(),Toast.LENGTH_SHORT).show();
                                error1.printStackTrace();
                            }


                        }
                    });

                }

                else
                    {
                        //  lezem nsob el allAlive fel allRoles bech tsir el mise a jour
                        for(int i=0; i<allRoles.size(); i++){
                            String ro = allRoles.get(i).getPlayerRole();
                           int k = 0;
                            while (k<allAlive.size()){
                                if(allAlive.get(k).getPlayerRole().equals(ro)){
                                    allRoles.get(i).setAlive(allAlive.get(k).getAlive());
                                    allRoles.get(i).setBlocker(allAlive.get(k).getBlocker());
                                    allRoles.get(i).setKill(allAlive.get(k).isKill());
                                    allRoles.get(i).setIdol(allAlive.get(k).isIdol());
                                    allRoles.get(i).setShilded(allAlive.get(k).isShilded());
                                    allRoles.get(i).setLoup(allAlive.get(k).isLoup());
                                    allRoles.get(i).setLover(allAlive.get(k).isLover());
                                    allRoles.get(i).setShowed(allAlive.get(k).isShowed());
                                    allRoles.get(i).setUse(allAlive.get(k).isUse());

                                    break;
                                }

                                k++;
                            }

                        }

                         Toast.makeText(getApplicationContext(),"BONJOUR !!",Toast.LENGTH_SHORT).show();
                         Intent intent = new Intent(Nuit.this, Bonjour.class);
                         Bundle bb = new Bundle();
                         bb.putString("Vue", vue);
                         bb.putSerializable("AllRoles", (Serializable) allRoles);
                         bb.putSerializable("KilledNow", killedNow);
                         bb.putString("Annoncer",annoncer);
                        intent = intent.putExtras(bb);
                         startActivity(intent);
                    }


            }catch(Exception error1) {
                Toast.makeText(getApplicationContext(),error1.toString(),Toast.LENGTH_SHORT).show();
                System.out.println(error1.toString());
                error1.printStackTrace();
            }


            }
        });







    }


    public int findMeRole(ArrayList<Roles> allAlive, String role){
        int i = 0;

        while (true) {
            if (allAlive.get(i).getPlayerRole().equals(role)) {
                return i;
            }

            i++;
        }
    }

    public int isShildedd(ArrayList<Roles> allAlive){
        int i = 0;

        while(i<=allAlive.size()-1){
            if (allAlive.get(i).isShilded()) {
                return i;
            }

            i++;


        }
        return -1;
    }

    public int isBlocked(ArrayList<Roles> allAlive){
        int i = 0;

        while(i<=allAlive.size()-1){
            if (allAlive.get(i).isBlocker()) {
                return i;
            }

            i++;

        }
        return -1;
    }




    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String role = intent.getStringExtra("role");
            String name = intent.getStringExtra("name");
            Toast.makeText(Nuit.this,role +" "+name ,Toast.LENGTH_SHORT).show();

            TextView hided = findViewById(R.id.hidedRole);
            hided.setText(role);
        }
    };

}