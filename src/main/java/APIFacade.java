import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

import static spark.Spark.get;

/**
 * Created by UltraKnecht on 08.05.2017.
 */
public class APIFacade extends Observable {

    private Controller controller;

    public APIFacade(Controller controller) {

        this.controller = controller;
        this.defineEndpoints();
    }

    public void defineEndpoints() {
        get("/api/log", (req, res) -> {
            this.setChanged();
            this.notifyObservers(req.protocol() + " " + req.ip() + " " + req.pathInfo());

            Gson gson = new GsonBuilder().create();
            HashMap<String, List<LogEntry>> ret = new HashMap();
            ret.put("data", LoggerSingleton.getInstance().getLogEntries());
            return gson.toJson(ret);
        });

        get("/api/absences", (req, res) -> {
            this.setChanged();
            this.notifyObservers(req.protocol() + " " + req.ip() + " " + req.pathInfo());

            Gson gson = new GsonBuilder().create();

            ArrayList<AbsenceDetail> details = this.controller.getAbsenceDetails();
            HashMap<String, AbsenceData> days = new HashMap<String, AbsenceData>();

            details.forEach(detail -> {
                String key = detail.getDate();
                if (days.containsKey(key)) {
                    AbsenceData day = days.get(key);
                    day.addDetail(detail);
                    day.addSummary(new AbsenceSummary(detail.name, detail.value));
                    days.replace(detail.getDate(), day);
                } else {
                    AbsenceData day = new AbsenceData(key);
                    day.addDetail(detail);
                    day.addSummary(new AbsenceSummary(detail.name, detail.value));
                    days.put(key, day);
                }
            });

            ArrayList<AbsenceData> data = new ArrayList<AbsenceData>();

            days.forEach((s, absenceData) -> {
                data.add(absenceData);
            });

            return gson.toJson(data);
        });
    }
}
