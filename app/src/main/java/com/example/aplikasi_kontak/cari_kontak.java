package com.example.aplikasi_kontak;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class cari_kontak extends AppCompatActivity {

    EditText Cari;
    Button batal, reset;
    ListView listView;
    ArrayList<listKontak> model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_kontak);
        batal = findViewById(R.id.keluar);
        reset = findViewById(R.id.reset);
        Cari = findViewById(R.id.form_cari);
        listView = findViewById(R.id.list_cari);

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

        Cari.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                cari_data(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    void cari_data(String data)
    {
        String url="http://172.125.1.179/kontak/cari.php?q="+data;
        StringRequest request=new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject;
                            jsonObject = new JSONObject(response);
                            JSONArray getHasil=jsonObject.getJSONArray("hasil");
                            model=new ArrayList<>();
                            for (int index=0; index< getHasil.length();index++)
                            {
                                JSONObject getData=getHasil.getJSONObject(index);
                                String id_kontak=getData.getString("id_kontak");
                                String namal=getData.getString("namaLengkap");
                                String no_handphone=getData.getString("noHp");

                                model.add(new listKontak(id_kontak,namal,no_handphone, "", ""));
                            }
                            Adapter adapter = new Adapter(getApplicationContext(), model);
                            listView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(cari_kontak.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}


