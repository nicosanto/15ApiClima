package nicosantomartino.a15apiclima;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private ListView listCiudades;
    private ArrayList<Ciudad> ciudades;
    private SwipeRefreshLayout swipe;
    private SqLiteHelper sqLiteHelper;
    private SQLiteDatabase db;
    private activity_adapter_ciudad adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listCiudades = (ListView) findViewById(R.id.lvCiudades);
        ciudades = new ArrayList<Ciudad>();
        swipe =(SwipeRefreshLayout)findViewById(R.id.swipeCiudades);

        sqLiteHelper = new SqLiteHelper(this,"DBciudad", null, 1);
        db = sqLiteHelper.getWritableDatabase();

        adapter = new activity_adapter_ciudad(ciudades);
        listCiudades.setAdapter(adapter);

        FloatingActionButton fab =  findViewById(R.id.fab);

        removerTodo();
        //REFRESH
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                update();
                adapter.notifyDataSetChanged();   //Avisamos al adaptador q hubo cambios en los datos para q refresque
                swipe.setRefreshing(false);
            }
        });

        //DETALLE
        listCiudades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Ciudad ciudad = ciudades.get(position);
                //Donde estamos y a donde queremos ir
                Intent intent = new Intent(MainActivity.this, Activity_Detalle.class);
                intent.putExtra("ID",ciudad.getId());
                startActivity(intent);
            }
        });

        //BOTON +
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Snackbar.make(view, "Se presionó el FAB", Snackbar.LENGTH_LONG)
             //         .setAction("Action", null).show();
               // Intent intent = new Intent(this, activity_agregar.class);
                startActivity(new Intent(MainActivity.this,activity_agregar.class));

            }
        });
        removerTodo();
        cargarCiudades();
        update();
    }

    private void cargarCiudades() {

        //verificamos si abrio la BD
        if(db!=null){
            //Registro 1
            ContentValues reg1 = new ContentValues();
            reg1.put(DbConstantes.COLUMN_ID,3835994);
            reg1.put(DbConstantes.COLUMN_NOMBRE,"Santa Rosa");

            ContentValues  reg2 = new ContentValues();
            reg2.put(DbConstantes.COLUMN_ID,3860259);
            reg2.put(DbConstantes.COLUMN_NOMBRE,"Cordoba");

            ContentValues reg3 = new ContentValues();
            reg3.put(DbConstantes.COLUMN_ID,6693229);
            reg3.put(DbConstantes.COLUMN_NOMBRE,"San Nicolas Bs As");

            ContentValues reg4 = new ContentValues();
            reg4.put(DbConstantes.COLUMN_ID,3844421);
            reg4.put(DbConstantes.COLUMN_NOMBRE,"Mendoza");

            ContentValues reg5 = new ContentValues();
            reg5.put(DbConstantes.COLUMN_ID,3838233);
            reg5.put(DbConstantes.COLUMN_NOMBRE,"Salta");

            ContentValues reg6 = new ContentValues();
            reg6.put(DbConstantes.COLUMN_ID,3836564);
            reg6.put(DbConstantes.COLUMN_NOMBRE,"Jujuy");

            ContentValues reg7 = new ContentValues();
            reg7.put(DbConstantes.COLUMN_ID,3836277);
            reg7.put(DbConstantes.COLUMN_NOMBRE,"Santa Fe");

            ContentValues reg8 = new ContentValues();
            reg8.put(DbConstantes.COLUMN_ID,3836873);
            reg8.put(DbConstantes.COLUMN_NOMBRE,"Tucuman");

            ContentValues reg9 = new ContentValues();
            reg9.put(DbConstantes.COLUMN_ID,3839307);
            reg9.put(DbConstantes.COLUMN_NOMBRE,"Rawson");

            ContentValues reg10 = new ContentValues();
            reg10.put(DbConstantes.COLUMN_ID,3843123);
            reg10.put(DbConstantes.COLUMN_NOMBRE,"Neuquen");

            ContentValues reg11 = new ContentValues();
            reg11.put(DbConstantes.COLUMN_ID,3848950);
            reg11.put(DbConstantes.COLUMN_NOMBRE,"La Rioja");

            ContentValues reg12 = new ContentValues();
            reg12.put(DbConstantes.COLUMN_ID,4164138);
            reg12.put(DbConstantes.COLUMN_NOMBRE,"Miami");

            db.insert(DbConstantes.TABLE_CIUDADES, null, reg1);
            db.insert(DbConstantes.TABLE_CIUDADES, null, reg2);
            db.insert(DbConstantes.TABLE_CIUDADES, null, reg3);
            db.insert(DbConstantes.TABLE_CIUDADES, null, reg4);
            db.insert(DbConstantes.TABLE_CIUDADES, null, reg5);
            db.insert(DbConstantes.TABLE_CIUDADES, null, reg6);
            db.insert(DbConstantes.TABLE_CIUDADES, null, reg7);
            db.insert(DbConstantes.TABLE_CIUDADES, null, reg8);
            db.insert(DbConstantes.TABLE_CIUDADES, null, reg9);
            db.insert(DbConstantes.TABLE_CIUDADES, null, reg10);
            db.insert(DbConstantes.TABLE_CIUDADES, null, reg11);
            db.insert(DbConstantes.TABLE_CIUDADES, null, reg12);
        }
    }

    private List<Ciudad> obtenerCiudades(){
     //  SqLiteHelper helper = new SqLiteHelper(this,"DBciudad", null, 1);
      //  SQLiteDatabase db = helper.getWritableDatabase();

        String sqlSELECT = "SELECT * FROM " + DbConstantes.TABLE_CIUDADES;
        List<Ciudad> lista = new ArrayList<>();
        try{
            Cursor cursor = db.rawQuery(sqlSELECT,null);
            if(cursor.moveToFirst()){

                while (cursor.isAfterLast()==false){

                    int id = cursor.getInt(cursor.getColumnIndex(DbConstantes.COLUMN_ID));
                    String nombre = cursor.getString(cursor.getColumnIndex(DbConstantes.COLUMN_NOMBRE));

                    lista.add(new Ciudad(id,nombre));
                    cursor.moveToNext();
                }
            }
        }catch(SQLiteException ex){
            Toast t = Toast.makeText(context, "Error: "+ ex.getMessage(), Toast.LENGTH_SHORT);
            t.show();
        }
        return lista;
    }

    private void update(){
        ciudades.clear();
        ciudades.addAll(obtenerCiudades());
        adapter.notifyDataSetChanged();
    }

    private void removerTodo(){
        db.delete(DbConstantes.TABLE_CIUDADES,"",null);
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }

}
