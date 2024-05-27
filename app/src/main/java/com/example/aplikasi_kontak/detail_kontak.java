package com.example.aplikasi_kontak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class detail_kontak extends AppCompatActivity {

    TextView namal, namap, nohp, alamat;
    Button hapus, edit;
    ArrayList<listKontak> model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kontak);
        namal = findViewById(R.id.Namal);
        namap = findViewById(R.id.Namap);
        nohp = findViewById(R.id.noHP);
        alamat = findViewById(R.id.alamaT);
        hapus = findViewById(R.id.btn_hapus);
        edit = findViewById(R.id.btn_edit);

        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hapus_data();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id_kotak = getIntent().getStringExtra("id_kontak");
                Intent intent = new Intent(getApplicationContext(), editKontak.class);
                intent.putExtra("id_kontak", id_kotak);
                startActivity(intent);
            }
        });

        tampil_data();
    }

    void tampil_data(){
        String url = "http://192.168.1.5/kontak/tampil_data.php?id_kontak="+getIntent().getStringExtra("id_kontak");
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            namal.setText(jsonObject.getString("namaLengkap"));
                            namap.setText(jsonObject.getString("namaPanggilan"));
                            nohp.setText(jsonObject.getString("noHp"));
                            alamat.setText(jsonObject.getString("alamat"));
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(detail_kontak.this, error.getMessage(), Toast.LENGTH_SHORT). show();
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    void hapus_data(){
        String url = "http://192.168.1.5/kontak/hapus_data.php?id_kontak="+getIntent().getStringExtra("id_kontak");
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("status");
                            if (status.equals("data_terhapus")){
                                Toast.makeText(detail_kontak.this, "Kontak Terhapus", Toast.LENGTH_SHORT). show();
                                finish();
                            } else {
                                Toast.makeText(detail_kontak.this, "Kontak Gagal Dihapus", Toast.LENGTH_SHORT). show();
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(detail_kontak.this, error.getMessage(), Toast.LENGTH_SHORT). show();
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    @Override
    protected void onResume() {
        tampil_data();
        super.onResume();
    }

}