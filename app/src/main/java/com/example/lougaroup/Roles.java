package com.example.lougaroup;

import android.app.Application;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;

public class Roles implements Parcelable {

    private String playerName;
    private String playerRole;
    private String question;
    private boolean alive;
    private boolean lover;
    private boolean loup;
    private boolean blocker;
    private boolean idol;
    private boolean use;
    private boolean kill;
    private boolean shilded;
    private boolean showed;

    public boolean isKill() {
        return kill;
    }

    public void setKill(boolean kill) {
        this.kill = kill;
    }

    public void setUse(boolean use) {
        this.use = use;
    }
    public boolean getUse() {
        return use;
    }

    public boolean isLover() {
        return lover;
    }

    public boolean isLoup() {
        return loup;
    }

    public boolean isBlocker() {
        return blocker;
    }

    public boolean isIdol() {
        return idol;
    }

    public boolean isUse() {
        return use;
    }

    public Roles (String playerRole){
    this.playerRole = playerRole;
    this.alive = true;
    this.lover = false;
    this.loup = false;
    this.blocker = false;
    this.idol = false;
    this.use = false;
    this.kill = false;
    this.shilded = false;
    this.showed = false;
}

    public String getQuestion() {
        return question;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setPlayerName(String playerName){
        this.playerName = playerName;
    }
    public String getPlayerName(){
        return playerName;
    }

    public void setPlayerRole(String playerRole){
        this.playerRole = playerRole;
    }
    public String getPlayerRole(){
        return playerRole;
    }

    public void setAlive(boolean alive){
        this.alive = alive;
    }
    public boolean getAlive(){
        return alive;
    }

    public void setLover(boolean lover){
        this.lover = lover;
    }
    public boolean getLover(){
        return lover;
    }

    public void setLoup(boolean loup){
        this.loup = loup;
    }
    public boolean getLoup(){
        return loup;
    }

    public void setBlocker(boolean blocker){
        this.blocker = blocker;
    }
    public boolean getBlocker(){
        return blocker;
    }

    public void setIdol(boolean idol){
        this.idol = idol;
    }
    public boolean getIdol(){
        return idol;
    }

    public boolean isShilded() {
        return shilded;
    }

    public void setShilded(boolean shilded) {
        this.shilded = shilded;
    }

    public boolean isShowed() {
        return showed;
    }

    public void setShowed(boolean showed) {
        this.showed = showed;
    }

    public Roles(Parcel in) {
        playerName = in.readString();
        playerRole = in.readString();
        question = in.readString();
        alive = in.readByte() != 0;
        lover = in.readByte() != 0;
        loup = in.readByte() != 0;
        blocker = in.readByte() != 0;
        idol = in.readByte() != 0;
        use = in.readByte() != 0;
        kill = in.readByte() != 0;

        shilded = in.readByte() != 0;
        showed = in.readByte() != 0;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(playerName);
        parcel.writeString(playerRole);
        parcel.writeString(question);
        parcel.writeByte((byte) (alive ? 1 : 0));
        parcel.writeByte((byte) (lover ? 1 : 0));
        parcel.writeByte((byte) (loup ? 1 : 0));
        parcel.writeByte((byte) (blocker ? 1 : 0));
        parcel.writeByte((byte) (idol ? 1 : 0));
        parcel.writeByte((byte) (use ? 1 : 0));

        parcel.writeByte((byte) (kill ? 1 : 0));
        parcel.writeByte((byte) (shilded ? 1 : 0));
        parcel.writeByte((byte) (showed ? 1 : 0));




    }

    public static final Creator<Roles> CREATOR = new Creator<Roles>() {
        @Override
        public Roles createFromParcel(Parcel in) {
            return new Roles(in);
        }

        @Override
        public Roles[] newArray(int size) {
            return new Roles[size];
        }
    };

}
