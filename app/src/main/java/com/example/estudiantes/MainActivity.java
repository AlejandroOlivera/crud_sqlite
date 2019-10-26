package com.example.estudiantes;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    daoEstudiante dao;
    Adaptador adapter;
    ArrayList<Estudiantes> lista;
    Estudiantes ed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_estudiantes);

        dao = new daoEstudiante(this);
        lista = dao.verTodos();
        adapter = new Adaptador(this, lista, dao);

        ListView list = (ListView) findViewById(R.id.lista);
        Button agregar = (Button) findViewById(R.id.agregar);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Vista previa de registro
            }
        });

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Agregar


                final Dialog dialogo = new Dialog(MainActivity.this);
                dialogo.setTitle("Nuevo Registro");
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
                agregar.setText("Agregar");
                Button cancelar = (Button) dialogo.findViewById(R.id.bt_cancelar);

                agregar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        try {

                            ed = new Estudiantes(Integer.parseInt(identificacion.getText().toString()), nombre.getText().toString(), apellido.getText().toString(),
                                    colegio.getText().toString(), tipo.getText().toString(), departamento.getText().toString(), ciudad.getText().toString(),
                                    Integer.parseInt(puntaje.getText().toString()) );

                             dao.agregar(ed);
                             lista = dao.verTodos();
                             adapter.notifyDataSetChanged();
                             dialogo.dismiss();


                        }catch (Exception e){
                            Toast.makeText(getApplication(), "Error", Toast.LENGTH_SHORT).show();
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





    }
}
