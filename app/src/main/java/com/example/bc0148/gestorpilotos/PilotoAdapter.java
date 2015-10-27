package com.example.bc0148.gestorpilotos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;


public class PilotoAdapter extends ArrayAdapter<Piloto> {
    private Context _contexto;
    private ArrayList<Piloto> _pilotos;

    public PilotoAdapter(Context context, ArrayList<Piloto> pilotos) {
        super(context, R.layout.content_tabla, pilotos);
        this._pilotos = pilotos;
        this._contexto = context;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) _contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.content_tabla, null);
        }

        Piloto piloto = _pilotos.get(position);
        if (piloto != null) {
            TextView tvId = (TextView) convertView.findViewById(R.id.tvId);
            tvId.setText(String.format("%d", piloto.get_id()));

            TextView tvNombre = (TextView) convertView.findViewById(R.id.tvNombre);
            tvNombre.setText(piloto.get_nombre());

            TextView tvDorsal = (TextView) convertView.findViewById(R.id.tvDorsal);
            tvDorsal.setText(String.format("%d", piloto.get_dorsal()));

            TextView tvMoto = (TextView) convertView.findViewById(R.id.tvMoto);
            tvMoto.setText(piloto.get_moto());

            CheckBox ctvActivo = (CheckBox) convertView.findViewById(R.id.tvActivo);
            ctvActivo.setClickable(false);
            ctvActivo.setFocusable(false);
            ctvActivo.setFocusableInTouchMode(false);
            ctvActivo.setChecked(piloto.is_activo());
        }

        return convertView;
    }

}
