import Logging.LoggerSingleton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.servlet.MultipartConfigElement;
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import static spark.Spark.get;
import static spark.Spark.post;

/**
 * name: Glavanits Marcel & Alexander Lampalzer
 * matnr.: i14075 & i14085
 * catnr.: 03 & 10
 * Created on 08.05.2017
 * file: APIFacade
 * Class: 3CHIF
 */

public class APIFacade extends Observable {

    private Controller controller;

    public APIFacade(Controller controller) {

        this.controller = controller;
        this.defineEndpoints();
    }

    private void defineEndpoints() {
        File uploadDir = new File("upload");
        uploadDir.mkdir();

        get("/api/log", (req, res) -> {
            this.setChanged();
            this.notifyObservers(req.protocol() + " " + req.ip() + " " + req.pathInfo());

            Gson gson = new GsonBuilder().create();
            HashMap ret = new HashMap();
            ret.put("data", Logging.LoggerSingleton.getInstance().getLogEntries());
            return gson.toJson(ret);
        });

        post("/api/test", (req, res) -> "Works properly.!");

        post("/api/upload/absences", (req, res) -> {

            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));

            try (BufferedReader input = new BufferedReader(new InputStreamReader(req.raw().getPart("uploaded_file").getInputStream(), "UTF-8"))) { // getPart needs to use same "name" as input field in form
                FileWriter out = null;

                try {
                    out = new FileWriter(uploadDir.getAbsolutePath() + "\\absences.txt");

                    int c;
                    while ((c = input.read()) != -1) {
                        out.write(c);
                    }
                }finally {
                    if (input != null) {
                        input.close();
                    }
                    if (out != null) {
                        out.close();
                    }
                }
            }

            this.setChanged();
            this.notifyObservers(req.protocol() + " " + req.ip() + " " + req.pathInfo());

            this.controller.updateAbsence(uploadDir.getAbsolutePath() + "\\absences.txt");

            return "Upload successful.";
        });

        post("/api/upload/timetable", (req, res) -> {

            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));

            try (InputStream input = req.raw().getPart("uploaded_file").getInputStream()) { // getPart needs to use same "name" as input field in form
                FileWriter out = null;

                try {
                    out = new FileWriter(uploadDir.getAbsolutePath() + "\\timetable.txt");

                    int c;
                    while ((c = input.read()) != -1) {
                        out.write(c);
                    }
                }finally {
                    if (input != null) {
                        input.close();
                    }
                    if (out != null) {
                        out.close();
                    }
                }
            }

            this.setChanged();
            this.notifyObservers(req.protocol() + " " + req.ip() + " " + req.pathInfo());

            this.controller.updateTests(uploadDir.getAbsolutePath() + "\\timetable.txt");

            return "Upload successful.";
        });

        get("/api/absences", (req, res) -> {
            this.setChanged();
            this.notifyObservers(req.protocol() + " " + req.ip() + " " + req.pathInfo());

            Gson gson = new GsonBuilder().create();

            ArrayList<AbsenceDetail> details = this.controller.getAbsenceDetails();
            TreeMap<String, AbsenceData> days = new TreeMap<>();

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

            ArrayList<AbsenceData> data = new ArrayList<>();

            days.forEach((s, absenceData) -> data.add(absenceData));

            return gson.toJson(data);
        });

        get("/api/ranking", (req, res) -> {
            this.setChanged();
            this.notifyObservers(req.protocol() + " " + req.ip() + " " + req.pathInfo());

            Gson gson = new GsonBuilder().create();
            ArrayList<AbsenceDetail> ranking = this.controller.getRanking();

            for (Integer i = 0; i < ranking.size(); i++) {
                ranking.get(i).setId(i + 1);
            }

            HashMap ret = new HashMap();
            ret.put("data", ranking);
            return gson.toJson(ret);
        });

        get("/api/month/absence", (req, res) -> {
            this.setChanged();
            this.notifyObservers(req.protocol() + " " + req.ip() + " " + req.pathInfo());

            Gson gson = new GsonBuilder().create();

            return gson.toJson(this.controller.getMonthAverage());
        });

        get("/api/user/allocation/:name", (req, res) -> {
            this.setChanged();
            this.notifyObservers(req.protocol() + " " + req.ip() + " " + req.pathInfo());

            Gson gson = new GsonBuilder().create();

            this.controller.getAbsencePerDay(req.params("name"));

            return gson.toJson(this.controller.getAbsencePerDay(req.params("name")));
        });

        get("/api/user/testPresent/:name", (req, res) -> {
            this.setChanged();
            this.notifyObservers(req.protocol() + " " + req.ip() + " " + req.pathInfo());

            Gson gson = new GsonBuilder().create();

            int notPresent = this.controller.getAmountTestPresent(req.params("name"));
            int present = this.controller.getTestAmount() - notPresent;

            return "[" + present + "," + notPresent + "]";
        });

        get("/api/shutdown", (req, res) -> {
            this.controller.shutdown();
            return "";
        });
    }
}
