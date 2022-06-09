package com.example.mobil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;


public class genel extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    TextView ebilgi, eucret, esaat;
    ImageView eresim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.genel);

        eucret = findViewById(R.id.eucret);
        esaat = findViewById(R.id.esaat);
        ebilgi = findViewById(R.id.ebilgi);
        eresim = findViewById(R.id.eresim);


        String ucret = getIntent().getStringExtra("eucret");
        String sure = getIntent().getStringExtra("esaat");
        String bilgi = getIntent().getStringExtra("ebilgi");
        String img = getIntent().getStringExtra("eresim");


        eucret.setText("Ücret: " + ucret);
        esaat.setText("Saat: " + sure);
        ebilgi.setText(bilgi);
        ebilgi.setMovementMethod(new ScrollingMovementMethod());


        Glide.with(eresim.getContext())
                .load(img)
                //.placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .into(eresim);


    }

}



