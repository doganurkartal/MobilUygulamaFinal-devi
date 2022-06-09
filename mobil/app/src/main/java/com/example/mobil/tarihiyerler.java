package com.example.mobil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class tarihiyerler extends AppCompatActivity {

    DatabaseReference ref;
    FirebaseRecyclerOptions<model> options;
    FirebaseRecyclerAdapter<model, MyViewHolder> adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarihiyerler);

        ref = FirebaseDatabase.getInstance().getReference().child("kategori").child("tarihi yerler");

        Query query = FirebaseDatabase.getInstance().getReference("kategori").child("tarihi yerler");

        recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        options = new FirebaseRecyclerOptions.Builder<model>().setQuery(ref, model.class).build();

        adapter = new FirebaseRecyclerAdapter<model, MyViewHolder>(options){

            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull model model) {
                String bilgi = model.getBilgi();
                String resim = model.getResim();
                String saat = model.getSaat();
                String ucret = model.getUcret();


                holder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(),genel.class);

                        intent.putExtra("bilgi", bilgi);
                        intent.putExtra("resim", resim);
                        intent.putExtra("saat", saat);
                        intent.putExtra("ucret", ucret);


                        startActivity(intent);
                    }
                });


                Glide.with(holder.resim.getContext())
                        .load(model.getResim())
                        //.placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                        .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                        .into(holder.resim);
            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.eleman, parent, false);

                return new MyViewHolder(v);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }
}