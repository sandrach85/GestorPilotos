package com.example.bc0148.gestorpilotos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static com.example.bc0148.gestorpilotos.PilotoContract.tablaPiloto;

public class AlmacenPilotos extends SQLiteOpenHelper {

    //Nombre del fichero de base de datos
    protected static final String DEFAULT_DB_FILENAME = "pilotos.db";
    protected static final int DATABADE_VERSION = 1;


    public AlmacenPilotos(Context context) {
        super(context, DEFAULT_DB_FILENAME, null, DATABADE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String consultaSQL = "CREATE TABLE " + tablaPiloto.TABLE_NAME + " ("
                + tablaPiloto.COL_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + tablaPiloto.COL_NAME_NOMBRE + " TEXT,"
                + tablaPiloto.COL_NAME_DORSAL + " INTEGER,"
                + tablaPiloto.COL_NAME_MOTO + " TEXT,"
                + tablaPiloto.COL_NAME_ACTIVO + " INTEGER)";
        db.execSQL(consultaSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String consultaSQL = "DROP TABLE " + tablaPiloto.TABLE_NAME;
        db.execSQL(consultaSQL);
        onCreate(db);
    }

    /**
     * Añadir piloto
     * @param piloto
     * @return int Nª de fila
     */
    public int add(Piloto piloto){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(tablaPiloto.COL_NAME_ID, piloto.get_id());
        valores.put(tablaPiloto.COL_NAME_NOMBRE, piloto.get_nombre());
        valores.put(tablaPiloto.COL_NAME_DORSAL, piloto.get_dorsal());
        valores.put(tablaPiloto.COL_NAME_MOTO, piloto.get_moto());
        if (piloto.is_activo()){
            valores.put(tablaPiloto.COL_NAME_ACTIVO, 0);
        }else {
            valores.put(tablaPiloto.COL_NAME_ACTIVO, 1);
        }

        return (int)db.insert(tablaPiloto.TABLE_NAME, null, valores);
    }

    public ArrayList<Piloto> getAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Piloto> resultado = new ArrayList<>();

        String consultaSQL="SELECT * FROM " + tablaPiloto.TABLE_NAME;
        Cursor cursor=db.rawQuery(consultaSQL,null);

        if (cursor!=null) {
            cursor.moveToFirst();
            do {
                resultado.add(new Piloto(cursor.getInt(cursor.getColumnIndex(tablaPiloto.COL_NAME_ID)),
                        cursor.getString(cursor.getColumnIndex(tablaPiloto.COL_NAME_NOMBRE)),
                        cursor.getInt(cursor.getColumnIndex(tablaPiloto.COL_NAME_DORSAL)),
                        cursor.getString(cursor.getColumnIndex(tablaPiloto.COL_NAME_MOTO)),
                        cursor.getInt(cursor.getColumnIndex(tablaPiloto.COL_NAME_ACTIVO)) != 0));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return resultado;

    }

    public long count(){
        String consultaSQL="SELECT COUNT(*) FROM" + tablaPiloto.TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(consultaSQL,null);
        cursor.moveToFirst();
        long numero = cursor.getLong(0);

        cursor.close();
        return numero;
    }


}
