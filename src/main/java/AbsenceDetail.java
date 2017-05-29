import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * name: Glavanits Marcel & Alexander Lampalzer
 * matnr.: i14075 & i14085
 * catnr.: 03 & 10
 * Created on 15.05.2017
 * file: AbsenceDetail
 * Class: 3CHIF
 */

public class AbsenceDetail {
    public String name; // Name of the Student. Has be called name because of easier converting to json.
    public int value; // Amount of seconds absent. Value because of converting.
    public Date date;
    public Integer id;

    AbsenceDetail(String name, int value, Date date) {
        this.name = name;
        this.value = value;
        this.date = date;
    }

    public String getDate() {
        return new SimpleDateFormat("YYYY-MM-DD").format(this.date);
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
