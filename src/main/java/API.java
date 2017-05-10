import com.google.gson.Gson;
import static spark.Spark.get;

/**
 * Created by UltraKnecht on 08.05.2017.
 */
public class API {

    private Controller controller;
    private Gson g = new Gson();

    public API(Controller controller) {

        this.controller = controller;
        this.defineEndpoints();
    }

    public void defineEndpoints() {
        get("/api/log", (req, res) -> {
            res.type("application/json");
            return "{\"message\":\"Custom 404\"}";
        });
    }
}
