package nicosantomartino.a15apiclima;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
/**
 * Created by nicol on 02/11/2017.
 */

//INTERFAZ: Requisito para realizar la llamada
public interface WeatherService {

    @GET("weather")
    Call<City> getCity(@Query("q") String city, @Query("appid") String key);

    @GET("weather")
    Call<City> getCity(@Query("id") int idCity, @Query("appid") String key);

    @GET("weather")
    Call<City> getCity(@Query("id") int idCity, @Query("appid") String key, @Query("units") String value);

    //USAMOS
    @GET("weather")
    Call<City> getCity(@Query("id") int idCity, @Query("appid") String key, @Query("units") String value, @Query("lang") String lang);
}

