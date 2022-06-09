package com.example.mobil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class GirisEtkinligi extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris_etkinligi);

        //Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            finish();
            return;
        }

        Button girisbtn = findViewById(R.id.girisbtn);
        girisbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authenticateUser();
            }
        });

        TextView tvSwitchToRegister = findViewById(R.id.accountTV);
        tvSwitchToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View view) {

                startActivity(new Intent(GirisEtkinligi.this,KayitEtkinligi.class));
                finish();
            }
        });
    }

    private void authenticateUser() {
        EditText gemailet = findViewById(R.id.gemailet);
        EditText gsifreet = findViewById(R.id.gsifreet);

        String email = gemailet.getText().toString();
        String sifre = gsifreet.getText().toString();

        if (email.isEmpty() || sifre.isEmpty()) {
            Toast.makeText(this, "Eksik Girdiniz!",Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, sifre)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            showMainActivity();
                        } else {
                            Toast.makeText(GirisEtkinligi.this,"Bağlantı Başarısız.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void showMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}






