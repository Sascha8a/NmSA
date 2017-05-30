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

    /**
     * One Absece Entry
     * @param date The date of the entry
     */
    AbsenceData(String date) {
        this.date = date;
        this.total = 0;
        this.details = new ArrayList<AbsenceDetail>();
        this.summary = new ArrayList<AbsenceSummary>();
    }

    /**
     * Add a detail to the entry
     * @param detail The Detail
     */
    void addDetail(AbsenceDetail detail) {
        this.details.add(detail);
        this.total += detail.value;
    }

    /**
     * Add a summary to the entry
     * @param summary Summary
     */
    void addSummary(AbsenceSummary summary) {
        this.summary.add(summary);
    }
}
