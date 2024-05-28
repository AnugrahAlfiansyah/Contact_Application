package com.example.aplikasi_kontak;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.Manifest;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class tambahKontak extends AppCompatActivity {

    Button keluar, simpan;
    EditText namaL, no_handphone, alamat, namaP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_kontak);
        keluar = findViewById(R.id.btn_keluar);
        namaL = findViewById(R.id.txt_namaL);
        namaP = findViewById(R.id.txt_namaP);
        no_handphone = findViewById(R.id.txt_nohp);
        alamat = findViewById(R.id.txt_alamat);
        simpan = findViewById(R.id.simpan_kontak);
        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            simpan_data();
            }
        });
    }

    void simpan_data(){
        String url="http://172.125.1.179/kontak/simpan_kontak.php";
        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status_kirim = jsonObject.getString("status");
                            if(status_kirim.equals("oke"))
                            {
                                Toast.makeText(tambahKontak.this, "Kontak Tersimpan", Toast.LENGTH_SHORT). show();
                                finish();
                            }
                            else{
                                Toast.makeText(tambahKontak.this, "Kontak Gagal Tersimpan", Toast.LENGTH_SHORT). show();
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(tambahKontak.this, error.getMessage(), Toast.LENGTH_SHORT). show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> input_form=new HashMap<String, String>();
                input_form.put("namaLengkap", namaL.getText().toString());
                input_form.put("namaPanggilan", namaP.getText().toString());
                input_form.put("noHp", no_handphone.getText().toString());
                input_form.put("alamat", alamat.getText().toString());
                return input_form;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }
}