package com.example.aplikasi_kontak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton tambahKontak;
    Button btnCari;
    ListView listView;
    ArrayList<listKontak> model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tambahKontak = findViewById(R.id.tambah_kontak);
        btnCari = findViewById(R.id.btn_cari);
        listView = findViewById(R.id.list_data);
        tambahKontak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), tambahKontak.class);
                startActivity(intent);
            }
        });

        btnCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), cari_kontak.class);
                startActivity(intent);
            }
        });
        load();
    }
    void load(){
        String url="http://192.168.1.5/kontak/data_kontak.php";
        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject =new JSONObject(response);
                            JSONArray getHasil = jsonObject.getJSONArray("hasil");
                            model = new ArrayList();
                            for (int i=0; i<getHasil.length(); i++){
                                JSONObject getData = getHasil.getJSONObject(i);
                                String id_kontak = getData.getString("id_kontak");
                                String namaL = getData.getString("namaLengkap");
                                String namaP = getData.getString("namaPanggilan");
                                String noHp = getData.getString("noHp");
                                String alamat = getData.getString("alamat");
                                model.add(new listKontak(id_kontak,namaL, namaP, noHp, alamat));
                            }
                            Adapter adapter = new Adapter(getApplicationContext(), model);
                            listView.setAdapter(adapter);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int index, long id) {
                                    Intent intent = new Intent(getApplicationContext(), detail_kontak.class);
                                    intent.putExtra("id_kontak", model.get(index).id_kontak);
                                    startActivity(intent);
                                }
                            });
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    @Override
    protected void onResume() {
        load();
        super.onResume();
    }
}
