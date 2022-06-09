package com.example.mobil;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView bilgi;
    ImageView resim;
    View view;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        bilgi = itemView.findViewById(R.id.ebilgi);
        resim = itemView.findViewById(R.id.eresim);

        view = itemView;
    }
}