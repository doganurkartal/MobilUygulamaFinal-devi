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
import com.google.firebase.database.FirebaseDatabase;

public class KayitEtkinligi extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_etkinligi);

        //initialize FirebaseAuth
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null){
            finish();
            return;
        }

        Button kayitbtn = findViewById(R.id.kayıtbtn);
        kayitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                kullanici();
            }
        });

        TextView textViewSwitchToLogin = findViewById(R.id.accountTV);
        textViewSwitchToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(KayitEtkinligi.this,KayitEtkinligi.class));
                finish();
            }
        });

    }
    private void kullanici(){
        EditText isimet = findViewById(R.id.isimet);
        EditText soyisimet = findViewById(R.id.soyisimet);
        EditText emailet = findViewById(R.id.emailet);
        EditText sifreet = findViewById(R.id.sifreet);

        String isim = isimet.getText().toString();
        String soyisim = soyisimet.getText().toString();
        String email = emailet.getText().toString();
        String sifre = sifreet.getText().toString();

        if(isim.isEmpty() || soyisim.isEmpty() ||email.isEmpty() || sifre.isEmpty() ){
            Toast.makeText(this, "Eksik Giriş Yaptınız. ",Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, sifre)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            kullanici user = new  kullanici (isim, soyisim, email);
                            FirebaseDatabase.getInstance().getReference("kullanicilar")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    showMainActivity();
                                }
                            });
                        } else {
                            Toast.makeText(KayitEtkinligi.this,"Kimlik Doğrulama Başarasız!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void showMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}