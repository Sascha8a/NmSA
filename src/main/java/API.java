import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
            Gson gson = new GsonBuilder().create();
            return gson.toJson(LoggerSingleton.getInstance().getLogEntries());
        });
    }
}
