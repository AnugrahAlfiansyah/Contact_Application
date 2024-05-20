package com.example.aplikasi_kontak;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class cari_kontak extends AppCompatActivity {

    EditText Cari;
    Button batal, reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_kontak);
        batal = findViewById(R.id.keluar);
        reset = findViewById(R.id.reset);
        Cari = findViewById(R.id.form_cari);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cari.setText("");
            }
        });

        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}