package nicosantomartino.a15apiclima;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Activity_Detalle extends AppCompatActivity {
    private int id;
    TextView txtUbicacion;
    TextView txtActual;
    TextView txtMinima;
    TextView txtMaxima;
    TextView txtHumedad;
    TextView txtPresion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__detalle);

        Bundle extras = getIntent().getExtras();

        id=extras.getInt("ID");
        txtUbicacion = (TextView) findViewById(R.id.txtUbicacion);
        txtActual = (TextView) findViewById(R.id.txtActual);
        txtMinima = (TextView) findViewById(R.id.txtMinima);
        txtMaxima = (TextView) findViewById(R.id.txtMaxima);
        txtHumedad = (TextView) findViewById(R.id.txtHumedad);
        txtPresion = (TextView) findViewById(R.id.txtPresion);

        final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
        final String KEY_API = "03cf03fccd8b992536a08b53036a682e";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherService service = retrofit.create(WeatherService.class);

       // Call<City> cityCall = service.getCity(3835994, KEY_API, "metric", "es"); //SANTA ROSA (L.P)
        Call<City> cityCall = service.getCity(id, KEY_API, "metric", "es");

        cityCall.enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, Response<City> response) {
                City city = response.body();

                txtUbicacion.setText(String.valueOf(city.getName()));
                txtActual.setText(String.valueOf(city.getClima().getTemp())+" Celsius");

                txtMinima.setText(String.valueOf(city.getClima().getTemp_min())+" Celsius");
                txtMaxima.setText(String.valueOf(city.getClima().getTemp_max())+" Celsius");

                txtHumedad.setText(String.valueOf(city.getClima().getHumidity())+" %");
                txtPresion.setText(String.valueOf(city.getClima().getPressure())+" HPA");
            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {
                Toast.makeText(Activity_Detalle.this, "Error al consultar", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
