package com.example.estudiantes;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {

    ArrayList <Estudiantes> lista;
    daoEstudiante dao;
    Estudiantes ed;
    Activity a;
    int id = 0;

    public Adaptador(Activity a, ArrayList<Estudiantes> lista, daoEstudiante dao) {
        this.a = a;
        this.lista = lista;
        this.dao = dao;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getCount() {

       return lista.size();
//        return (lista == null) ? 0 : lista.size();
//

    }

    @Override
    public Estudiantes getItem(int i) {
        ed = lista.get(i);
        return null;
    }

    @Override
    public long getItemId(int i) {
        ed = lista.get(i);
        return ed.getId();
    }

    @Override
    public View getView(int position, final View view, ViewGroup parent) {

        View v = view;

        if (v == null) {

            LayoutInflater li = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.item, null);
        }

        ed = lista.get(position);
        TextView identificacion = (TextView) v.findViewById(R.id.t_identificacion);
        TextView nombre = (TextView) v.findViewById(R.id.t_nombre);
        TextView apellido = (TextView) v.findViewById(R.id.t_apellido);
        TextView tipo = (TextView) v.findViewById(R.id.t_tipo);
        TextView colegio = (TextView) v.findViewById(R.id.t_colegio);
        TextView departamento = (TextView) v.findViewById(R.id.t_departamento);
        TextView ciudad = (TextView) v.findViewById(R.id.t_ciudad);
        TextView puntaje = (TextView) v.findViewById(R.id.t_puntaje);

        Button editar = (Button) v.findViewById(R.id.bt_modificar);
        Button eliminar = (Button) v.findViewById(R.id.bt_eliminar);

        identificacion.setText(""+ed.getIdentificacion());
        nombre.setText(ed.getNombre());
        apellido.setText(ed.getApellido());
        colegio.setText(ed.getColegio());
        tipo.setText(ed.getTipo());
        departamento.setText(ed.getDepartamento());
        ciudad.setText(ed.getCiudad());
        puntaje.setText(""+ed.getPuntaje());

        editar.setTag(position);
        eliminar.setTag(position);


        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view ) {


                int pos = Integer.parseInt(view.getTag().toString());
                final Dialog dialogo = new Dialog(a);
                dialogo.setTitle("Editar Registro");
                dialogo.setCancelable(true);
                dialogo.setContentView(R.layout.activity_main);
                dialogo.show();
                final EditText identificacion = (EditText) dialogo.findViewById(R.id.et_id);
                final EditText nombre = (EditText) dialogo.findViewById(R.id.et_nombre);
                final EditText apellido = (EditText) dialogo.findViewById(R.id.et_apellido);
                final EditText colegio = (EditText) dialogo.findViewById(R.id.et_colegio);
                final EditText tipo = (EditText) dialogo.findViewById(R.id.et_tipo);
                final EditText departamento = (EditText) dialogo.findViewById(R.id.et_departamento);
                final EditText ciudad = (EditText) dialogo.findViewById(R.id.et_ciudad);
                final EditText puntaje = (EditText) dialogo.findViewById(R.id.et_puntaje);

                Button agregar = (Button) dialogo.findViewById(R.id.bt_add);
                agregar.setText("Guardar");
                Button cancelar = (Button) dialogo.findViewById(R.id.bt_cancelar);

                ed = lista.get(pos);
                setId(ed.getId());
                identificacion.setText(""+ ed.getIdentificacion());
                nombre.setText(ed.getNombre());
                apellido.setText(ed.getApellido());
                colegio.setText(ed.getColegio());
                tipo.setText(ed.getTipo());
                departamento.setText(ed.getDepartamento());
                ciudad.setText(ed.getCiudad());
                puntaje.setText(""+ed.getPuntaje());


                agregar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try {

                            ed = new Estudiantes(getId(),Integer.parseInt(identificacion.getText().toString()), nombre.getText().toString(), apellido.getText().toString(),
                                    colegio.getText().toString(), tipo.getText().toString(), departamento.getText().toString(), ciudad.getText().toString(),
                                    Integer.parseInt(puntaje.getText().toString()) );

                            dao.modificar(ed);
                            lista = dao.verTodos();
                            notifyDataSetChanged();
                            dialogo.dismiss();


                        }catch (Exception e){
                            Toast.makeText(a, "Error", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialogo.dismiss();

                    }
                });
            }
        });


        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int pos = Integer.parseInt(view.getTag().toString());
                ed = lista.get(pos);
                setId(ed.getId());

                AlertDialog.Builder del = new AlertDialog.Builder(a);
                del.setMessage("Estas seguro de eliminar el registro?");
                del.setCancelable(false);
                del.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dao.eliminar(getId());
                        lista = dao.verTodos();
                        notifyDataSetChanged();

                    }
                });
                del.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                    }
                });

                del.show();

            }
        });


        return v;
    }
}
