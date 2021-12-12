package com.example.lougaroup;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RoleViewHolder> {
    ArrayList<Roles> roles;
    static String selected;
    private int lastSelectedPosition = -1;
    private Context context;

    public RecyclerViewAdapter(ArrayList<Roles> roles, Context ctx) {
        this.roles = roles;
        this.context = ctx;
    }

    @NonNull
    @Override
    // c'est pour la création des nombres d'item a afficher dans chaque holder
    public RoleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.role_custom_item,null,false);
        RoleViewHolder viewHolder = new RoleViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RoleViewHolder holder, int position) {
        Roles r = roles.get(position);
        String na = r.getPlayerName();
        String ro = r.getPlayerRole();
        holder.name.setText(na);
        holder.role.setText(ro);

        holder.selectionState.setChecked(lastSelectedPosition == position);
    }

    @Override
    public int getItemCount() {
        return roles.size();
    }

    //holder class pour recy view
    class RoleViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView role;
        RadioButton selectionState;
        public RoleViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.playerName);
            role = itemView.findViewById(R.id.playerRole);
            selectionState = itemView.findViewById(R.id.radioButton);

            selectionState.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lastSelectedPosition = getAdapterPosition();
                    notifyDataSetChanged();

                    Intent intent = new Intent("custom-message");
                    intent.putExtra("name", name.getText().toString());
                    intent.putExtra("role", role.getText().toString());
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                }
            });

        }
    }
}
