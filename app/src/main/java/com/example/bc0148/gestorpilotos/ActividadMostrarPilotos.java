package com.example.bc0148.gestorpilotos;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

public class ActividadMostrarPilotos extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.content_info_piloto);
        Bundle bundle = this.getIntent().getExtras();

        if (bundle.getString("IMAGENNULL")!=null){

        }

        TextView idPiloto = (TextView) findViewById(R.id.infoPilotoId);
        idPiloto.setText(String.format("%d", bundle.getInt("ID")));

        TextView nombrePiloto = (TextView) findViewById(R.id.infoPilotoNombre);
        nombrePiloto.setText(bundle.getString("NOMBRE"));

        TextView dorsalPiloto = (TextView) findViewById(R.id.infoPilotoDorsal);
        dorsalPiloto.setText(String.format("%d", bundle.getInt("ID")));

        TextView motoPiloto = (TextView) findViewById(R.id.infoPilotoMoto);
        motoPiloto.setText(bundle.getString("MOTO"));

        CheckBox ctvActivo = (CheckBox) findViewById(R.id.infoPilotoActivo);
        ctvActivo.setClickable(false);
        ctvActivo.setFocusable(false);
        ctvActivo.setFocusableInTouchMode(false);
        ctvActivo.setChecked(bundle.getBoolean("ACTIVO"));


    }
}
