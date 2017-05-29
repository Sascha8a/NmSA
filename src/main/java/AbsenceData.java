import java.util.ArrayList;

/**
 * name: Glavanits Marcel & Alexander Lampalzer
 * matnr.: i14075 & i14085
 * catnr.: 03 & 10
 * Created on 15.05.2017
 * file: AbsenceData
 * Class: 3CHIF
 */

public class AbsenceData {
    public String date;
    public int total;
    public ArrayList<AbsenceDetail> details;
    public ArrayList<AbsenceSummary> summary;

    AbsenceData(String date) {
        this.date = date;
        this.total = 0;
        this.details = new ArrayList<AbsenceDetail>();
        this.summary = new ArrayList<AbsenceSummary>();
    }

    void addDetail(AbsenceDetail detail) {
        this.details.add(detail);
        this.total += detail.value;
    }

    void addSummary(AbsenceSummary summary) {
        this.summary.add(summary);
    }
}
