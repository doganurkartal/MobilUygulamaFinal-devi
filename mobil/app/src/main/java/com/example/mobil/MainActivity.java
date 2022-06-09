package com.example.mobil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView tvisim, tvsoyisim, tvemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser == null) {
            Intent intent = new Intent(this, GirisEtkinligi.class);
            startActivity(intent);
            finish();
            return;
        }

        tvisim = findViewById(R.id.tvisim);
        tvsoyisim = findViewById(R.id.tvsoyisim);
        tvemail = findViewById(R.id.tvemail);

        Button mgirisbtn = findViewById(R.id.mgirisbtn);
        mgirisbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogoutUser();
            }
        });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("kullanıcılar").child(currentUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                kullanici user = snapshot.getValue(kullanici.class);
                if (user != null) {
                    tvisim.setText("isim: " + user.isim);
                    tvsoyisim.setText("soyisim: " + user.soyisim);
                    tvemail.setText("email: " + user.email);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    private void LogoutUser() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, GirisEtkinligi.class);
        startActivity(intent);
        finish();
    }
}








