package com.example.bc0148.gestorpilotos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ActividadPrincipal extends AppCompatActivity {

    protected ArrayList<Piloto> pilotos;
    protected Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        contexto = getApplicationContext();


        AlmacenPilotos db = new AlmacenPilotos(getApplicationContext());

        ArrayList<Piloto> pilotos =db.getAll();
        PilotoAdapter pilotoAdapter = new PilotoAdapter(this,pilotos);
        final ListView lvPilotos = (ListView)findViewById(R.id.lvListadoPilotos);
        lvPilotos.setAdapter(pilotoAdapter);

        lvPilotos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Piloto piloto = (Piloto)lvPilotos.getItemAtPosition(position);

                Intent intent = new Intent(getApplicationContext(),ActividadMostrarPilotos.class);
                Bundle bundle = new Bundle();
                bundle.putInt("ID", piloto.get_id());
                bundle.putString("NOMBRE", piloto.get_nombre());
                bundle.putInt("DORSAL", piloto.get_dorsal());
                bundle.putString("MOTO", piloto.get_moto());
                bundle.putBoolean("ACTIVO", piloto.is_activo());
                bundle.putString("IMAGENULL", piloto.get_imagen_url());

                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        //Para poner el toast
        /*AlmacenPilotos db = new AlmacenPilotos(getApplicationContext());

        ArrayList<Piloto> pilotos =db.getAll();
        PilotoAdapter pilotoAdapter = new PilotoAdapter(this,pilotos);
        final ListView lvPilotos = (ListView)findViewById(R.id.lvListadoPilotos);
        lvPilotos.setAdapter(pilotoAdapter);

        lvPilotos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String opcionElegida = lvPilotos.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), opcionElegida, Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actividad_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        AlmacenPilotos db = new AlmacenPilotos(getApplicationContext());

        db.deletaAll();

        db.add(new Piloto(15, "p1", 1, "Derbi", false, null));
        db.add(new Piloto(16, "p2", 25, "Honda", true, "image_url"));
        db.add(new Piloto(17, "p3", 66, "Yamaha", true, null));

        pilotos = db.getAll();

        Log.i("--", "--");
        ArrayAdapter<Piloto> adaptador = new PilotoAdapter(contexto, pilotos);
        ListView lvPilotos = (ListView) findViewById(R.id.lvListadoPilotos);
        lvPilotos.setAdapter(adaptador);

    }
}
