package nicosantomartino.a15apiclima;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class activity_agregar extends AppCompatActivity {

    private SqLiteHelper sqLiteHelper;
    private SQLiteDatabase db;
    private EditText edtNombre;
    private Button btnAgregar;

    private String Nombre="Prueba";
    private int Id=383887;
    private EditText edtID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        sqLiteHelper = new SqLiteHelper(this,"DBciudad", null, 1);
        db = sqLiteHelper.getWritableDatabase();


        edtID=(EditText) findViewById(R.id.edtID);
        //Id=edtID.toString();

       //Id = Integer.parseInt(edtID.getText().toString());

        // android:inputType="number"
        //  android:inputType="text"

         edtNombre=(EditText) findViewById(R.id.edtNombre);
         Nombre=edtNombre.toString();

         btnAgregar= (Button)findViewById(R.id.btnAgregar);

         btnAgregar.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 //AGREGAR
                     ContentValues regnuevo = new ContentValues();

                     //AGREGO UN EJEMPLO
                     regnuevo.put(DbConstantes.COLUMN_ID, 3996063);
                     regnuevo.put(DbConstantes.COLUMN_NOMBRE, "Nuevo Mexico");

                     //ASI ANDA MAL
                    //  regnuevo.put(DbConstantes.COLUMN_ID, Id);
                    //  regnuevo.put(DbConstantes.COLUMN_NOMBRE, Nombre);

                 db.insert(DbConstantes.TABLE_CIUDADES, null, regnuevo);
                 Toast.makeText(getApplicationContext(),"Registro guardado correctamente.", Toast.LENGTH_LONG).show();
             }
         });

    }

}
