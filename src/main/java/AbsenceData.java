import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Sascha on 15/05/2017.
 */
public class AbsenceData {
    public String date;
    public int total;
    public ArrayList<AbsenceDetail> details;
    public ArrayList<AbsenceSummary> summary;

    public AbsenceData(String date) {
        this.date = date;
        this.total = 0;
        this.details = new ArrayList<AbsenceDetail>();
        this.summary = new ArrayList<AbsenceSummary>();
    }

    public void addDetail(AbsenceDetail detail) {
        this.details.add(detail);
        this.total += detail.value;
    }

    public void addSummary(AbsenceSummary summary) {
        this.summary.add(summary);
    }
}
