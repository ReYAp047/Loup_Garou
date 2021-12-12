package com.example.lougaroup;

public class Sorciere extends Roles{
private boolean tuer;
private boolean viver;

    public Sorciere(String playerRole) {
        super(playerRole);
        this.tuer=true;
        this.viver=true;
    }

    public void setTuer(boolean tuer){
        this.tuer = tuer;
    }
    public boolean getTuer(){
        return this.tuer;
    }

    public void setTuerViver(boolean viver){
        this.viver = viver;
    }
    public boolean getViver(){
        return this.viver;
    }

}
