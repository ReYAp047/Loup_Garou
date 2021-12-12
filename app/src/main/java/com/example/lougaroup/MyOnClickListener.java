package com.example.lougaroup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MyOnClickListener implements View.OnClickListener {

    ArrayList<Roles> allRoles = new ArrayList<Roles>();
    ArrayList<Roles> giveRoles = new ArrayList<Roles>();
    String vue ;
    ArrayList<String> killedNow = new ArrayList<>();
    String annoncer;

    public MyOnClickListener(ArrayList<Roles> allRoles, ArrayList<Roles> giveRoles, String vue, ArrayList<String> killedNow, String annoncer){
        this.allRoles.addAll(allRoles);
        this.giveRoles.addAll(giveRoles);
        this.vue = vue;
        this.killedNow = killedNow;
        this.annoncer = annoncer;
    }
    @Override
    public void onClick(View view) {

    }
}


