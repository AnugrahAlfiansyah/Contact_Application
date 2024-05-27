package com.example.aplikasi_kontak;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class Adapter extends BaseAdapter {
    LayoutInflater layoutInflater;
    Context context;
    ArrayList<listKontak> model;
    Adapter(Context c_context, ArrayList<listKontak> c_model){
        this.context=c_context;
        this.model=c_model;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return model.size();
    }

    @Override
    public Object getItem(int index) {
        return model.get(index);
    }

    @Override
    public long getItemId(int index) {
        return index;
    }

    TextView namal, nohp;
    @Override
    public View getView(int index, View view, ViewGroup parent) {
        View view1 = layoutInflater.inflate(R.layout.list_data, parent, false);
        namal = view1.findViewById(R.id.txt_namaLengkap);
        nohp = view1.findViewById(R.id.noHp);

        namal.setText(model.get(index).getNamal());
        nohp.setText(model.get(index).getNoHp());
        return view1;
    }
}
