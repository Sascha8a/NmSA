import com.google.gson.Gson;
import jdk.nashorn.internal.runtime.options.LoggingOption;

import static spark.Spark.get;
import static spark.Spark.init;
import static spark.Spark.port;

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
