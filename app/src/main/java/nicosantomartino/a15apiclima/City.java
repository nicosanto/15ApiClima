package nicosantomartino.a15apiclima;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
/**
 * Created by nicol on 02/11/2017.
 */

public class City {
    private int id;  //Variables Primitiva
    private String name;
    @SerializedName("main")   //anotacion: afuera el .json se llama main, dentro lo llamo clima.
    private Clima clima;  //Objeto del tipo Clima(Clase)

    public City(int id, String name, Clima clima) {
        this.id = id;
        this.name = name;
        this.clima = clima;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Clima getClima() {
        return clima;
    }

    public void setClima(Clima clima) {
        this.clima = clima;
    }

    public static Clima parseJSON(String response){  //response:consulta padre ob hijo clima
        Gson gson = new GsonBuilder().create();
        Clima clima = gson.fromJson(response,Clima.class); //response(devuelve de la consulta de afuera), clase donde se inyecta los datos
        return clima;
    }
}
