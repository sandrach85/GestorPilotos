package com.example.bc0148.gestorpilotos;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ActividadMostrarPilotos extends Activity {

    final String URL_OBJETIVO = "https://avatars1.githubusercontent.com/u/5365410";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.content_info_piloto);
        Bundle bundle = this.getIntent().getExtras();
        ImageView image= (ImageView) findViewById(R.id.infoPilotoImagen);

        if ((bundle.getString("IMAGENULL"))!=null){
            // Mostrar contenido
            TareaCargarImagen tarea = new TareaCargarImagen();
            image.setTag(URL_OBJETIVO);
            tarea.execute(image);
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

    private class TareaCargarImagen extends AsyncTask<ImageView, Void, Bitmap> {

        ImageView imageView = null;

        @Override
        protected Bitmap doInBackground(ImageView... imageViews) {
            this.imageView = imageViews[0];
            return download_Image((String) imageView.getTag());
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }

        private Bitmap download_Image(String url) {
            Bitmap bmp = null;

            try {
                URL ulrn = new URL(url);
                HttpURLConnection con = (HttpURLConnection) ulrn.openConnection();
                InputStream is = con.getInputStream();
                bmp = BitmapFactory.decodeStream(is);
                if (bmp != null)
                    return bmp;

            } catch (Exception e) {
                Log.e("ERROR", e.getMessage());
            }
            return bmp;
        }
    }
}
