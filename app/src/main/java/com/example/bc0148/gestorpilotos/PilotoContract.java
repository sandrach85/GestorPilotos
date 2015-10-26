package com.example.bc0148.gestorpilotos;

import android.provider.BaseColumns;

public abstract class PilotoContract {

    public PilotoContract() {
    }

    public static class TablaPiloto implements BaseColumns {
        public static final String TABLE_NAME = "pilotos";
        public static final String COL_NAME_ID = "id";
        public static final String COL_NAME_NOMRE = "nombre";
        public static final String COL_NAME_DORSAL = "dorsal";
        public static final String COL_NAME_MOTO = "moto";
        public static final String COL_NAME_ACTIVO = "activo";

    }

}
