package com.example.estudiantes;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class daoEstudiante {

    SQLiteDatabase db;
    ArrayList<Estudiantes> lista = new ArrayList<Estudiantes>();
    Estudiantes ed;
    Context ct;


    String nombreDB = "DBestudiantes";
    String tabla = "create table if not exists estudiantes (id integer primary key autoincrement, " +
            "identificacion integer, nombre text, apellido text, colegio text, tipo text, departamento text, " +
            "ciudad text, puntaje integer)";


    public daoEstudiante(Context ed) {
        this.ct = ed;
        db = ed.openOrCreateDatabase(nombreDB, Context.MODE_PRIVATE, null);
        db.execSQL(tabla);
    }

    public boolean agregar(Estudiantes ed) {

        ContentValues contenedor = new ContentValues();
        contenedor.put("identificacion", ed.getIdentificacion());
        contenedor.put("nombre", ed.getNombre());
        contenedor.put("apellido", ed.getApellido());
        contenedor.put("colegio", ed.getColegio());
        contenedor.put("tipo", ed.getTipo());
        contenedor.put("departamento", ed.getDepartamento());
        contenedor.put("ciudad", ed.getCiudad());
        contenedor.put("puntaje", ed.getPuntaje());

        return (db.insert("Estudiantes" ,null, contenedor))>0;

    }

    public boolean eliminar(int id) {



        return (db.delete("Estudiantes" ,  "id="+id,null))>0;



    }

    public boolean modificar (Estudiantes ed) {

        ContentValues contenedor = new ContentValues();
        contenedor.put("identificacion", ed.getIdentificacion());
        contenedor.put("nombre", ed.getNombre());
        contenedor.put("apellido", ed.getApellido());
        contenedor.put("colegio", ed.getColegio());
        contenedor.put("tipo", ed.getTipo());
        contenedor.put("departamento", ed.getDepartamento());
        contenedor.put("ciudad", ed.getCiudad());
        contenedor.put("puntaje", ed.getPuntaje());

        return (db.update("Estudiantes" , contenedor, "id="+ed.getId(),null))>0;



    }

    public ArrayList<Estudiantes> verTodos() {
        lista.clear();
        Cursor cursor = db.rawQuery("select * from estudiantes", null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                lista.add(new Estudiantes(cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getInt(8)
                ));

            } while (cursor.moveToNext());

        }
            return lista;

    }

    public Estudiantes verUno (int posicion){
        Cursor cursor = db.rawQuery("select * from estudiantes", null);
        cursor.moveToPosition(posicion);
         ed = new Estudiantes(cursor.getInt(0),
                 cursor.getInt(1),
                 cursor.getString(2),
                 cursor.getString(3),
                 cursor.getString(4),
                 cursor.getString(5),
                 cursor.getString(6),
                 cursor.getString(7),
                 cursor.getInt(8)
         );
            return ed;
    }

}
