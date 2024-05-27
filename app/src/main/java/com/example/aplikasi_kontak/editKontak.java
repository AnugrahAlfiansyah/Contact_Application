package com.example.aplikasi_kontak;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class editKontak extends AppCompatActivity {

    Button keluar, edit;
    EditText namaL, no_handphone, alamat , namaP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_kontak);
        keluar = findViewById(R.id.btn_keluar);
        namaL = findViewById(R.id.txt_namaL);
        namaP = findViewById(R.id.txt_namaP);
        no_handphone = findViewById(R.id.txt_nohp);
        alamat = findViewById(R.id.txt_alamat);
        edit = findViewById(R.id.edit_kontak);

        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_data();
            }
        });

        tampil_data();
    }

    void edit_data(){
        String url = "http://192.168.1.5/kontak/update_data.php?id_kontak="+getIntent().getStringExtra("id_kontak");
        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status_kirim = jsonObject.getString("status");
                            if(status_kirim.equals("data_berhasil_diubah"))
                            {
                                Toast.makeText(editKontak.this, "Kontak Diubah", Toast.LENGTH_SHORT). show();
                                finish();
                            }
                            else{
                                Toast.makeText(editKontak.this, "Kontak Gagal Diubah", Toast.LENGTH_SHORT). show();
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(editKontak.this, error.getMessage(), Toast.LENGTH_SHORT). show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> form=new HashMap<String, String>();
                form.put("namaLengkap", namaL.getText().toString());
                form.put("namaPanggilan", namaP.getText().toString());
                form.put("noHp", no_handphone.getText().toString());
                form.put("alamat", alamat.getText().toString());
                return form;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
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
                            namaL.setText(jsonObject.getString("namaLengkap"));
                            namaP.setText(jsonObject.getString("namaPanggilan"));
                            no_handphone.setText(jsonObject.getString("noHp"));
                            alamat.setText(jsonObject.getString("alamat"));
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(editKontak.this, error.getMessage(), Toast.LENGTH_SHORT). show();
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}

